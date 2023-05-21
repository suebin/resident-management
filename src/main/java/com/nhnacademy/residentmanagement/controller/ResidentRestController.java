package com.nhnacademy.residentmanagement.controller;

import com.nhnacademy.residentmanagement.domain.request.*;
import com.nhnacademy.residentmanagement.exception.ValidationFailedException;
import com.nhnacademy.residentmanagement.service.ResidentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * 주민 관련 데이터 추가 입력을 위한 RestController. (주민, 가족관계, 출생 및 사망 신고)
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/residents")
public class ResidentRestController {
    private final ResidentService residentService;

    /**
     * 주민 등록.
     *
     * @param request       주민 등록 요청 정보
     * @param bindingResult bindingResult
     * @return 주민일련번호
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ResidentSerialNumber registerResident(@RequestBody ResidentRequest request,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return residentService.registerResident(request);
    }

    /**
     * 주민 수정.
     *
     * @param serialNumber  주민일련번호
     * @param request       주민 수정 요청 정보
     * @param bindingResult bindingResult
     * @return 주민일련번호
     */
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{serialNumber}")
    public ResidentSerialNumber modifyResident(@PathVariable("serialNumber") int serialNumber,
                                               @RequestBody ResidentRequest request,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return residentService.modifyResident(serialNumber, request);
    }

    /**
     * 가족관계 등록.
     *
     * @param serialNumber  기준주민일련번호
     * @param request       가족관계 등록 요청 정보
     * @param bindingResult bindingResult
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/{serialNumber}/relationship")
    public void registerFamilyRelationship(@PathVariable("serialNumber") int serialNumber,
                                           @RequestBody FamilyRelationshipCreateRequest request,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        residentService.registerFamilyRelationship(serialNumber, request);
    }

    /**
     * 가족관계 수정.
     *
     * @param serialNumber       기준주민일련번호
     * @param familySerialNumber 가족주민일련번호
     * @param request            가족관계 수정 요청 정보
     * @param bindingResult      bindingResult
     */
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public void modifyFamilyRelationship(@PathVariable("serialNumber") int serialNumber,
                                         @PathVariable("familySerialNumber") int familySerialNumber,
                                         @RequestBody FamilyRelationshipModifyRequest request,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        residentService.modifyFamilyRelationship(serialNumber, familySerialNumber, request);
    }

    /**
     * 가족관계 삭제.
     *
     * @param serialNumber       기준주민일련번호
     * @param familySerialNumber 가족주민일련번호
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public void deleteFamilyRelationship(@PathVariable("serialNumber") int serialNumber,
                                         @PathVariable("familySerialNumber") int familySerialNumber) {
        residentService.deleteFamilyRelationship(serialNumber, familySerialNumber);
    }

    /**
     * 출생신고 등록.
     *
     * @param serialNumber  행위자 주민일련번호
     * @param request       출생신고 등록 요청 정보
     * @param bindingResult bindingResult
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/{serialNumber}/birth")
    public void registerBirthReportResident(@PathVariable("serialNumber") int serialNumber,
                                            @RequestBody BirthDeathReportResidentRequest request,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        residentService.registerBirthDeathReportResident(serialNumber, request);
    }

    /**
     * 출생신고 수정.
     *
     * @param serialNumber       행위자 주민일련번호
     * @param targetSerialNumber 출생한 사람의 주민일련번호
     * @param request            출생신고 수정 요청 정보
     * @param bindingResult      bindingResult
     */
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public void modifyBirthReportResident(@PathVariable("serialNumber") int serialNumber,
                                          @PathVariable("targetSerialNumber") int targetSerialNumber,
                                          @RequestBody BirthDeathReportResidentRequest request,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        residentService.modifyBirthDeathReportResident(serialNumber, targetSerialNumber,
                request, "출생");
    }

    /**
     * 출생신고 삭제.
     *
     * @param serialNumber       행위자 주민일련번호
     * @param targetSerialNumber 출생한 사람의 주민일련번호
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public void deleteBirthReportResident(@PathVariable("serialNumber") int serialNumber,
                                          @PathVariable("targetSerialNumber") int targetSerialNumber) {
        residentService.deleteBirthDeathReportResident(serialNumber, targetSerialNumber, "출생");
    }

    /**
     * 사망신고 등록.
     *
     * @param serialNumber  행위자 주민일련번호
     * @param request       사망신고 등록 요청 정보
     * @param bindingResult bindingResult
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/{serialNumber}/death")
    public void registerDeathReportResident(@PathVariable("serialNumber") int serialNumber,
                                            @RequestBody BirthDeathReportResidentRequest request,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        residentService.registerBirthDeathReportResident(serialNumber, request);
    }

    /**
     * 사망신고 수정.
     *
     * @param serialNumber       행위자 주민일련번호
     * @param targetSerialNumber 사망한 사람의 주민일련번호
     * @param request            사망신고 수정 요청 정보
     * @param bindingResult      bindingResult
     */
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{serialNumber}/death/{targetSerialNumber}")
    public void modifyDeathReportResident(@PathVariable("serialNumber") int serialNumber,
                                          @PathVariable("targetSerialNumber") int targetSerialNumber,
                                          @RequestBody BirthDeathReportResidentRequest request,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        residentService.modifyBirthDeathReportResident(serialNumber, targetSerialNumber,
                request, "사망");
    }

    /**
     * 사망신고 삭제.
     *
     * @param serialNumber       행위자 주민일련번호
     * @param targetSerialNumber 사망한 사람의 주민일련번호
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{serialNumber}/death/{targetSerialNumber}")
    public void deleteDeathReportResident(@PathVariable("serialNumber") int serialNumber,
                                          @PathVariable("targetSerialNumber") int targetSerialNumber) {
        residentService.deleteBirthDeathReportResident(serialNumber, targetSerialNumber, "사망");
    }
}
