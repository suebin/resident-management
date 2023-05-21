package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.dto.HouseholdOfResidentDto;
import com.nhnacademy.residentmanagement.entity.HouseholdCompositionResident;
import com.nhnacademy.residentmanagement.entity.QHouseholdCompositionResident;
import com.nhnacademy.residentmanagement.entity.QResident;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * HouseholdCompositionResidentRepositoryCustom Interface 구현. (Querydsl)
 */
public class HouseholdCompositionResidentRepositoryCustomImpl extends QuerydslRepositorySupport
        implements HouseholdCompositionResidentRepositoryCustom{
    public HouseholdCompositionResidentRepositoryCustomImpl() {
        super(HouseholdCompositionResident.class);
    }

    @Override
    public List<HouseholdOfResidentDto> getHouseholdOfResidentDtoList(int householdSerialNumber) {
        QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;
        QResident resident = QResident.resident;

        return from(householdCompositionResident)
                .innerJoin(householdCompositionResident.resident, resident)
                .where(householdCompositionResident.pk.householdSerialNumber.eq(householdSerialNumber))
                .select(Projections.constructor(
                        HouseholdOfResidentDto.class,
                        householdCompositionResident.householdRelationshipCode,
                        resident.name,
                        resident.residentRegistrationNumber,
                        householdCompositionResident.reportDate,
                        householdCompositionResident.householdCompositionChangeReasonCode))
                .orderBy(householdCompositionResident.reportDate.asc())
                .fetch();
    }
}
