package com.nhnacademy.residentmanagement.dto.certificate;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 출생신고서 조회 DTO.
 */
@Getter
@AllArgsConstructor
public class BirthCertificateDto {
    private String name; // 성명
    private String genderCode; // 성별
    private LocalDateTime birthDate; // 출생일시
    private String birthPlaceCode; // 출생장소
    private String registrationBaseAddress; // 등록기준지 (본적)
    private String fatherName; // 부 성명
    private String fatherResidentRegistrationNumber; // 부 주민등록번호
    private String motherName; // 모 성명
    private String motherResidentRegistrationNumber; // 모 주민등록번호
    private String reporterName; // 신고인 성명
    private String reporterResidentRegistrationNumber; // 신고인 주민등록번호
    private String reporterQualificationsCode; // 신고인 자격
    private String reporterEmailAddress; // 신고인 이메일
    private String reporterPhoneNumber; // 신고인 전화번호

    // 신고일
    private String reportYear;
    private String reportMonth;
    private String reportDay;
}
