package com.nhnacademy.residentmanagement.dto;

import java.time.LocalDate;

/**
 * 증명서 조회 시 필요한 증명서 발급 DTO.
 */
public interface CertificateIssueDto {
    Long getCertificateConfirmationNumber();
    LocalDate getCertificateIssueDate();
}
