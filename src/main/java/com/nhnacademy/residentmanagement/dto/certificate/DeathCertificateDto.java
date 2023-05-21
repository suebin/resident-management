package com.nhnacademy.residentmanagement.dto.certificate;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 사망신고서 조회 DTO.
 */
@Getter
@AllArgsConstructor
public class DeathCertificateDto {
    private String name; // 성명
    private String residentRegistrationNumber; // 주민등록번호
    private LocalDateTime deathDate; // 사망일시
    private String deathPlaceCode; // 사망장소 구분
    private String deathPlaceAddress; // 사망장소 주소
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
