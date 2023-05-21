package com.nhnacademy.residentmanagement.repository;

import com.nhnacademy.residentmanagement.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 가족관계 Repository.
 */
public interface FamilyRelationshipRepository
        extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk>,
        FamilyRelationshipRepositoryCustom {
    @Modifying
    @Query("update FamilyRelationship i set i.familyRelationshipCode = :familyRelationshipCode "
            + "where i.pk.baseResident.residentSerialNumber = :serialNumber "
            + "and i.pk.familyResident.residentSerialNumber = :familySerialNumber")
    void updateFamilyRelationshipCode(@Param("familyRelationshipCode") String familyRelationshipCode,
                                      @Param("serialNumber") int serialNumber,
                                      @Param("familySerialNumber") int familySerialNumber);

    @Modifying
    @Query("delete from FamilyRelationship i where i.pk.baseResident.residentSerialNumber = :serialNumber "
            + "and i.pk.familyResident.residentSerialNumber = :familySerialNumber")
    void deleteFamilyRelationship(@Param("serialNumber") int serialNumber,
                                  @Param("familySerialNumber") int familySerialNumber);

}
