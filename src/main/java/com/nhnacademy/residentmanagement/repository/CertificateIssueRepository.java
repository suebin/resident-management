package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.dto.CertificateIssueDto;
import com.nhnacademy.residentmanagement.entity.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 증명서 발급 Repository.
 */
public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    CertificateIssueDto queryByCertificateConfirmationNumber(Long certificationConfirmationNumber);
}
