package com.nhnacademy.residentmanagement.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 가족관계증명서 조회 시 필요한 주민 가족 정보 DTO.
 */
@Getter
@AllArgsConstructor
public class FamilyOfResidentDto {
    String familyRelationshipCode;

    String name;

    LocalDateTime birthDate;

    String residentRegistrationNumber;

    String genderCode;
}
