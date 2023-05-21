package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.dto.HouseholdDto;
import com.nhnacademy.residentmanagement.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 세대 Repository.
 */
public interface HouseholdRepository extends JpaRepository<Household, Integer> {
    @Modifying
    @Query("delete from Household i where i.householdSerialNumber = :householdSerialNumber")
    void deleteById(@Param("householdSerialNumber") int householdSerialNumber);

    HouseholdDto queryByHouseholdSerialNumber(@Param("householdSerialNumber")
                                              int householdSerialNumber);
}
