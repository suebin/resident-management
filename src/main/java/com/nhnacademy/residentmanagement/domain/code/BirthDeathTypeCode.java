package com.nhnacademy.residentmanagement.domain.code;

/**
 * 출생 및 사망 코드
 */
public enum BirthDeathTypeCode {
    BIRTH("출생"),
    DEATH("사망");

    private final String stringValue;

    BirthDeathTypeCode(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getValue() {
        return stringValue;
    }
}
