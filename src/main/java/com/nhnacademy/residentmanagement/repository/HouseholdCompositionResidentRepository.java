package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 세대 구성 주민 Repository.
 */
public interface HouseholdCompositionResidentRepository
        extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.Pk>,
        HouseholdCompositionResidentRepositoryCustom {
    @Query("select i from HouseholdCompositionResident i "
            + "where i.pk.residentSerialNumber = :residentSerialNumber")
    HouseholdCompositionResident findByResidentSerialNumber(@Param("residentSerialNumber")
                                                            int residentSerialNumber);
}
