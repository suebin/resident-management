package com.nhnacademy.residentmanagement.entity;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 증명서 발급.
 */
@Getter
@Setter
@Entity
@Table(name = "certificate_issue")
public class CertificateIssue {
    @Id
    @Column(name = "certificate_confirmation_number")
    private Long certificateConfirmationNumber;
    @Column(name = "resident_serial_number")
    private int residentSerialNumber;
    @Column(name = "certificate_type_code")
    private String certificateTypeCode;
    @Column(name = "certificate_issue_date")
    private LocalDate certificateIssueDate;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number", insertable = false, updatable = false)
    private Resident resident;
}
