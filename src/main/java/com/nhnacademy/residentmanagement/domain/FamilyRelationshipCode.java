package com.nhnacademy.residentmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FamilyRelationshipCode {
    FATHER("father", "부"),
    MOTHER("mother", "모"),
    SPOUSE("spouse", "배우자"),
    CHILD("child", "자녀");

    private final String code;
    private final String label;
}
