package com.nhnacademy.residentmanagement.service;

import com.nhnacademy.residentmanagement.domain.code.CertificateTypeCode;
import com.nhnacademy.residentmanagement.domain.code.FamilyRelationshipCode;
import com.nhnacademy.residentmanagement.dto.CertificateIssueDto;
import com.nhnacademy.residentmanagement.dto.CertificateOfFamilyRelationsDto;
import com.nhnacademy.residentmanagement.dto.FamilyOfResidentDto;
import com.nhnacademy.residentmanagement.entity.CertificateIssue;
import com.nhnacademy.residentmanagement.entity.FamilyRelationship;
import com.nhnacademy.residentmanagement.entity.Resident;
import com.nhnacademy.residentmanagement.exception.ResidentNotFoundException;
import com.nhnacademy.residentmanagement.repository.CertificateIssueRepository;
import com.nhnacademy.residentmanagement.repository.FamilyRelationshipRepository;
import com.nhnacademy.residentmanagement.repository.ResidentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 증명서 발급 및 조회 서비스.
 */
@Slf4j
@Service
@AllArgsConstructor
public class CertificateIssueService {
    private final CertificateIssueRepository certificateIssueRepository;
    private final ResidentRepository residentRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final Random random = new Random();

    /**
     * 가족관계증명서 발급 서비스.
     *
     * @param residentSerialNumber 주민일련번호
     * @return 증명서확인번호
     */
    @Transactional
    public Long registerCertificateOfFamilyRelationsIssue(int residentSerialNumber) {
        Resident resident = residentRepository.findById(residentSerialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(residentSerialNumber));

        CertificateIssue certificateIssue = new CertificateIssue();
        certificateIssue.setCertificateConfirmationNumber(generateCertificateConfirmationNumber());
        certificateIssue.setResident(resident);
        certificateIssue.setResidentSerialNumber(resident.getResidentSerialNumber());
        certificateIssue.setCertificateTypeCode(
                CertificateTypeCode.CERTIFICATE_OF_FAMILY_RELATIONS.getValue());
        certificateIssue.setCertificateIssueDate(LocalDate.now());
        certificateIssueRepository.saveAndFlush(certificateIssue);
        return certificateIssue.getCertificateConfirmationNumber();
    }

    /**
     * 가족관계증명서 조회 서비스.
     *
     * @param residentSerialNumber            주민일련번호
     * @param certificationConfirmationNumber 증명서확인번호
     * @return 가족관계증명서에 필요한 데이터를 담은 CertificateOfFamilyRelationsDto
     */
    @Transactional
    public CertificateOfFamilyRelationsDto getCertificateOfFamilyRelations(
            int residentSerialNumber, Long certificationConfirmationNumber) {
        CertificateIssueDto certificateIssueDto =
                certificateIssueRepository.queryByCertificateConfirmationNumber(certificationConfirmationNumber);

        Resident resident = residentRepository.findById(residentSerialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(residentSerialNumber));

        List<FamilyOfResidentDto> familyOfResidentDtoList
                = familyRelationshipRepository.getFamilyOfResidentDtoList(residentSerialNumber);

        FamilyOfResidentDto residentDto = new FamilyOfResidentDto(
                FamilyRelationshipCode.ME.getValue(),
                resident.getName(),
                resident.getBirthDate(),
                resident.getResidentRegistrationNumber(),
                resident.getGenderCode());
        familyOfResidentDtoList.add(0, residentDto);


        return new CertificateOfFamilyRelationsDto(
                certificateIssueDto.getCertificateIssueDate(),
                String.valueOf(certificateIssueDto.getCertificateConfirmationNumber()).substring(0, 8),
                String.valueOf(certificateIssueDto.getCertificateConfirmationNumber()).substring(8),
                resident.getRegistrationBaseAddress(), familyOfResidentDtoList);
    }

    /**
     * 증명서 발급 시 생성되는 16자리 증명서확인번호 간이 generator. (증명서확인번호는 고유한 랜덤 값이라고 가정)
     *
     * @return 증명서확인번호
     */
    @Transactional
    public Long generateCertificateConfirmationNumber() {
        long min = (long) Math.pow(10, 15);
        long max = (long) Math.pow(10, 16) - 1;

        Long certificateConfirmationNumber = Math.abs(random.nextLong() % (max - min + 1) + min);
        if (certificateIssueRepository.existsById(certificateConfirmationNumber)) {
            generateCertificateConfirmationNumber();
        }
        return certificateConfirmationNumber;
    }
}
