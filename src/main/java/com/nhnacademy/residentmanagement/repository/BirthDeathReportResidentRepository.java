package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 출생 및 사망 신고 Repository.
 */
public interface BirthDeathReportResidentRepository
        extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
    @Query("SELECT i FROM BirthDeathReportResident i "
            + "WHERE i.pk.residentSerialNumber = :residentSerialNumber "
            + "AND i.pk.birthDeathTypeCode = :birthDeathTypeCode")
    BirthDeathReportResident findBySerialNumberAndTypeCode(@Param("residentSerialNumber") int residentSerialNumber,
                                                           @Param("birthDeathTypeCode") String birthDeathTypeCode);

    @Query("SELECT i FROM BirthDeathReportResident i "
            + "WHERE i.pk.residentSerialNumber = ?1 AND i.pk.birthDeathTypeCode = ?2")
    BirthDeathReportResident findReportResident(@Param("residentSerialNumber") int baseResidentSerialNumber,
                                                @Param("birthDeathTypeCode") String birthDeathTypeCode);
}
