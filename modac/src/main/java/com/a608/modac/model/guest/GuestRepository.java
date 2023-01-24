package com.a608.modac.model.guest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, GuestPK> {
	List<Guest> findGuestByGuestPK_UsersSeq(final Long userSeq);

}
