package com.nhnacademy.residentmanagement.controller;

import com.nhnacademy.residentmanagement.domain.request.HouseholdCreateRequest;
import com.nhnacademy.residentmanagement.domain.request.HouseholdMovementAddressRequest;
import com.nhnacademy.residentmanagement.exception.ValidationFailedException;
import com.nhnacademy.residentmanagement.service.HouseholdService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 세대 관련 데이터 추가 입력을 위한 RestController. (세대, 세대 전입 주소)
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/household")
public class HouseholdRestController {
    private final HouseholdService householdService;

    /**
     * 세대 등록.
     *
     * @param request       세대 등록 요청 정보
     * @param bindingResult bindingResult
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void registerHousehold(@RequestBody HouseholdCreateRequest request,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        householdService.registerHousehold(request);
    }

    /**
     * 세대 삭제.
     *
     * @param householdSerialNumber 세대일련번호
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{householdSerialNumber}")
    public void deleteHousehold(@PathVariable("householdSerialNumber") int householdSerialNumber) {
        householdService.deleteHousehold(householdSerialNumber);
    }

    /**
     * 세대 전입 주소 등록.
     *
     * @param householdSerialNumber 세대일련번호
     * @param request               세대전입주소 등록 요청 정보
     * @param bindingResult         bindingResult
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/{householdSerialNumber}/movement")
    public void registerHouseholdMovementAddress(@PathVariable("householdSerialNumber") int householdSerialNumber,
                                                 @RequestBody HouseholdMovementAddressRequest request,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        householdService.registerHouseholdMovementAddress(householdSerialNumber, request);
    }

    /**
     * 세대 전입 주소 수정.
     *
     * @param householdSerialNumber   세대일련번호
     * @param houseMovementReportDate 전입신고일자
     * @param request                 세대전입주소 수정 요청 정보
     * @param bindingResult           bindingResult
     */
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{householdSerialNumber}/movement/{reportDate}")
    public void modifyHouseholdMovementAddress(@PathVariable("householdSerialNumber") int householdSerialNumber,
                                               @PathVariable("reportDate")
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate houseMovementReportDate,
                                               @RequestBody HouseholdMovementAddressRequest request,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        householdService.modifyHouseholdMovementAddress(householdSerialNumber, houseMovementReportDate, request);
    }

    /**
     * 세대 전입 주소 삭제.
     *
     * @param householdSerialNumber   세대일련번호
     * @param houseMovementReportDate 전입신고일자
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{householdSerialNumber}/movement/{reportDate}")
    public void deleteHouseholdMovementAddress(@PathVariable("householdSerialNumber") int householdSerialNumber,
                                               @PathVariable("reportDate")
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate houseMovementReportDate) {
        householdService.deleteHouseholdMovementAddress(householdSerialNumber, houseMovementReportDate);
    }
}
