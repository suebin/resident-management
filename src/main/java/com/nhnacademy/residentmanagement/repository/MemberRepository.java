package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Member Repository.
 */
public interface MemberRepository extends JpaRepository<Member, String> {
}
