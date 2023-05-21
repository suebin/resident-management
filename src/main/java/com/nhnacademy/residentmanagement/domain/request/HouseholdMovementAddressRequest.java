package com.nhnacademy.residentmanagement.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HouseholdMovementAddressRequest {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate houseMovementReportDate;
    private String houseMovementAddress;
    private String isLastAddress;
}
