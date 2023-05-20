package com.nhnacademy.residentmanagement.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * 세대 전입 주소.
 */
@Getter
@Setter
@Entity
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {
    @EmbeddedId
    private Pk pk;
    @Column(name = "house_movement_address")
    private String houseMovementAddress;
    @Column(name = "last_address_yn")
    private String isLastAddress;

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
        @Column(name = "house_movement_report_date")
        private LocalDate houseMovementReportDate;
        @Column(name = "household_serial_number")
        private int householdSerialNumber;
    }

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;
}
