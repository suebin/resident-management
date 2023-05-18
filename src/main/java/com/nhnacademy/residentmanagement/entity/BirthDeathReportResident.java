package com.nhnacademy.residentmanagement.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

/**
 * 출생 사망 신고 주민.
 */
@Getter
@Setter
@Entity
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {
    @EmbeddedId
    private Pk pk;
    @Column(name = "birth_death_report_date")
    private LocalDate birthDeathReportDate;
    @Column(name = "birth_report_qualifications_code")
    private String birthReportQualificationsCode;
    @Column(name = "death_report_qualifications_code")
    private String deathReportQualificationsCode;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Primary Key.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "birth_death_type_code")
        private String birthDeathTypeCode;
        @Column(name = "resident_serial_number")
        private int residentSerialNumber;
        @Column(name = "report_resident_serial_number")
        private int reportResidentSerialNumber;
    }

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @MapsId("reportResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident;
}
