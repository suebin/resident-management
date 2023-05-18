package com.nhnacademy.residentmanagement.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

/**
 * 가족 관계.
 */
@Getter
@Setter
@Entity
@Table(name = "family_relationship")
public class FamilyRelationship {
    @EmbeddedId
    private Pk pk;

    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

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
        @Column(name = "family_resident_serial_number")
        private int familyResidentSerialNumber;
        @Column(name = "base_resident_serial_number")
        private int  baseResidentSerialNumber;
    }

    @MapsId("familyResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "family_resident_serial_number")
    private Resident familyResident;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number")
    private Resident baseResident;
}
