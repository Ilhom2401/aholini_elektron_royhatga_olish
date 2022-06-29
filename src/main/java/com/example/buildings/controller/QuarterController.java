package com.example.buildings.controller;

import com.example.buildings.dto.EditDto;
import com.example.buildings.dto.QuarterDto;
import com.example.buildings.entity.Person;
import com.example.buildings.entity.Quarter;
import com.example.buildings.entity.Region;
import com.example.buildings.service.PersonService;
import com.example.buildings.service.QuarterService;
import com.example.buildings.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuarterController {

    private final QuarterService quarterService;
    private final PersonService personService;
    private final RegionService regionService;

    @PostMapping("/get-quarters")
    public String getList(@ModelAttribute("userId") Long userId, Model model) {
        boolean access = personService.checkAccess("QUARTER_GET_LIST", userId);
        if (!access)
            return "not-found";
        List<Quarter> list = quarterService.getList();
        model.addAttribute("list", list);
        model.addAttribute("empId", userId);
        return "admin-quarter";
    }

    @PostMapping("/add-quarter")
    public String addQuarter(@ModelAttribute("userId") Long userId, Model model) {
        boolean access = personService.checkAccess("QUARTER_ADD", userId);
        if (!access)
            return "not-found";
        List<Region> regionList = regionService.getList();
        model.addAttribute("regionList", regionList);
        model.addAttribute("empId", userId);
        return "add-quarter";
    }

    @PostMapping("/save-quarter")
    public String save(@ModelAttribute QuarterDto quarterDto, Model model) {
        boolean access = personService.checkAccess("QUARTER_ADD", quarterDto.getUserId());
        if (!access)
            return "not-found";
        quarterService.saveQuarter(quarterDto);
        List<Quarter> list = quarterService.getList();
        model.addAttribute("list", list);
        model.addAttribute("empId", quarterDto.getUserId());
        return "admin-quarter";
    }

    @PostMapping("/edit-quarter")
    public String edit(@ModelAttribute QuarterDto quarterDto, Model model){
        boolean access = personService.checkAccess("QUARTER_UPDATE", quarterDto.getUserId());
        if (!access)
            return "not-found";
        Quarter quarter = quarterService.edit(quarterDto);
        List<Region> regionList = regionService.getList();
        model.addAttribute("regionList", regionList);
        model.addAttribute("quarter", quarter);
        model.addAttribute("empId", quarterDto.getUserId());
        return "edit-quarter";
    }

    @PostMapping("/edited-quarter")
    public String edited(@ModelAttribute QuarterDto quarterDto, Model model){
        boolean access = personService.checkAccess("QUARTER_UPDATE", quarterDto.getUserId());
        if (!access)
            return "not-found";
        quarterService.edited(quarterDto);
        List<Quarter> list = quarterService.getList();
        model.addAttribute("list", list);
        model.addAttribute("empId", quarterDto.getUserId());
        return "admin-quarter";
    }

    @PostMapping("/delete-quarter")
    public String deleteEmployee(QuarterDto quarterDto, Model model){
        boolean access = personService.checkAccess("QUARTER_DELETE", quarterDto.getUserId());
        if (!access)
            return "not-found";
        quarterService.deleteQuarter(quarterDto.getQuarterId());
        List<Quarter> list = quarterService.getList();
        model.addAttribute("list", list);
        model.addAttribute("empId", quarterDto.getUserId());
        return "admin-quarter";
    }

    @PostMapping("/get-quarter")
    public String chairmanQuarter(@ModelAttribute("userId") Long userId, Model model){
        boolean access = personService.checkAccess("PERSON_GET_LIST", userId);
        if (!access)
            return "not-found";
        Person person = personService.getById(userId);
        List<Person> list = personService.getListByQuarterId(person.getQuarter().getId());
        model.addAttribute("list", list);
        model.addAttribute("person", person);
        model.addAttribute("empId", userId);
        return "chairman-quarter-list";
    }
}
