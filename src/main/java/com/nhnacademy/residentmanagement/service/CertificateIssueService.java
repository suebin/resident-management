package com.nhnacademy.residentmanagement.service;

import com.nhnacademy.residentmanagement.domain.code.BirthDeathTypeCode;
import com.nhnacademy.residentmanagement.domain.code.CertificateTypeCode;
import com.nhnacademy.residentmanagement.domain.code.FamilyRelationshipCode;
import com.nhnacademy.residentmanagement.dto.*;
import com.nhnacademy.residentmanagement.dto.certificate.*;
import com.nhnacademy.residentmanagement.entity.*;
import com.nhnacademy.residentmanagement.exception.ResidentNotFoundException;
import com.nhnacademy.residentmanagement.repository.*;

import java.time.LocalDate;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 주민관리 문서 발급 및 조회 서비스. (가족관계증명서, 주민등록등본, 출생신고서, 사망신고서)
 */
@Slf4j
@Service
@AllArgsConstructor
public class CertificateIssueService {
    private final CertificateIssueRepository certificateIssueRepository;
    private final ResidentRepository residentRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final Random random = new Random();

    /**
     * 가족관계증명서 발급 서비스.
     *
     * @param residentSerialNumber 주민일련번호
     * @return 증명서확인번호
     */
    @Transactional
    public Long issueRegisterCertificateOfFamilyRelations(int residentSerialNumber) {
        return issueCertificate(residentSerialNumber,
                CertificateTypeCode.CERTIFICATE_OF_FAMILY_RELATIONS);
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
                certificateIssueRepository
                        .queryByCertificateConfirmationNumber(certificationConfirmationNumber);

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
                String.valueOf(certificateIssueDto.getCertificateConfirmationNumber())
                        .substring(0, 8),
                String.valueOf(certificateIssueDto.getCertificateConfirmationNumber())
                        .substring(8),
                resident.getRegistrationBaseAddress(),
                familyOfResidentDtoList);
    }

    /**
     * 주민등록등본 발급 서비스.
     *
     * @param residentSerialNumber 주민일련번호
     * @return 증명서확인번호
     */
    @Transactional
    public Long issueResidentRegister(int residentSerialNumber) {
        return issueCertificate(residentSerialNumber, CertificateTypeCode.RESIDENT_REGISTER);
    }

    /**
     * 주민등록등본 조회 서비스
     *
     * @param residentSerialNumber 주민일련번호
     * @param certificationConfirmationNumber 증명서확인번호
     * @return 주민등록등본에 필요한 데이터를 담은 ResidentRegisterDto
     */
    @Transactional
    public ResidentRegisterDto getResidentRegister(int residentSerialNumber,
                                                   Long certificationConfirmationNumber) {
        CertificateIssueDto certificateIssueDto = certificateIssueRepository
                .queryByCertificateConfirmationNumber(certificationConfirmationNumber);

        HouseholdCompositionResident resident = householdCompositionResidentRepository
                .findByResidentSerialNumber(residentSerialNumber);
        if (resident == null) {
            throw new ResidentNotFoundException(residentSerialNumber);
        }
        int householdSerialNumber = resident.getPk().getHouseholdSerialNumber();
        HouseholdDto householdDto = householdRepository
                .queryByHouseholdSerialNumber(householdSerialNumber);
        List<HouseholdMovementAddressDto> householdMovementAddressDtoList = householdMovementAddressRepository
                .getHouseholdMovementAddressDto(householdSerialNumber);
        List<HouseholdOfResidentDto> householdOfResidentDtoList = householdCompositionResidentRepository
                .getHouseholdOfResidentDtoList(householdSerialNumber);
        String householdName = householdOfResidentDtoList.get(0).getName();

        return new ResidentRegisterDto(certificateIssueDto.getCertificateIssueDate(),
                String.valueOf(certificateIssueDto.getCertificateConfirmationNumber())
                        .substring(0, 8),
                String.valueOf(certificateIssueDto.getCertificateConfirmationNumber())
                        .substring(8),
                householdName, householdDto, householdMovementAddressDtoList, householdOfResidentDtoList);
    }

    /**
     * 출생신고서 발급 서비스.
     *
     * @param residentSerialNumber 주민일련번호
     */
    @Transactional
    public void issueBirthCertificate(int residentSerialNumber) {
        issueCertificate(residentSerialNumber, CertificateTypeCode.BIRTH_CERTIFICATE);
    }

    @Transactional
    public BirthCertificateDto getBirthCertificate(int residentSerialNumber) {
        Resident resident = residentRepository.findById(residentSerialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(residentSerialNumber));

        String familyRelationshipCode = FamilyRelationshipCode.FATHER.getValue();
        int fatherResidentSerialNumber = familyRelationshipRepository
                .findFamilyResidentSerialNumber(residentSerialNumber, familyRelationshipCode);
        familyRelationshipCode = FamilyRelationshipCode.MOTHER.getValue();
        int motherResidentSerialNumber = familyRelationshipRepository
                .findFamilyResidentSerialNumber(residentSerialNumber, familyRelationshipCode);
        String birthDeathTypeCode = BirthDeathTypeCode.BIRTH.getValue();
        BirthDeathReportResident birthDeathReportResident
                = birthDeathReportResidentRepository.findReportResident(residentSerialNumber, birthDeathTypeCode);

        String reportDate = String.valueOf(birthDeathReportResident.getBirthDeathReportDate());

        Resident fatherResident = residentRepository.findById(fatherResidentSerialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(residentSerialNumber));
        Resident motherResident = residentRepository.findById(motherResidentSerialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(residentSerialNumber));
        Resident reportResident = residentRepository.findById(birthDeathReportResident.getPk().getReportResidentSerialNumber())
                .orElseThrow(() -> new ResidentNotFoundException(residentSerialNumber));

        return new BirthCertificateDto(resident.getName(), resident.getGenderCode(), resident.getBirthDate(),
                resident.getBirthPlaceCode(), resident.getRegistrationBaseAddress(),
                fatherResident.getName(), fatherResident.getResidentRegistrationNumber(),
                motherResident.getName(), motherResident.getResidentRegistrationNumber(),
                reportResident.getName(), reportResident.getResidentRegistrationNumber(),
                birthDeathReportResident.getBirthReportQualificationsCode(),
                birthDeathReportResident.getEmailAddress(),
                birthDeathReportResident.getPhoneNumber(),
                reportDate.substring(0,4), reportDate.substring(5, 7), reportDate.substring(8));
    }

    /**
     * 증명서 발급 서비스. (가족관계증명서, 주민등록등본)
     *
     * @param residentSerialNumber 주민일련번호
     * @return 증명서확인번호
     */
    @Transactional
    public Long issueCertificate(int residentSerialNumber, CertificateTypeCode certificateTypeCode) {
        Resident resident = residentRepository.findById(residentSerialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(residentSerialNumber));

        CertificateIssue certificateIssue = new CertificateIssue();
        certificateIssue.setCertificateConfirmationNumber(generateCertificateConfirmationNumber());
        certificateIssue.setResident(resident);
        certificateIssue.setResidentSerialNumber(resident.getResidentSerialNumber());
        certificateIssue.setCertificateTypeCode(certificateTypeCode.getValue());
        certificateIssue.setCertificateIssueDate(LocalDate.now());
        certificateIssueRepository.saveAndFlush(certificateIssue);
        return certificateIssue.getCertificateConfirmationNumber();
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
