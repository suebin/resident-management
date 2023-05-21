package com.nhnacademy.residentmanagement.controller;

import com.nhnacademy.residentmanagement.dto.certificate.BirthCertificateDto;
import com.nhnacademy.residentmanagement.dto.certificate.CertificateOfFamilyRelationsDto;
import com.nhnacademy.residentmanagement.dto.certificate.DeathCertificateDto;
import com.nhnacademy.residentmanagement.dto.certificate.ResidentRegisterDto;
import com.nhnacademy.residentmanagement.entity.CertificateIssue;
import com.nhnacademy.residentmanagement.service.CertificateIssueService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 주민관리 문서 발급 및 조회 Controller. (가족관계증명서, 주민등록등본, 출생신고서, 사망신고서)
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

    /**
     * 출생신고서 발급.
     *
     * @param residentSerialNumber 주민일련번호
     * @return view
     */
    @PostMapping("/birth-certificate")
    public String issueBirthCertificate(int residentSerialNumber) {
        certificateIssueService.issueBirthCertificate(residentSerialNumber);
        return "redirect:/certificate/birth-certificate/" + residentSerialNumber;
    }

    /**
     * 출생신고서 조회.
     *
     * @param model                출생신고서에 필요한 데이터
     * @param residentSerialNumber 주민일련번호
     * @return view
     */
    @GetMapping("/birth-certificate/{serialNumber}")
    public String getBirthCertificate(Model model,
                                      @PathVariable("serialNumber")
                                      int residentSerialNumber) {
        BirthCertificateDto dto =
                certificateIssueService.getBirthCertificate(residentSerialNumber);
        model.addAttribute("dto", dto);
        return "certificate/birth-certificate";
    }

    /**
     * 사망신고서 발급.
     *
     * @param residentSerialNumber 주민일련번호
     * @return view
     */
    @PostMapping("/death-certificate")
    public String issueDeathCertificate(int residentSerialNumber) {
        certificateIssueService.issueDeathCertificate(residentSerialNumber);
        return "redirect:/certificate/death-certificate/" + residentSerialNumber;
    }

    /**
     * 사망신고서 조회.
     *
     * @param model                사망신고서에 필요한 데이터
     * @param residentSerialNumber 주민일련번호
     * @return view
     */
    @GetMapping("/death-certificate/{serialNumber}")
    public String getDeathCertificate(Model model,
                                      @PathVariable("serialNumber")
                                      int residentSerialNumber) {
        DeathCertificateDto dto =
                certificateIssueService.getDeathCertificate(residentSerialNumber);
        model.addAttribute("dto", dto);
        return "certificate/death-certificate";
    }

    /**
     * 증명서 발급 목록 조회.
     *
     * @param model                증명서 발급 목록
     * @param residentSerialNumber 주민일련번호
     * @return view
     */
    @GetMapping("/issue-list/{serialNumber}")
    public String getCertificateIssueList(Model model,
                                          @PathVariable("serialNumber") int residentSerialNumber,
                                          @PageableDefault(size = 5, sort = "certificateIssueDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CertificateIssue> list
                = certificateIssueService.getCertificateIssue(residentSerialNumber, pageable);
        model.addAttribute("list", list);
        model.addAttribute("residentSerialNumber", residentSerialNumber);
        return "certificate/issue-list";
    }

}
