package com.a608.modac.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.chat.ChatRoom;
import com.a608.modac.model.chat.ChatRoomDto;
import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.follow.FollowResponse;
import com.a608.modac.repository.ChatRoomRepository;
import com.a608.modac.repository.FollowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
	private final ChatRoomRepository chatRoomRepository;
	private final FollowRepository followRepository;

	// 채팅 룸 생성, 삭제 -> 스터디룸 서비스에서 수행.

	@Override
	public ChatRoomDto findChatRoomBySeq(final Long roomsSeq) {
		final ChatRoom chatRoom = chatRoomRepository.findById(roomsSeq).orElseThrow(NoSuchElementException::new);

		return ChatRoomDto.builder()
			.seq(roomsSeq)
			.lastMessageSeq(chatRoom.getLastMessageSeq())
			.lastMessageTime(chatRoom.getLastMessageTime())
			.build();
	} // 특정 채팅룸 찾기. -> 입장할 때 사용.

	public List<FollowResponse> findAllChatRoomsByFollowingsSeq(final Long followingsSeq) {
		final List<Follow> allByFromUserSeq = followRepository.findAllByFromUser_Seq(followingsSeq);

		return allByFromUserSeq.stream().map(FollowResponse::fromEntity).collect(Collectors.toList());
	} // 특정 유저의 모든 채팅룸 찾기. -> 팔로잉 관계에서 1:1 채팅방 정보를 모두 가져와서 프론트로 전달.



}
