package com.example.buildings.controller;

import com.example.buildings.dto.EditDto;
import com.example.buildings.dto.LoginDto;
import com.example.buildings.dto.PersonDto;
import com.example.buildings.entity.Person;
import com.example.buildings.entity.Region;
import com.example.buildings.entity.RoleEntity;
import com.example.buildings.enums.*;
import com.example.buildings.service.PersonService;
import com.example.buildings.service.RegionService;
import com.example.buildings.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final RoleService roleService;
    private final RegionService regionService;

    @GetMapping("/")
    public String mainMenu() {
        return "census/index";
    }


    @PostMapping(value = "/login-user")
    public String add(@ModelAttribute LoginDto loginDto, Model model) {
        Person person = personService.getUserId(loginDto);
        if (person == null)
            return "not-found";
        if (person.getRole().getId() == 2) {
            model.addAttribute("employee", person);
            return "user";
        }
        model.addAttribute("empId", person.getId());
        model.addAttribute("person", person);
        model.addAttribute("roleId", person.getRole().getId());
        return "admin";
    }

    @PostMapping("/get-chairmans")
    public String getEmpList(@ModelAttribute("userId") Long userId, Model model) {
        boolean access = personService.checkAccess("CHAIRMAN_GET_LIST", userId);
        if (!access)
            return "not-found";
        List<Person> list = personService.getChairmans();
        model.addAttribute("list", list);
        model.addAttribute("empId", userId);
        return "admin-chairman";
    }

    @PostMapping("/add-chairman")
    public String addEmpl(@ModelAttribute("userId") Long userId, Model model) {
        boolean access = personService.checkAccess("CHAIRMAN_ADD", userId);
        if (!access)
            return "not-found";
        Person person = personService.getById(userId);
        List<RoleEntity> roleEntityList = roleService.getList();
        List<EducationStatus> educationStatusList = Arrays.asList(EducationStatus.values());
        EmploymentStatus[] employmentStatuses = EmploymentStatus.values();
        Gender[] genders = Gender.values();
        HealthStatus[] healthStatuses = HealthStatus.values();
        SocicalStatus[] socicalStatuses = SocicalStatus.values();
        List<Region> regionList = regionService.getList();
        model.addAttribute("roleEntityList", roleEntityList);
        model.addAttribute("educationStatus", educationStatusList);
        model.addAttribute("employmentStatuses", employmentStatuses);
        model.addAttribute("genders", genders);
        model.addAttribute("healthStatuses", healthStatuses);
        model.addAttribute("socicalStatuses", socicalStatuses);
        model.addAttribute("regionList", regionList);
        model.addAttribute("roleId", person.getRole().getId());
        model.addAttribute("empId", userId);
        return "add-chairman";
    }

    @PostMapping("save-chairman")
    public String save(@ModelAttribute PersonDto personDto, Model model) {
        boolean access = personService.checkAccess("CHAIRMAN_ADD", personDto.getUserId());
        if (!access)
            return "not-found";
        personService.save(personDto);
        Person person = personService.getById(personDto.getUserId());
        if (person.getRole().getId()==3){
            List<Person> list = personService.getListByQuarterId(person.getQuarter().getId());
            model.addAttribute("list", list);
            model.addAttribute("person", person);
            model.addAttribute("empId", personDto.getUserId());
            return "chairman-quarter-list";
        }
        List<Person> list = personService.getChairmans();
        model.addAttribute("list", list);
        model.addAttribute("empId", personDto.getUserId());
        return "admin-chairman";
    }

    @PostMapping("/edit-chairman")
    public String edit(@ModelAttribute EditDto editDto, Model model) {
        boolean access = personService.checkAccess("CHAIRMAN_UPDATE", editDto.getUserId());
        if (!access)
            return "not-found";
        Person person = personService.getById(editDto.getPersonId());
        List<RoleEntity> roleEntityList = roleService.getList();
        List<EducationStatus> educationStatusList = Arrays.asList(EducationStatus.values());
        EmploymentStatus[] employmentStatuses = EmploymentStatus.values();
        Gender[] genders = Gender.values();
        HealthStatus[] healthStatuses = HealthStatus.values();
        SocicalStatus[] socicalStatuses = SocicalStatus.values();
        List<Region> regionList = regionService.getList();
        model.addAttribute("roleEntityList", roleEntityList);
        model.addAttribute("educationStatus", educationStatusList);
        model.addAttribute("employmentStatuses", employmentStatuses);
        model.addAttribute("genders", genders);
        model.addAttribute("healthStatuses", healthStatuses);
        model.addAttribute("socicalStatuses", socicalStatuses);
        model.addAttribute("regionList", regionList);
        model.addAttribute("empId", editDto.getUserId());
        model.addAttribute("person", person);
        model.addAttribute("roleId", person.getRole().getId());
        return "edit-chairman";
    }

    @PostMapping("/edited-chairman")
    public String edited(@ModelAttribute PersonDto personDto, Model model) {
        boolean access = personService.checkAccess("CHAIRMAN_UPDATE", personDto.getUserId());
        if (!access)
            return "not-found";
        personService.edited(personDto);
        Person person = personService.getById(personDto.getPersonId());
        if (person.getRole().getId()==3){
            List<Person> list = personService.getListByQuarterId(person.getQuarter().getId());
            model.addAttribute("list", list);
            model.addAttribute("person", person);
            model.addAttribute("empId", personDto.getUserId());
            return "chairman-quarter-list";
        }
        List<Person> list = personService.getChairmans();
        model.addAttribute("list", list);
        model.addAttribute("empId", personDto.getUserId());
        return "admin-chairman";
    }

    @PostMapping("/delete-chairman")
    public String deleteEmployee(EditDto editDto, Model model){
        boolean access = personService.checkAccess("CHAIRMAN_DELETE", editDto.getUserId());
        if (!access)
            return "not-found";
        personService.deletePerson(editDto.getPersonId());
        List<Person> list = personService.getChairmans();
        model.addAttribute("list", list);
        model.addAttribute("empId", editDto.getUserId());
        return "admin-chairman";
    }

}
