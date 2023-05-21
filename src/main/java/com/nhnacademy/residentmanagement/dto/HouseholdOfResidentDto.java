package com.nhnacademy.residentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 주민등록등본 조회 시 필요한 주민 세대 정보 DTO.
 */
@Getter
@AllArgsConstructor
public class HouseholdOfResidentDto {
    private String householdRelationshipCode; // 세대주 관계
    private String name; // 성명
    private String residentRegistrationNumber; // 주민등록번호
    private LocalDate reportDate; // 신고일
    private String householdCompositionChangeReasonCode; // 변동사유
}
