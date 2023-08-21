package com.a608.modac.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.a608.modac.model.chat.ChatRoom;
import com.a608.modac.model.chat.DirectMessage;
import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.follow.FollowRequest;
import com.a608.modac.model.follow.FollowResponse;
import com.a608.modac.model.notification.Notification;
import com.a608.modac.model.user.User;
import com.a608.modac.model.user.UserRequest;
import com.a608.modac.model.user.UserResponse;
import com.a608.modac.repository.ChatDirectRepository;
import com.a608.modac.repository.ChatRoomRepository;
import com.a608.modac.repository.FollowRepository;
import com.a608.modac.repository.NotificationRepository;
import com.a608.modac.repository.UserRepository;
import com.a608.modac.security.JwtTokenProvider;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final NotificationRepository notificationRepository;
    private final JwtTokenProvider jwtTokenProvider;
	private final RedisTemplate<String, DirectMessage> redisTemplate;
	private final ChatDirectRepository chatDirectRepository;

    static int KEY_STRETCHING = 1000;

    public UserServiceImpl(UserRepository userRepository, FollowRepository followRepository,
        ChatRoomRepository chatRoomRepository, NotificationRepository notificationRepository,
        JwtTokenProvider jwtTokenProvider,
        RedisTemplate<String, DirectMessage> redisTemplate,
        ChatDirectRepository chatDirectRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.notificationRepository = notificationRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTemplate = redisTemplate;
        this.chatDirectRepository = chatDirectRepository;
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) throws NoSuchAlgorithmException { // 회원 저장
        // 고양이 스킨 번호 랜덤 (1~12)
        double min = 1;
        double max = 12;
        byte skin = (byte) ((Math.random() * (max - min)) + min);

        // salt 생성
        String salt = "";
        salt = createsalt(salt);

        // 비밀번호 암호화
        String pw = userRequest.getPassword() + salt;
        pw = encryption(pw, KEY_STRETCHING);
        userRequest.setPassword(pw);

        User save = userRepository.save(userRequest.toEntity(skin, salt));
        System.out.println(save);
        return new UserResponse(save);
    }

    @Override
    public UserResponse updateUser(Long seq, UserRequest userRequest) {
        User findUser = userRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoUser"));
        findUser.updateUser(userRequest.getNickname(),userRequest.getEmail());
        User save = userRepository.save(findUser);
        return new UserResponse(save);
    }


    @Override
    public void updatePassword(Long seq, String password) throws NoSuchAlgorithmException {
        User findUser = userRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoUser"));

        // salt 생성
        String salt = "";
        salt = createsalt(salt);

        // 비밀번호 암호화
        String pw = password + salt;
        pw = encryption(pw, KEY_STRETCHING);

        findUser.updatePassword(pw);
        findUser.updateSalt(salt);

        userRepository.save(findUser);
    }

    @Override
    public void deleteUser(Long seq) {
        userRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoUser"));
        userRepository.deleteById(seq);
    }

    @Override
    public UserResponse findUserBySeq(Long seq) {
        User findUser = userRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoUser"));
        UserResponse userResponse = new UserResponse(findUser);
        return userResponse;
    }
    public UserResponse findUserByEmail(String email) {
        User findUser = userRepository.findUserByEmail(email).orElseThrow(() -> new NoSuchElementException("NoUser"));
        UserResponse userResponse = new UserResponse(findUser);
        return userResponse;
    }

    @Override
    public void checkAvailableNick(String nick) {
        User user = userRepository.findUserByNickname(nick).orElseThrow(() -> new NoSuchElementException("NoUser"));
    }

    @Override
    public void checkAvailableId(String id) {
        User user = userRepository.findUserById(id).orElseThrow(() -> new NoSuchElementException("NoUser"));
    }

    @Override
    public void checkAvailableEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new NoSuchElementException("NoUser"));
    }

    @Override
    public UserResponse login(UserRequest userRequest) throws NoSuchAlgorithmException {
        User user = userRepository.findUserById(userRequest.getId()).orElseThrow(() -> new NoSuchElementException("NoUser"));

        String salt = user.getSalt();
        String pass = userRequest.getPassword() + salt;
        pass = encryption(pass, KEY_STRETCHING);

        if(user.getPassword().equals(pass)){
           //로그인 성공
            String token = jwtTokenProvider.createToken(user);
            UserResponse userResponse = new UserResponse(user);
            userResponse.setToken(token);
            return userResponse;
        }else{
            System.out.println("[로그인 실패] - 비밀번호 불일치");
            return null;
        }
    }

    @Override
    public void following(FollowRequest followRequest) {

        // 상대가 날 팔로우하는지 찾기
        Follow follower = followRepository.findFollowByFromUser_SeqAndToUser_Seq(followRequest.getToSeq(),
            followRequest.getFromSeq());
        ChatRoom chatRoom;

        if (follower != null) { // 이미 쟤가 날 팔로우 함 (DM 방 이미 존재)
            chatRoom = follower.getChatRoom();
        } else {  // 상대방은 날 팔로우하고 있지 않음 (새로운 관계)
            chatRoom = chatRoomRepository.save(new ChatRoom());
        }

        User fromUser = userRepository.findById(followRequest.getFromSeq())
            .orElseThrow(() -> new NoSuchElementException("NoUser"));
        User toUser = userRepository.findById(followRequest.getToSeq())
            .orElseThrow(() -> new NoSuchElementException("NoUser"));

        // 팔로잉 정보 저장
        followRepository.save(new Follow(null, fromUser, toUser, chatRoom));

        // 채팅 리스트 저장할 List 생성 및 방번호 key로 redis에 저장.
		ListOperations<String, DirectMessage> listOperations = redisTemplate.opsForList();

        // 팔로잉한 상대에게 팔로우 알림 보내기
        notificationRepository.save(Notification.builder()
            .registeredTime(LocalDateTime.now())
            .fromUser(fromUser)
            .toUser(toUser)
            .isRead((byte)1)
            .type("follow")
            .build());
    }

    @Override
    public void unFollowing(Long followSeq) {
        Follow follow = followRepository.findById(followSeq)
            .orElseThrow(() -> new NoSuchElementException("NoFollowing"));
        followRepository.delete(follow);
    }

    @Override
    public List<Follow> findFollowingList(Long seq) {
        List<Follow> list = followRepository.findAllByFromUser_Seq(seq);
        return list;
    }

    @Override
    public List<Follow> findFollowerList(Long seq) {
        List<Follow> list = followRepository.findAllByToUser_Seq(seq);
        return list;
    }

    // 출석체크시 20포인트 적립
    @Override
    public UserResponse updatePoint(Long seq, String point) {
        User user = userRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoUser"));
        user.updatePoint(Integer.parseInt(point));
        return new UserResponse(userRepository.save(user));
    }

    @Override
    public FollowResponse isFollowing(Long fromSeq, Long toSeq){
        Follow follow = followRepository.findFollowByFromUser_SeqAndToUser_Seq(fromSeq, toSeq);

        if(follow == null){
            return null;
        }else{
            return FollowResponse.fromEntity(follow);
        }
    }

    // 암호화 하는 함수
    private String encryption(String pass, int KEY_STRETCHING) throws NoSuchAlgorithmException {
        String hex = "";
        for (int i = 0; i < KEY_STRETCHING; i++) {
            MessageDigest msg = MessageDigest.getInstance("SHA-512");
            msg.update(pass.getBytes());
            hex = String.format("%128x", new BigInteger(1, msg.digest()));

            pass = hex;
        }
        return pass;
    }

    // salt 생성 함수
    private String createsalt(String salt) {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] bytes = new byte[16];
            random.nextBytes(bytes);
            salt = new String(Base64.getEncoder().encode(bytes));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return salt;
    }

}
