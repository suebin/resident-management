package com.nhnacademy.residentmanagement.domain;

import lombok.Data;

@Data
public class FamilyRelationshipCreateRequest {
    private int familySerialNumber;
    private String relationship;
}
