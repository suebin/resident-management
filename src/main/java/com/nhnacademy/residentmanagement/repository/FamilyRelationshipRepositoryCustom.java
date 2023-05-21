package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.dto.FamilyOfResidentDto;
import java.util.List;

/**
 * 가족관계증명서 조회 시 필요한 주민 가족 정보를 위한 Custom Repository Interface. (Querydsl)
 */
public interface FamilyRelationshipRepositoryCustom {
    List<FamilyOfResidentDto> getFamilyOfResidentDtoList(int residentSerialNumber);
}
