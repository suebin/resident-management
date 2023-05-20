package com.nhnacademy.residentmanagement.exception;

public class HouseholdNotFoundException extends RuntimeException {
    public HouseholdNotFoundException(int householdSerialNumber) {
        super("household Not Found :"+ householdSerialNumber);
    }
}
