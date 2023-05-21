package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.dto.CertificateIssueDto;
import com.nhnacademy.residentmanagement.entity.CertificateIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 증명서 발급 Repository.
 */
public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    CertificateIssueDto queryByCertificateConfirmationNumber(Long certificationConfirmationNumber);

    @Query("SELECT i FROM CertificateIssue i WHERE i.residentSerialNumber = ?1")
    Page<CertificateIssue> getCertificateIssue(@Param("residentSerialNumber")
                                                int residentSerialNumber,
                                               Pageable pageable);
}
