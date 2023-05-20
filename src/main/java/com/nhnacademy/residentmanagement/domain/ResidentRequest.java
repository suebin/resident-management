package com.nhnacademy.residentmanagement.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResidentRequest {
    private int residentSerialNumber;
    private String name;
    private String residentRegistrationNumber;
    private String genderCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthDate;
    private String birthPlaceCode;
    private String registrationBaseAddress;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;
}
