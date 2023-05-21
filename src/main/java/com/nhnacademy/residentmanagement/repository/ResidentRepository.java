package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.dto.ResidentDto;
import com.nhnacademy.residentmanagement.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * 주민 Repository.
 */
public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    ResidentDto queryByResidentSerialNumber(@Param("residentSerialNumber")
                                            int residentSerialNumber);
}
