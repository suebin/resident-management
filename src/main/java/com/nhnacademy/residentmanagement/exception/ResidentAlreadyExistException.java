package com.nhnacademy.residentmanagement.exception;

import javax.validation.constraints.NotBlank;

public class ResidentAlreadyExistException extends RuntimeException {
    public ResidentAlreadyExistException(@NotBlank int residentSerialNumber) {
        super("resident already exists : " + residentSerialNumber);
    }
}
