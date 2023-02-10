package com.a608.modac.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.a608.modac.model.chat.DirectMessage;
@Repository
public interface ChatDirectRepository extends CrudRepository<DirectMessage, String> {
	List<DirectMessage> findDirectMessagesByChatRoomsSeq(final String chatRoomsSeq, final Pageable pageable);
}
