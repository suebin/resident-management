package com.nhnacademy.residentmanagement.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 세대.
 */
@Getter
@Setter
@Entity
@Table(name = "household")
public class Household {
    @Id
    @Column(name = "household_serial_number")
    private int householdSerialNumber;
    @Column(name = "household_resident_serial_number")
    private int householdResidentSerialNumber;
    @Column(name = "household_composition_date")
    private LocalDate householdCompositionDate;
    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;
    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;

    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number", insertable = false, updatable = false)
    private Resident resident;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<HouseholdCompositionResident> householdCompositionResidents;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<HouseholdMovementAddress> householdMovementAddresses;
}
