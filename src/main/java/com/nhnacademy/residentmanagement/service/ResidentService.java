package com.nhnacademy.residentmanagement.service;

import com.nhnacademy.residentmanagement.domain.code.BirthDeathTypeCode;
import com.nhnacademy.residentmanagement.domain.code.FamilyRelationshipCode;
import com.nhnacademy.residentmanagement.domain.request.*;
import com.nhnacademy.residentmanagement.dto.ResidentDto;
import com.nhnacademy.residentmanagement.entity.BirthDeathReportResident;
import com.nhnacademy.residentmanagement.entity.FamilyRelationship;
import com.nhnacademy.residentmanagement.entity.Resident;
import com.nhnacademy.residentmanagement.exception.BirthDeathReportResidentNotFoundException;
import com.nhnacademy.residentmanagement.exception.ResidentAlreadyExistException;
import com.nhnacademy.residentmanagement.exception.ResidentNotFoundException;
import com.nhnacademy.residentmanagement.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.residentmanagement.repository.FamilyRelationshipRepository;
import com.nhnacademy.residentmanagement.repository.ResidentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 주민 관련 데이터 추가 입력을 위한 서비스. (주민, 가족관계, 출생 및 사망 신고)
 */
@Slf4j
@Service
@AllArgsConstructor
public class ResidentService {
    private final ResidentRepository residentRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    /**
     * 주민 등록 서비스.
     *
     * @param request 주민 등록 요청 정보
     * @return 주민일련번호
     */
    @Transactional
    public ResidentSerialNumber registerResident(ResidentRequest request) {
        if (residentRepository.existsById(request.getResidentSerialNumber())) {
            throw new ResidentAlreadyExistException(request.getResidentSerialNumber());
        }

        Resident resident = new Resident(request.getResidentSerialNumber(),
                request.getName(),
                request.getResidentRegistrationNumber(),
                request.getGenderCode(),
                request.getBirthDate(),
                request.getBirthPlaceCode(),
                request.getRegistrationBaseAddress());

        residentRepository.saveAndFlush(resident);

        ResidentSerialNumber residentSerialNumber = new ResidentSerialNumber();
        residentSerialNumber.setResidentSerialNumber(request.getResidentSerialNumber());
        return residentSerialNumber;
    }

    /**
     * 주민 수정 서비스.
     *
     * @param serialNumber 주민일련번호
     * @param request      주민 수정 요청 정보
     * @return 주민일련번호
     */
    @Transactional
    public ResidentSerialNumber modifyResident(int serialNumber, ResidentRequest request) {
        Resident resident = residentRepository.findById(serialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(serialNumber));
        resident.setName(request.getName());
        resident.setResidentRegistrationNumber(request.getResidentRegistrationNumber());
        resident.setGenderCode(request.getGenderCode());
        resident.setBirthDate(request.getBirthDate());
        resident.setBirthPlaceCode(request.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(request.getRegistrationBaseAddress());
        resident.setDeathDate(request.getDeathDate());
        resident.setDeathPlaceCode(request.getDeathPlaceCode());
        resident.setDeathPlaceAddress(request.getDeathPlaceAddress());
        residentRepository.saveAndFlush(resident);

        ResidentSerialNumber residentSerialNumber = new ResidentSerialNumber();
        residentSerialNumber.setResidentSerialNumber(resident.getResidentSerialNumber());
        return residentSerialNumber;
    }

    /**
     * 주민 목록 조회 서비스.
     *
     * @param pageable pageable
     * @return 주민 목록
     */
    @Transactional
    public Page<Resident> getResidentList(Pageable pageable) {
        return residentRepository.findAll(pageable);
    }

    /**
     * 주민 조회 서비스.
     *
     * @param residentSerialNumber
     * @return
     */
    @Transactional
    public ResidentDto getResident(int residentSerialNumber) {
        if (!residentRepository.existsById(residentSerialNumber)) {
            throw new ResidentNotFoundException(residentSerialNumber);
        }
        return residentRepository.queryByResidentSerialNumber(residentSerialNumber);
    }

    /**
     * 주민 삭제 서비스.
     *
     * @param residentSerialNumber 주민일련번호
     */
    @Transactional
    public void deleteResident(int residentSerialNumber) {
        if (!residentRepository.existsById(residentSerialNumber)) {
            throw new ResidentNotFoundException(residentSerialNumber);
        }
        residentRepository.deleteById(residentSerialNumber);
    }

    /**
     * 가족관계 등록 서비스.
     *
     * @param serialNumber 기준주민일련번호
     * @param request      가족관계 등록 요청 정보
     */
    @Transactional
    public void registerFamilyRelationship(int serialNumber,
                                           FamilyRelationshipCreateRequest request) {

        FamilyRelationship familyRelationship = new FamilyRelationship();

        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();
        pk.setBaseResident(residentRepository.findById(serialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(serialNumber)));
        pk.setFamilyResident(residentRepository.findById(request.getFamilySerialNumber())
                .orElseThrow(() -> new ResidentNotFoundException(request.getFamilySerialNumber())));
        familyRelationship.setPk(pk);

        familyRelationship.setFamilyRelationshipCode(FamilyRelationshipCode.getValue(request.getRelationship()));
        familyRelationshipRepository.saveAndFlush(familyRelationship);
    }

    /**
     * 가족관계 수정 서비스.
     *
     * @param serialNumber       기준주민일련번호
     * @param familySerialNumber 가족주민일련번호
     * @param request            가족관계 수정 요청 정보
     */
    @Transactional
    public void modifyFamilyRelationship(int serialNumber,
                                         int familySerialNumber,
                                         FamilyRelationshipModifyRequest request) {
        if (!residentRepository.existsById(serialNumber)) {
            throw new ResidentNotFoundException(serialNumber);
        }
        if (!residentRepository.existsById(familySerialNumber)) {
            throw new ResidentNotFoundException(familySerialNumber);
        }
        String familyRelationshipCode = FamilyRelationshipCode.getValue(request.getRelationship());
        familyRelationshipRepository.updateFamilyRelationshipCode(familyRelationshipCode,
                serialNumber, familySerialNumber);
    }

    /**
     * 가족관계 삭제 서비스.
     *
     * @param serialNumber       기준주민일련번호
     * @param familySerialNumber 가족주민일련번호
     */
    @Transactional
    public void deleteFamilyRelationship(int serialNumber, int familySerialNumber) {
        if (!residentRepository.existsById(serialNumber)) {
            throw new ResidentNotFoundException(serialNumber);
        }
        if (!residentRepository.existsById(familySerialNumber)) {
            throw new ResidentNotFoundException(familySerialNumber);
        }
        familyRelationshipRepository.deleteFamilyRelationship(serialNumber, familySerialNumber);
    }

    /**
     * 출생 및 사망 신고 등록 서비스.
     *
     * @param serialNumber 행위자 주민일련번호
     * @param request      출생 혹은 사망 신고 등록 요청 정보
     */
    @Transactional
    public void registerBirthDeathReportResident(int serialNumber,
                                                 BirthDeathReportResidentRequest request) {
        Resident resident = residentRepository.findById(serialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(serialNumber));
        Resident targetResident = residentRepository.findById(request.getResidentSerialNumber())
                .orElseThrow(() -> new ResidentNotFoundException(request.getResidentSerialNumber()));

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(request.getBirthDeathTypeCode(),
                targetResident.getResidentSerialNumber(), resident.getResidentSerialNumber());

        BirthDeathReportResident birthReportResident = new BirthDeathReportResident();
        birthReportResident.setPk(pk);
        birthReportResident.setResident(targetResident);
        birthReportResident.setReportResident(resident);
        birthReportResident.setBirthDeathReportDate(request.getBirthDeathReportDate());
        birthReportResident.setBirthReportQualificationsCode(request.getBirthReportQualificationsCode());
        birthReportResident.setDeathReportQualificationsCode(request.getDeathReportQualificationsCode());
        birthReportResident.setEmailAddress(request.getEmailAddress());
        birthReportResident.setPhoneNumber(request.getPhoneNumber());

        birthDeathReportResidentRepository.saveAndFlush(birthReportResident);
    }

    /**
     * 출생 및 사망 신고 수정 서비스.
     *
     * @param serialNumber       행위자 주민일련번호
     * @param targetSerialNumber 출생 혹은 사망한 사람의 주민일련번호
     * @param request            출생 및 사망 신고 수정 요청 정보
     */
    @Transactional
    public void modifyBirthDeathReportResident(int serialNumber, int targetSerialNumber,
                                               BirthDeathReportResidentRequest request, String birthDeathTypeCode) {
        Resident resident = residentRepository.findById(serialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(serialNumber));
        Resident targetResident = residentRepository.findById(targetSerialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(targetSerialNumber));

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(birthDeathTypeCode,
                targetResident.getResidentSerialNumber(), resident.getResidentSerialNumber());

        BirthDeathReportResident birthReportResident = birthDeathReportResidentRepository.findById(pk)
                .orElseThrow(BirthDeathReportResidentNotFoundException::new);
        birthReportResident.setBirthDeathReportDate(request.getBirthDeathReportDate());
        birthReportResident.setBirthReportQualificationsCode(request.getBirthReportQualificationsCode());
        birthReportResident.setDeathReportQualificationsCode(request.getDeathReportQualificationsCode());
        birthReportResident.setEmailAddress(request.getEmailAddress());
        birthReportResident.setPhoneNumber(request.getPhoneNumber());

        birthDeathReportResidentRepository.saveAndFlush(birthReportResident);
    }

    /**
     * 출생 및 사망 신고 삭제 서비스.
     *
     * @param serialNumber       행위자 주민일련번호
     * @param targetSerialNumber 출생 혹은 사망한 사람의 주민일련번호
     */
    @Transactional
    public void deleteBirthDeathReportResident(int serialNumber, int targetSerialNumber,
                                               String birthDeathTypeCode) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(birthDeathTypeCode,
                targetSerialNumber, serialNumber);

        BirthDeathReportResident resident = birthDeathReportResidentRepository.findById(pk).orElse(null);
        if (resident == null) {
            throw new BirthDeathReportResidentNotFoundException();
        }
        birthDeathReportResidentRepository.deleteById(pk);
    }

    /**
     * 출생신고 여부 확인 서비스.
     *
     * @return 출생신고 여부
     */
    public List<Integer> checkBirthReport() {
        List<Integer> birthReportResidentList = new ArrayList<>();
        String birthDeathTypeCode = BirthDeathTypeCode.BIRTH.getValue();
        for (Resident resident : residentRepository.findAll()) {
            int residentSerialNumber = resident.getResidentSerialNumber();
            BirthDeathReportResident reportResident =
                    birthDeathReportResidentRepository
                            .findBySerialNumberAndTypeCode(residentSerialNumber, birthDeathTypeCode);
            if (reportResident != null) {
                birthReportResidentList.add(residentSerialNumber);
            }
        }
        return birthReportResidentList;
    }
}
