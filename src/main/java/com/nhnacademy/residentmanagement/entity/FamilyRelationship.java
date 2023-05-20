package com.nhnacademy.residentmanagement.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.nhnacademy.residentmanagement.domain.FamilyRelationshipCode;
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
        @ManyToOne
        @JoinColumn(name = "family_resident_serial_number")
        private Resident familyResident;

        @ManyToOne
        @JoinColumn(name = "base_resident_serial_number")
        private Resident baseResident;
    }
}
