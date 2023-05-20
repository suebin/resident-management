package com.nhnacademy.residentmanagement.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HouseholdCreateRequest {
    private int householdSerialNumber;
    private int householdResidentSerialNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;
}
