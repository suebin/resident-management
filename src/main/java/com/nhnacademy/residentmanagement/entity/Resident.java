package com.nhnacademy.residentmanagement.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 주민.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resident")
public class Resident {
    @Id
    @Column(name = "resident_serial_number")
    private int residentSerialNumber;
    private String name;
    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber;
    @Column(name = "gender_code")
    private String genderCode;
    @Column(name = "birth_date")
    private LocalDateTime birthDate;
    @Column(name = "birth_place_code")
    private String birthPlaceCode;
    @Column(name = "registration_base_address")
    private String registrationBaseAddress;
    @Column(name = "death_date")
    private LocalDateTime deathDate;
    @Column(name = "death_place_code")
    private String deathPlaceCode;
    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    public Resident(int residentSerialNumber, String name, String residentRegistrationNumber, String genderCode, LocalDateTime birthDate, String birthPlaceCode, String registrationBaseAddress) {
        this.residentSerialNumber = residentSerialNumber;
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
        this.birthDate = birthDate;
        this.birthPlaceCode = birthPlaceCode;
        this.registrationBaseAddress = registrationBaseAddress;
    }

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private List<BirthDeathReportResident> birthDeathReportResidentList;

    @OneToMany(mappedBy = "reportResident", cascade = CascadeType.ALL)
    private List<BirthDeathReportResident> birthDeathReportResidentList2;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private List<CertificateIssue> certificateIssueList;

    @OneToMany(mappedBy = "pk.familyResident", cascade = CascadeType.ALL)
    private List<FamilyRelationship> familyRelationshipList;

    @OneToMany(mappedBy = "pk.baseResident", cascade = CascadeType.ALL)
    private List<FamilyRelationship> familyRelationshipList2;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private List<Household> householdList;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private List<HouseholdCompositionResident> householdCompositionResidentList;
}
