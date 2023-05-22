package com.nhnacademy.residentmanagement.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHouseholdCompositionResident_Pk is a Querydsl query type for Pk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHouseholdCompositionResident_Pk extends BeanPath<HouseholdCompositionResident.Pk> {

    private static final long serialVersionUID = 1653614869L;

    public static final QHouseholdCompositionResident_Pk pk = new QHouseholdCompositionResident_Pk("pk");

    public final NumberPath<Integer> householdSerialNumber = createNumber("householdSerialNumber", Integer.class);

    public final NumberPath<Integer> residentSerialNumber = createNumber("residentSerialNumber", Integer.class);

    public QHouseholdCompositionResident_Pk(String variable) {
        super(HouseholdCompositionResident.Pk.class, forVariable(variable));
    }

    public QHouseholdCompositionResident_Pk(Path<? extends HouseholdCompositionResident.Pk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHouseholdCompositionResident_Pk(PathMetadata metadata) {
        super(HouseholdCompositionResident.Pk.class, metadata);
    }

}

