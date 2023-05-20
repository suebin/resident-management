package com.nhnacademy.residentmanagement.service;

import com.nhnacademy.residentmanagement.domain.HouseholdCreateRequest;
import com.nhnacademy.residentmanagement.domain.HouseholdMovementAddressRequest;
import com.nhnacademy.residentmanagement.entity.Household;
import com.nhnacademy.residentmanagement.entity.HouseholdMovementAddress;
import com.nhnacademy.residentmanagement.entity.Resident;
import com.nhnacademy.residentmanagement.exception.HouseholdMovementAddressNotFoundException;
import com.nhnacademy.residentmanagement.exception.HouseholdNotFoundException;
import com.nhnacademy.residentmanagement.exception.ResidentNotFoundException;
import com.nhnacademy.residentmanagement.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.residentmanagement.repository.HouseholdRepository;
import com.nhnacademy.residentmanagement.repository.ResidentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@AllArgsConstructor
public class HouseholdService {
    private final ResidentRepository residentRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;

    /**
     * 세대 등록 서비스.
     *
     * @param request 세대 등록 요청 정보
     */
    @Transactional
    public void registerHousehold(HouseholdCreateRequest request) {
        Resident resident = residentRepository.findById(request.getHouseholdResidentSerialNumber())
                .orElseThrow(() -> new ResidentNotFoundException(request.getHouseholdResidentSerialNumber()));

        Household household = new Household();
        household.setResident(resident);
        household.setHouseholdSerialNumber(request.getHouseholdSerialNumber());
        household.setHouseholdResidentSerialNumber(resident.getResidentSerialNumber());
        household.setHouseholdCompositionDate(request.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(request.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(request.getCurrentHouseMovementAddress());
        householdRepository.saveAndFlush(household);
    }

    /**
     * 세대 삭제 서비스.
     *
     * @param householdSerialNumber 세대일련번호
     */
    @Transactional
    public void deleteHousehold(int householdSerialNumber) {
        Household household = householdRepository.findById(householdSerialNumber)
                .orElse(null);
        if (household == null) {
            throw new HouseholdNotFoundException(householdSerialNumber);
        }
        householdRepository.deleteById(householdSerialNumber);
    }

    /**
     * 세대 전입 주소 등록 서비스
     *
     * @param householdSerialNumber 세대일련번호
     * @param request               세대전입주소 등록 요청 정보
     */
    @Transactional
    public void registerHouseholdMovementAddress(int householdSerialNumber,
                                                 HouseholdMovementAddressRequest request) {
        Household household = householdRepository.findById(householdSerialNumber)
                .orElseThrow(() -> new HouseholdNotFoundException(householdSerialNumber));

        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        householdMovementAddress.setHousehold(household);
        HouseholdMovementAddress.Pk pk =
                new HouseholdMovementAddress.Pk(request.getHouseMovementReportDate(), householdSerialNumber);
        householdMovementAddress.setPk(pk);
        householdMovementAddress.setHouseMovementAddress(request.getHouseMovementAddress());
        householdMovementAddress.setIsLastAddress(request.getIsLastAddress());
        householdMovementAddressRepository.saveAndFlush(householdMovementAddress);
    }

    /**
     * 세대 전입 주소 수정 서비스.
     *
     * @param householdSerialNumber   세대일련번호
     * @param houseMovementReportDate 전입신고일자
     * @param request                 세대전입주소 수정 요청 정보
     */
    @Transactional
    public void modifyHouseholdMovementAddress(int householdSerialNumber,
                                               LocalDate houseMovementReportDate,
                                               HouseholdMovementAddressRequest request) {
        HouseholdMovementAddress.Pk pk =
                new HouseholdMovementAddress.Pk(houseMovementReportDate, householdSerialNumber);
        HouseholdMovementAddress householdMovementAddress =
                householdMovementAddressRepository.findById(pk)
                        .orElseThrow(() -> new HouseholdMovementAddressNotFoundException());
        householdMovementAddress.setHouseMovementAddress(request.getHouseMovementAddress());
        householdMovementAddress.setIsLastAddress(request.getIsLastAddress());

        householdMovementAddressRepository.saveAndFlush(householdMovementAddress);
    }

    /**
     * 세대 전입 주소 삭제 서비스.
     *
     * @param householdSerialNumber   세대일련번호
     * @param houseMovementReportDate 전입신고일자
     */
    @Transactional
    public void deleteHouseholdMovementAddress(int householdSerialNumber,
                                               LocalDate houseMovementReportDate) {
        HouseholdMovementAddress.Pk pk =
                new HouseholdMovementAddress.Pk(houseMovementReportDate, householdSerialNumber);
        householdMovementAddressRepository.deleteById(pk);
    }
}
