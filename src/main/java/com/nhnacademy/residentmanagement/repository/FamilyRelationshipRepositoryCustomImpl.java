package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.dto.FamilyOfResidentDto;
import com.nhnacademy.residentmanagement.entity.FamilyRelationship;
import com.nhnacademy.residentmanagement.entity.QFamilyRelationship;
import com.nhnacademy.residentmanagement.entity.QResident;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * FamilyRelationshipRepositoryCustom Interface 구현. (Querydsl)
 */
public class FamilyRelationshipRepositoryCustomImpl extends QuerydslRepositorySupport
        implements FamilyRelationshipRepositoryCustom {
    public FamilyRelationshipRepositoryCustomImpl() {
        super(FamilyRelationship.class);
    }

    @Override
    public List<FamilyOfResidentDto> getFamilyOfResidentDtoList(int residentSerialNumber) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
        QResident resident = QResident.resident;

        return from(familyRelationship)
                .innerJoin(familyRelationship.pk.familyResident, resident)
                .where(familyRelationship.pk.baseResident.residentSerialNumber
                        .eq(residentSerialNumber))
                .select(Projections.constructor(FamilyOfResidentDto.class,
                        familyRelationship.familyRelationshipCode,
                        resident.name,
                        resident.birthDate,
                        resident.residentRegistrationNumber,
                        resident.genderCode))
                .fetch();
    }
}
