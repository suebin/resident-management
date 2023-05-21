package com.nhnacademy.residentmanagement.dto;

import java.time.LocalDate;

/**
 * 주민등록등본 조회에 필요한 세대 전입 주소 DTO.
 */
public interface HouseholdMovementAddressDto {
    LocalDate getHouseMovementReportDate();
    String getHouseMovementAddress();
    String getIsLastAddress();
}
