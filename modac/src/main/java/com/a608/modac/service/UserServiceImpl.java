package com.a608.modac.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.a608.modac.model.chatting.ChatRoom;
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
        User user = userRepository.findUserByEmail(email);
        return user.getId();
    }

    @Override
    public String findPasswordByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user.getPassword();
    }

    @Override
    public boolean checkAvailableNick(String nick) {
        User user = userRepository.findUserByNickname(nick);
        return user == null ? true : false;
    }

    @Override
    public boolean checkAvailableId(String id) {
        User user = userRepository.findUserById(id);
        return user == null ? true : false;
    }

    @Override
    public boolean checkAvailableEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user == null ? true : false;
    }

    @Override
    public String login(UserRequest userRequest) {
        // User user = userRepository.findUserById(userRequest.getId());
        User user = userRepository.findById(userRequest.getId()).orElseThrow(() -> new NoSuchElementException("NoUser"));

        if(user.getPassword().equals(userRequest.getPassword())){
           //로그인 성공
            String token = jwtTokenProvider.createToken(user);
            return token;
        }else{
            System.out.println("[로그인 실패] - 비밀번호 불일치");
            return "false";
        }
        // if(user != null){
        //     return user.getPassword().equals(userRequest.getPassword());
        // }else {
        //     return false;
        // }
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
    public void unFollowing(FollowRequest followRequest) {
        Follow following = followRepository.findFollowByFromUser_SeqAndToUser_Seq(followRequest.getFromSeq(), followRequest.getToSeq());
        followRepository.findById(following.getSeq()).orElseThrow(() -> new NoSuchElementException("NoFollowing"));
        followRepository.delete(following);
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
    public void updatePoint(Long seq, String point) {
        User user = userRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoUser"));
        user.updatePoint(Integer.parseInt(point));
        userRepository.save(user);
    }
}
