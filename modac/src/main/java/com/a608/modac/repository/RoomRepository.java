package com.a608.modac.repository;

import java.util.List;

import com.a608.modac.model.room.Room;
import com.a608.modac.model.room.RoomResponse;
import com.a608.modac.model.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findAllByTitleContaining(String keyword);
	List<Room> findAllByDescriptionContaining(String keyword);
	List<Room> findAllByHost(User host);
}
