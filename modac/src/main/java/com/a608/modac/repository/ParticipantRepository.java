package com.a608.modac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a608.modac.model.participant.Participant;
import com.a608.modac.model.participant.ParticipantPK;

public interface ParticipantRepository  extends JpaRepository<Participant, ParticipantPK> {
}
