package com.nhnacademy.residentmanagement.exception;

public class ResidentNotFoundException extends RuntimeException {
    public ResidentNotFoundException(int residentSerialNumber) {
        super("resident not found : " + residentSerialNumber);
    }
}
