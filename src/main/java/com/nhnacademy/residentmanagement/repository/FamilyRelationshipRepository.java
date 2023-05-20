package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {
    @Modifying
    @Query("update FamilyRelationship i set i.familyRelationshipCode = :familyRelationshipCode where i.pk.baseResident.residentSerialNumber = :serialNumber and i.pk.familyResident.residentSerialNumber = :familySerialNumber")
    int updateFamilyRelationshipCode(@Param("familyRelationshipCode") String familyRelationshipCode,
                                     @Param("serialNumber") int serialNumber,
                                     @Param("familySerialNumber") int familySerialNumber);

    @Modifying
    @Query("delete from FamilyRelationship i where i.pk.baseResident.residentSerialNumber = :serialNumber and i.pk.familyResident.residentSerialNumber = :familySerialNumber")
    int deleteFamilyRelationshipCode(@Param("serialNumber") int serialNumber,
                                     @Param("familySerialNumber") int familySerialNumber);
}