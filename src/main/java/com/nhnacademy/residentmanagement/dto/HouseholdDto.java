package com.nhnacademy.residentmanagement.dto;

import java.time.LocalDate;

/**
 * 주민등록등본 조회에 필요한 세대 DTO.
 */
public interface HouseholdDto {
    String getHouseholdCompositionReasonCode(); // 세대구성 사유
    LocalDate getHouseholdCompositionDate(); // 세대구성 일자
}
