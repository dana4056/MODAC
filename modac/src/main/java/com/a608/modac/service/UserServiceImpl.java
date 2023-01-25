package com.a608.modac.service;

import com.a608.modac.model.chatting.ChatRoom;
import com.a608.modac.repository.ChatRoomRepository;
import com.a608.modac.model.follow.Follow;
import com.a608.modac.repository.FollowRepository;
import com.a608.modac.model.follow.FollowRequest;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.UserRepository;
import com.a608.modac.model.user.UserRequest;
import com.a608.modac.model.user.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ChatRoomRepository chatRoomRepository;

    public UserServiceImpl(UserRepository userRepository, FollowRepository followRepository, ChatRoomRepository chatRoomRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public void saveUser(UserRequest userRequest) { // 회원 저장
        userRepository.save(userRequest.toEntity());

    }

    @Override
    public void updateUser(Long seq, UserRequest userRequest) {
        User findUser =  userRepository.findUserBySeq(seq);
        findUser.updateUser(userRequest.getNickname(),userRequest.getEmail());

        userRepository.save(findUser);
    }


    @Override
    public void updatePassword(Long seq, String password) {
        User findUser = userRepository.findUserBySeq(seq);
        findUser.updatePassword(password);

        userRepository.save(findUser);
    }

    @Override
    public void deleteUser(Long seq) {
        userRepository.deleteById(seq);
    }

    @Override
    public UserResponse findUserBySeq(Long seq) {
        UserResponse userResponse = new UserResponse(userRepository.findUserBySeq(seq));
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
    public boolean login(UserRequest userRequest) {
        User user = userRepository.findUserById(userRequest.getId());
        if(user != null){
            return user.getPassword().equals(userRequest.getPassword()) ? true : false;
        }else {
            return false;
        }
    }

    @Override
    public void following(FollowRequest followRequest) {

        // 상대가 날 팔로우하는지 찾기
        Follow follower = followRepository.findFollowByFromSeqAndToSeq(followRequest.getToSeq(), followRequest.getFromSeq());

        Long chatRoomSeq;
        if(follower != null){ // 이미 쟤가 날 팔로우 함 (DM 방 이미 존재)
            chatRoomSeq = follower.getChatRoomSeq();
        }else{  // 상대방은 날 팔로우하고 있지 않음 (새로운 관계)
            chatRoomSeq = chatRoomRepository.save(new ChatRoom()).getSeq();
        }
        followRepository.save(new Follow(null, followRequest.getFromSeq(), followRequest.getToSeq(), chatRoomSeq));
    }

    @Override
    public void unFollowing(FollowRequest followRequest) {
        Follow following = followRepository.findFollowByFromSeqAndToSeq(followRequest.getFromSeq(), followRequest.getToSeq());
        followRepository.delete(following);
    }

    @Override
    public List<Follow> findFollowingList(Long seq) {
        List<Follow> list = followRepository.findAllByFromSeq(seq);
        return list;
    }

    @Override
    public List<Follow> findFollowerList(Long seq) {
        List<Follow> list = followRepository.findAllByToSeq(seq);
        return list;
    }
}
