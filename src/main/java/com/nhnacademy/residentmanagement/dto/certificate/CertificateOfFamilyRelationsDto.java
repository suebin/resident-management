package com.nhnacademy.residentmanagement.dto.certificate;

import java.time.LocalDate;
import java.util.List;

import com.nhnacademy.residentmanagement.dto.FamilyOfResidentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 가족관계증명서 조회 DTO.
 */
@Getter
@AllArgsConstructor
public class CertificateOfFamilyRelationsDto {
    private LocalDate certificateIssueDate; // 증명서 발급일자
    private String preCertificateConfirmationNumber; // 증명서확인번호 앞 8자리
    private String postCertificateConfirmationNumber; // 증명서확인번호 뒤 8자리
    private String registrationBaseAddress; // 등록기준지 (본적)
    private List<FamilyOfResidentDto> familyOfResidentDtoList; // 가족 리스트
}
