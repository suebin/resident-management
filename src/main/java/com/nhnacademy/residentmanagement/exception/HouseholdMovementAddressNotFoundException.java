package com.nhnacademy.residentmanagement.exception;

public class HouseholdMovementAddressNotFoundException extends RuntimeException {
    public HouseholdMovementAddressNotFoundException() {
        super("Household Movement Address Not Found!");
    }
}
