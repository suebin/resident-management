package com.nhnacademy.residentmanagement.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 주민등록등본 조회 DTO.
 */
@Getter
@AllArgsConstructor
public class ResidentRegisterDto {
    private LocalDate certificateIssueDate; // 증명서 발급일자
    private String preCertificateConfirmationNumber; // 증명서확인번호 앞 8자리
    private String postCertificateConfirmationNumber; // 증명서확인번호 뒤 8자리
    private String householdName; // 세대주 성명
    private HouseholdDto householdDto; // 세대구성 사유 및 일자
    private List<HouseholdMovementAddressDto> householdMovementAddressDtoList; // 세대전입주소 리스트 (최종주소여부, 주소, 신고일)
    private List<HouseholdOfResidentDto> householdOfResidentDtoList; // 세대구성주민 리스트
}
