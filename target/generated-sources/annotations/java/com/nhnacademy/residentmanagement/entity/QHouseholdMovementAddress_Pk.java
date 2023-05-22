package com.nhnacademy.residentmanagement.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHouseholdMovementAddress_Pk is a Querydsl query type for Pk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHouseholdMovementAddress_Pk extends BeanPath<HouseholdMovementAddress.Pk> {

    private static final long serialVersionUID = -614094582L;

    public static final QHouseholdMovementAddress_Pk pk = new QHouseholdMovementAddress_Pk("pk");

    public final NumberPath<Integer> householdSerialNumber = createNumber("householdSerialNumber", Integer.class);

    public final DatePath<java.time.LocalDate> houseMovementReportDate = createDate("houseMovementReportDate", java.time.LocalDate.class);

    public QHouseholdMovementAddress_Pk(String variable) {
        super(HouseholdMovementAddress.Pk.class, forVariable(variable));
    }

    public QHouseholdMovementAddress_Pk(Path<? extends HouseholdMovementAddress.Pk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHouseholdMovementAddress_Pk(PathMetadata metadata) {
        super(HouseholdMovementAddress.Pk.class, metadata);
    }

}

