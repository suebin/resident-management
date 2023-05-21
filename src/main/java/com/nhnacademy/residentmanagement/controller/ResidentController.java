package com.nhnacademy.residentmanagement.controller;

import com.nhnacademy.residentmanagement.dto.ResidentDto;
import com.nhnacademy.residentmanagement.entity.Resident;
import com.nhnacademy.residentmanagement.service.ResidentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 주민 관리 Controller.
 */
@Controller
@AllArgsConstructor
@RequestMapping("/resident")
public class ResidentController {
    private final ResidentService residentService;

    /**
     * 주민 목록 조회.
     *
     * @param model    주민 목록
     * @param pageable pageable
     * @return view
     */
    @GetMapping(value = {"/list", "/", ""})
    public String residents(Model model, @PageableDefault(size = 5, sort = "residentSerialNumber", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Resident> residentPage = residentService.getResidentList(pageable);
        model.addAttribute("residentPage", residentPage);
        return "resident/list";
    }

    /**
     * 주민 상세 조회.
     *
     * @param model                주민 정보
     * @param page                 page
     * @param size                 size
     * @param residentSerialNumber 주민일련번호
     * @return view
     */
    @GetMapping("/view")
    public String users(Model model,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size,
                        int residentSerialNumber) {
        ResidentDto residentDto = residentService.getResident(residentSerialNumber);
        model.addAttribute("residentDto", residentDto);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "resident/view";
    }
}
