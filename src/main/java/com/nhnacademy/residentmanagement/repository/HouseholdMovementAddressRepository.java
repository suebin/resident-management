package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 세대 전입 주소 Repository.
 */
public interface HouseholdMovementAddressRepository
        extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {
}
