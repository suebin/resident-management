package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.dto.HouseholdOfResidentDto;

import java.util.List;

/**
 * 주민등록등본 조회 시 필요한 주민 세대 정보를 위한 Custom Repository Interface. (Querydsl)
 */
public interface HouseholdCompositionResidentRepositoryCustom {
    List<HouseholdOfResidentDto> getHouseholdOfResidentDtoList(int householdSerialNumber);
}
