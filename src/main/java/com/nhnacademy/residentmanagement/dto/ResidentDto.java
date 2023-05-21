package com.nhnacademy.residentmanagement.dto;

import java.time.LocalDateTime;

/**
 * 주민 DTO.
 */
public interface ResidentDto {
    int getResidentSerialNumber();

    String getName();

    String getResidentRegistrationNumber();

    String getGenderCode();

    LocalDateTime getBirthDate();

    String getBirthPlaceCode();

    String getRegistrationBaseAddress();

    LocalDateTime getDeathDate();

    String getDeathPlaceCode();

    String getDeathPlaceAddress();
}
