package com.nhnacademy.residentmanagement.domain.code;

/**
 * 주민관리 문서 코드.
 */
public enum CertificateTypeCode {
    CERTIFICATE_OF_FAMILY_RELATIONS("가족관계증명서"),
    RESIDENT_REGISTER("주민등록등본"),
    BIRTH_CERTIFICATE("출생신고서"),
    DEATH_CERTIFICATE("사망신고서");

    private final String stringValue;

    CertificateTypeCode(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getValue() {
        return stringValue;
    }
}
