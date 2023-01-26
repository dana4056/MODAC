package com.a608.modac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a608.modac.model.membership.Membership;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, String> {
}
