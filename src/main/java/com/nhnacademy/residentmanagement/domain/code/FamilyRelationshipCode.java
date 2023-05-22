package com.nhnacademy.residentmanagement.domain.code;

import lombok.Getter;

/**
 * 가족 관계 코드.
 */
@Getter
public enum FamilyRelationshipCode {
    ME("me", "본인"),
    FATHER("father", "부"),
    MOTHER("mother", "모"),
    SPOUSE("spouse", "배우자"),
    CHILD("child", "자녀");

    private final String code;
    private final String value;

    FamilyRelationshipCode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValue(String code) {
        for (FamilyRelationshipCode familyRelationshipCode : FamilyRelationshipCode.values()) {
            if (familyRelationshipCode.getCode().equals(code)) {
                return familyRelationshipCode.getValue();
            }
        }
        return null;
    }

}
