package com.nhnacademy.residentmanagement.exception;

public class BirthDeathReportResidentNotFoundException extends RuntimeException {
    public BirthDeathReportResidentNotFoundException() {
        super("Birth Death Report Resident Not Found!");
    }
}
