package com.a608.modac.repository;

import java.util.List;

import com.a608.modac.model.guest.Guest;
import com.a608.modac.model.guest.GuestPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, GuestPK> {
	List<Guest> findGuestsByGuestPK_User_Seq(final Long usersSeq);

}
