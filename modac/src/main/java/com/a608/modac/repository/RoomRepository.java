package com.a608.modac.repository;

import java.util.List;

import com.a608.modac.model.room.Room;
import com.a608.modac.model.room.RoomResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
