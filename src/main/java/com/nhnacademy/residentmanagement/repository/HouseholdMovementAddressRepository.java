package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.dto.HouseholdMovementAddressDto;
import com.nhnacademy.residentmanagement.entity.HouseholdMovementAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 세대 전입 주소 Repository.
 */
public interface HouseholdMovementAddressRepository
        extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {
    @Query("SELECT i.isLastAddress AS isLastAddress, i.houseMovementAddress "
            + "AS houseMovementAddress, i.pk.houseMovementReportDate AS houseMovementReportDate "
            + "FROM HouseholdMovementAddress i "
            + "WHERE i.pk.householdSerialNumber = :householdSerialNumber "
            + "ORDER BY i.pk.houseMovementReportDate DESC")
    List<HouseholdMovementAddressDto> getHouseholdMovementAddressDto(@Param("householdSerialNumber")
                                                                     int householdSerialNumber);
}
