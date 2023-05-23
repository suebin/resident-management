package com.nhnacademy.residentmanagement.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResident is a Querydsl query type for Resident
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResident extends EntityPathBase<Resident> {

    private static final long serialVersionUID = -789173687L;

    public static final QResident resident = new QResident("resident");

    public final DateTimePath<java.time.LocalDateTime> birthDate = createDateTime("birthDate", java.time.LocalDateTime.class);

    public final ListPath<BirthDeathReportResident, QBirthDeathReportResident> birthDeathReportResidentList = this.<BirthDeathReportResident, QBirthDeathReportResident>createList("birthDeathReportResidentList", BirthDeathReportResident.class, QBirthDeathReportResident.class, PathInits.DIRECT2);

    public final ListPath<BirthDeathReportResident, QBirthDeathReportResident> birthDeathReportResidentList2 = this.<BirthDeathReportResident, QBirthDeathReportResident>createList("birthDeathReportResidentList2", BirthDeathReportResident.class, QBirthDeathReportResident.class, PathInits.DIRECT2);

    public final StringPath birthPlaceCode = createString("birthPlaceCode");

    public final ListPath<CertificateIssue, QCertificateIssue> certificateIssueList = this.<CertificateIssue, QCertificateIssue>createList("certificateIssueList", CertificateIssue.class, QCertificateIssue.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> deathDate = createDateTime("deathDate", java.time.LocalDateTime.class);

    public final StringPath deathPlaceAddress = createString("deathPlaceAddress");

    public final StringPath deathPlaceCode = createString("deathPlaceCode");

    public final ListPath<FamilyRelationship, QFamilyRelationship> familyRelationshipList = this.<FamilyRelationship, QFamilyRelationship>createList("familyRelationshipList", FamilyRelationship.class, QFamilyRelationship.class, PathInits.DIRECT2);

    public final ListPath<FamilyRelationship, QFamilyRelationship> familyRelationshipList2 = this.<FamilyRelationship, QFamilyRelationship>createList("familyRelationshipList2", FamilyRelationship.class, QFamilyRelationship.class, PathInits.DIRECT2);

    public final StringPath genderCode = createString("genderCode");

    public final ListPath<HouseholdCompositionResident, QHouseholdCompositionResident> householdCompositionResidentList = this.<HouseholdCompositionResident, QHouseholdCompositionResident>createList("householdCompositionResidentList", HouseholdCompositionResident.class, QHouseholdCompositionResident.class, PathInits.DIRECT2);

    public final ListPath<Household, QHousehold> householdList = this.<Household, QHousehold>createList("householdList", Household.class, QHousehold.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath registrationBaseAddress = createString("registrationBaseAddress");

    public final StringPath residentRegistrationNumber = createString("residentRegistrationNumber");

    public final NumberPath<Integer> residentSerialNumber = createNumber("residentSerialNumber", Integer.class);

    public QResident(String variable) {
        super(Resident.class, forVariable(variable));
    }

    public QResident(Path<? extends Resident> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResident(PathMetadata metadata) {
        super(Resident.class, metadata);
    }

}

