package com.a608.modac.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.a608.modac.model.chat.ChatRoom;
import com.a608.modac.model.participant.Participant;
import com.a608.modac.model.participant.ParticipantPK;
import com.a608.modac.model.participant.ParticipantRequest;
import com.a608.modac.model.room.Room;
import com.a608.modac.model.room.RoomRequest;
import com.a608.modac.model.room.RoomResponse;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.ChatRoomRepository;
import com.a608.modac.repository.ParticipantRepository;
import com.a608.modac.repository.RoomRepository;
import com.a608.modac.repository.UserRepository;

@Service
public class RoomServiceImpl implements RoomService{

	private final RoomRepository roomRepository;
	private final UserRepository userRepository;
	private final ChatRoomRepository chatRoomRepository;
	private final ParticipantRepository participantRepository;

	public RoomServiceImpl(RoomRepository roomRepository, UserRepository userRepository,
		ChatRoomRepository chatRoomRepository, ParticipantRepository participantRepository) {
		this.roomRepository = roomRepository;
		this.userRepository = userRepository;
		this.chatRoomRepository = chatRoomRepository;
		this.participantRepository = participantRepository;
	}

	@Override		// 멀티룸 조회
	public RoomResponse findRoomById(final Long seq) {
		final Room findRoomById = roomRepository.findById(seq).orElseThrow(()->new NoSuchElementException("NoRoom"));
		return new RoomResponse(findRoomById);
	}

	@Override		// 멀티룸 목록 조회
	public List<RoomResponse> findAllRooms(Long usersSeq) {
		User user = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
		final List<Room> findAllRooms = roomRepository.findAll();
		Collections.sort(findAllRooms, (r1, r2) -> {
			if(!(r1.getParticipants().contains(user)) && r2.getParticipants().contains(user)){
				return 1;
			}
			return (int) (r2.getSeq() - r1.getSeq());
		});
		return findAllRooms.stream().map(RoomResponse::new).collect(Collectors.toList());
	}

	@Override		// 내가 참여하고 있는 비공개 멀티룸 목록 조회
	public List<RoomResponse> findMyRooms(Long usersSeq) {
		// Validation
		User user = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
		List<Participant> participants = participantRepository.findAllByParticipantPK_UsersSeq(usersSeq);
		final List<Room> findMyRooms = new ArrayList<>();

		for(Participant p: participants){
			Room room = roomRepository.findById(p.getParticipantPK().getRoom().getSeq())
				.orElseThrow(NoSuchElementException::new);
			if(room.getPublicType()==0)
			findMyRooms.add(room);
		}
		Collections.sort(findMyRooms, (r1, r2) -> (int)(r2.getSeq() - r1.getSeq()));
		return findMyRooms.stream().map(RoomResponse::new).collect(Collectors.toList());
	}

	@Override	// 멀티룸 생성
	public RoomResponse createRoom(final RoomRequest roomRequest) {
		User host = userRepository.findById(roomRequest.getUsersSeq()).orElseThrow(() -> new NoSuchElementException("NoUser"));
		String code = null;
		if(roomRequest.getPublicType() == 0){ // 비공개방일때
			// 초대코드 생성 (대충 6자리코드)
			double min = 100000;
			double max = 999999;
			int random = (int) ((Math.random() * (max - min)) + min);
			code = Integer.toString(random);
		}

		// 채팅룸 생성
		ChatRoom chatRoom = chatRoomRepository.save(new ChatRoom());
		Room room = roomRepository.save(roomRequest.toEntity(host, chatRoom, code));

		// 참가자 정보 생성
		ParticipantPK participantPK = ParticipantPK.builder().room(room).usersSeq(host.getSeq()).build();
		Participant participant = participantRepository.save(new Participant(participantPK, host));
		room.getParticipants().set(0, participant);
		room.updateCurrentSize(1);

		// 스터디룸 생성
		return new RoomResponse(roomRepository.save(room));
	}

	@Override
	public RoomResponse updateRoom(final Long seq, final RoomRequest roomRequest) {
		Room room = roomRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoRoom"));
		room.updateRoom(roomRequest.getTitle(),
			roomRequest.getDescription(),
			roomRequest.getMultiTheme());
		return new RoomResponse(roomRepository.save(room));
	}

	@Transactional
	@Override
	public void deleteRoom(final Long seq) {
		// Validation
		Room room = roomRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoRoom"));

		// 채팅 로그 먼저 삭제
		// --채팅 로그 삭제하는 로직--

		// 해당 멀티룸 참가자 정보 삭제
		participantRepository.deleteAllByParticipantPKRoom(room);

		// 멀티룸 삭제
		roomRepository.delete(room);

		// 채팅룸 삭제
		chatRoomRepository.delete(room.getChatRoom());
	}

	@Override		//멀티룸에 참여하기 (participant 엔티티에서 참가자 저장)
	public RoomResponse joinRoom(Long seq, Long userSeq) {
		Room room = roomRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoRoom"));
		User user = userRepository.findById(userSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));

		if(room.getCurrentSize() >= room.getMaxSize()){
			System.out.println("최대인원을 넘을 수 없으므로 참가자가 추가되지 않았습니다.");
			return new RoomResponse(room);
		}

		ParticipantPK participantPK = ParticipantPK.builder().room(room).usersSeq(userSeq).build();
		if (participantRepository.findById(participantPK).isPresent()) {
			System.out.println("이미 방에 참여되어 있는 참가자입니다.");
			return new RoomResponse(room);
		}

		Participant participant = new Participant(participantPK, user);
		participantRepository.save(participant);

		room.participateRoom(participant);
		return new RoomResponse(roomRepository.save(room));
	}

	@Transactional
	@Override		//멀티룸에서 나가기
	public void exitRoom(Long seq, Long userSeq) {
		// Validation
		Room room = roomRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoRoom"));
		User user = userRepository.findById(userSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
		ParticipantPK participantPK = ParticipantPK.builder().room(room).usersSeq(userSeq).build();
		Participant participant = participantRepository.findById(participantPK)
			.orElseThrow(() -> new NoSuchElementException("NoParticipant"));

		// 나가는 사람이 방에 유일하게 존재하는 참가자면 스터디룸 삭제
		if (room.getParticipants().size() == 1) {
			System.out.println("마지막 참가자이므로 삭제 서비스를 호출한다.");
			deleteRoom(seq);
			return;
		}

		// 다른 사람이 존재하면, 나가는 사람 정보 삭제
		participantRepository.delete(participant);

		// 나가는 사람이 방장인 경우, 방장을 제외하고 가장 먼저 방에 들어온 사람에게 방장 위임
		if (room.getHost().equals(user)) {
			room.updateHost(userRepository.findById(
				participantRepository.findTopByParticipantPK_Room_SeqOrderByRegisteredTimeAsc(seq)
					.getParticipantPK()
					.getUsersSeq()).orElseThrow(() -> new NoSuchElementException("NoUser")));
		}

		// room 객체 수정 후 저장
		room.exitRoom(participant);
		roomRepository.save(room);
	}

	@Override
	public void updateUserAttend(Long seq, ParticipantRequest participantRequest) {
		Long usersSeq = participantRequest.getUsersSeq();
		boolean isAttended = participantRequest.isAttend();
		Room room = roomRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoRoom"));
		userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
		List<Participant> participants = room.getParticipants();
		for(Participant p: participants){
			if(p.getParticipantPK().getUsersSeq().equals(usersSeq)){
				p.updateAttend(isAttended);
				participantRepository.save(p);
				break;
			}
		}
		roomRepository.save(room);
	}

	@Override
	public boolean isMember(Long seq, Long userSeq) {
		Room room = roomRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoRoom"));
		userRepository.findById(userSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
		List<Participant> participants = room.getParticipants();
		boolean isMember = false;
		for(Participant p: participants){
			if(p.getParticipantPK().getUsersSeq().equals(userSeq)){
				isMember = true;
				break;
			}
		}
		return isMember;
	}

	@Override
	public boolean isSameCode(Long seq, String code) {
		Room room = roomRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoRoom"));
		boolean isEqual = room.getInvitationCode().equals(code.trim());
		return isEqual;
	}

	// 키워드로 스터디룸 검색
	@Override
	public List<RoomResponse> searchRooms(final Long usersSeq, final String keyword) {
		// Validation
		User myUser = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));

		List<Room> rooms = new ArrayList<>();
		rooms.addAll(roomRepository.findAllByTitleContaining(keyword)); // 방 제목으로 검색 (List)
		rooms.addAll(roomRepository.findAllByDescriptionContaining(keyword)); // 방 설명으로 검색 (List)
		List<User> users = userRepository.findAllByNicknameContaining(keyword); // 방장 닉네임으로 검색 (List)
		for (User user : users) {
			rooms.addAll(roomRepository.findAllByHost(user));
		}
		rooms.stream().distinct().collect(Collectors.toList()); // 중복 제거
		Collections.sort(rooms, (r1, r2) -> {
			if(!(r1.getParticipants().contains(myUser)) && r2.getParticipants().contains(myUser)){
				return 1;
			}
			return (int) (r2.getSeq() - r1.getSeq());
		});
		System.out.println("검색완료");
		return rooms.stream().map(RoomResponse::new).collect(Collectors.toList());
	}

}
