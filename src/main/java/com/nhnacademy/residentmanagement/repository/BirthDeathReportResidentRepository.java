package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 출생 및 사망 신고 Repository.
 */
public interface BirthDeathReportResidentRepository
        extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
}
