package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 주민 Repository.
 */
public interface ResidentRepository extends JpaRepository<Resident, Integer> {
}
