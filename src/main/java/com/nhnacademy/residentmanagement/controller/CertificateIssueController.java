package com.nhnacademy.residentmanagement.controller;

import com.nhnacademy.residentmanagement.dto.CertificateOfFamilyRelationsDto;
import com.nhnacademy.residentmanagement.dto.ResidentRegisterDto;
import com.nhnacademy.residentmanagement.service.CertificateIssueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 증명서 발급 Controller.
 */
@Controller
@AllArgsConstructor
@RequestMapping("/certificate")
public class CertificateIssueController {
    private final CertificateIssueService certificateIssueService;

    /**
     * 가족관계증명서 발급.
     *
     * @param residentSerialNumber 주민일련번호
     * @return view
     */
    @PostMapping("/certificate-of-family-relations")
    public String issueCertificateOfFamilyRelations(int residentSerialNumber) {
        Long certificateConfirmationNumber =
                certificateIssueService
                        .issueRegisterCertificateOfFamilyRelations(residentSerialNumber);
        return "redirect:/certificate/certificate-of-family-relations/"
                + residentSerialNumber + "/" + certificateConfirmationNumber;
    }

    /**
     * 가족관계증명서 조회.
     *
     * @param model                           가족관계증명서에 필요한 데이터
     * @param residentSerialNumber            주민일련번호
     * @param certificationConfirmationNumber 증명서확인번호
     * @return view
     */
    @GetMapping("/certificate-of-family-relations/{serialNumber}"
            + "/{certificationConfirmationNumber}")
    public String getCertificateOfFamilyRelations(Model model,
                                                @PathVariable("serialNumber")
                                                int residentSerialNumber,
                                                @PathVariable("certificationConfirmationNumber")
                                                Long certificationConfirmationNumber) {
        CertificateOfFamilyRelationsDto dto = certificateIssueService
                .getCertificateOfFamilyRelations(residentSerialNumber, certificationConfirmationNumber);
        model.addAttribute("dto", dto);
        return "certificate/certificate-of-family-relations";
    }

    /**
     * 주민등록등본 발급.
     *
     * @param residentSerialNumber 주민일련번호
     * @return view
     */
    @PostMapping("/resident-register")
    public String issueResidentRegister(int residentSerialNumber) {
        Long certificateConfirmationNumber =
                certificateIssueService
                        .issueResidentRegister(residentSerialNumber);
        return "redirect:/certificate/resident-register/"
                + residentSerialNumber + "/" + certificateConfirmationNumber;
    }

    /**
     * 주민등록등본 조회.
     *
     * @param model                           주민등록등본에 필요한 데이터
     * @param residentSerialNumber            주민일련번호
     * @param certificationConfirmationNumber 증명서확인번호
     * @return view
     */
    @GetMapping("/resident-register/{serialNumber}/{certificationConfirmationNumber}")
    public String getResidentRegister(Model model,
                                                  @PathVariable("serialNumber")
                                                  int residentSerialNumber,
                                                  @PathVariable("certificationConfirmationNumber")
                                                  Long certificationConfirmationNumber) {
        ResidentRegisterDto dto = certificateIssueService
                .getResidentRegister(residentSerialNumber, certificationConfirmationNumber);
        model.addAttribute("dto", dto);
        return "certificate/resident-register";
    }


}
