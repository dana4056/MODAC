package com.a608.modac.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.a608.modac.model.chat.ChatRoom;
import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.follow.FollowRequest;
import com.a608.modac.model.user.User;
import com.a608.modac.model.user.UserRequest;
import com.a608.modac.model.user.UserResponse;
import com.a608.modac.repository.ChatRoomRepository;
import com.a608.modac.repository.FollowRepository;
import com.a608.modac.repository.UserRepository;
import com.a608.modac.security.JwtTokenProvider;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UserRepository userRepository, FollowRepository followRepository, ChatRoomRepository chatRoomRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) { // 회원 저장
        // 고양이 스킨 번호 랜덤 (1~12)
        double min = 1;
        double max = 12;
        byte skin = (byte) ((Math.random() * (max - min)) + min);

        User save = userRepository.save(userRequest.toEntity(skin));
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
    public void updatePassword(Long seq, String password) {
        User findUser = userRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoUser"));
        findUser.updatePassword(password);
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

    @Override
    public String findIdByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new NoSuchElementException("NoUser"));
        return user.getId();
    }

    @Override
    public String findPasswordByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new NoSuchElementException("NoUser"));
        return user.getPassword();
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
    public UserResponse login(UserRequest userRequest) {
        User user = userRepository.findUserById(userRequest.getId()).orElseThrow(() -> new NoSuchElementException("NoUser"));

        if(user.getPassword().equals(userRequest.getPassword())){
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
        Follow follower = followRepository.findFollowByFromUser_SeqAndToUser_Seq(followRequest.getToSeq(), followRequest.getFromSeq());
        ChatRoom chatRoom;

        if(follower != null){ // 이미 쟤가 날 팔로우 함 (DM 방 이미 존재)
            chatRoom = follower.getChatRoom();
        }else{  // 상대방은 날 팔로우하고 있지 않음 (새로운 관계)
            chatRoom = chatRoomRepository.save(new ChatRoom());
        }

        User fromUser = userRepository.findById(followRequest.getFromSeq()).orElseThrow(() -> new NoSuchElementException("NoUser"));
        User toUser = userRepository.findById(followRequest.getToSeq()).orElseThrow(() -> new NoSuchElementException("NoUser"));

        followRepository.save(new Follow(null, fromUser, toUser, chatRoom));
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
    public boolean isFollowing(Long fromSeq, Long toSeq){
        Follow follow = followRepository.findFollowByFromUser_SeqAndToUser_Seq(fromSeq, toSeq);
        return follow != null;
    }
}
