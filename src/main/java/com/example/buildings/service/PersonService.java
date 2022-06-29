package com.example.buildings.service;

import com.example.buildings.dto.LoginDto;
import com.example.buildings.dto.PersonDto;
import com.example.buildings.entity.Person;
import com.example.buildings.entity.Quarter;
import com.example.buildings.entity.RoleEntity;
import com.example.buildings.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final ModelMapper modelMapper;
    private final PersonRepository personRepository;
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;
    private final RoleRepository roleRepository;
    private final QuarterRepository quarterRepository;


    public List<Person> getByProjectId(Long projectId) {
        return personRepository.getListByProjectId(projectId);
    }

    public Person getUserId(LoginDto loginDto) {
        Optional<Person> optionalEmployee = personRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        return optionalEmployee.orElse(null);
    }

    public Person getById(Long userId) {
        Optional<Person> optionalEmployee = personRepository.findById(userId);
        if (optionalEmployee.isEmpty())
            return null;
        return optionalEmployee.get();
    }

    public boolean checkAccess(String action, Long UserId) {
        Long access = personRepository.access(action, UserId);
        return access != null;
    }

    public List<Person> getList() {
        return personRepository.findAll();
    }

    public List<Person> getChairmans() {
        return personRepository.getChairmansByRoleId();
    }

    public List<Person> getListByQuarterId(Long id){
        return personRepository.getListByQurtId(id);
    }

    public boolean save(PersonDto personDto) {
        RoleEntity role = null;
        Quarter quarter = null;
        Optional<Person> optionalPerson = personRepository.findByUsername(personDto.getUsername());
        if (optionalPerson.isPresent())
            return false;
        if (personDto.getRoleId() != null){
            Optional<RoleEntity> optionalRole = roleRepository.findById(personDto.getRoleId());
            if (optionalRole.isEmpty())
                return false;
            role = optionalRole.get();
        }else {
            Optional<RoleEntity> optionalRole = roleRepository.findById(2L);
            role = optionalRole.get();
        }

        if (personDto.getQuarterId()!=null){
            Optional<Quarter> optionalQuarter = quarterRepository.findById(personDto.getQuarterId());
            if (optionalQuarter.isEmpty())
                return false;
            quarter = optionalQuarter.get();
        }else {
            Optional<Person> personOptional = personRepository.findById(personDto.getUserId());
            quarter = personOptional.get().getQuarter();
        }

        Person person = new Person();
        person.setActive(true);
        person.setBirthDate(personDto.getBirthDate());
        person.setEducationStatus(personDto.getEducationStatus());
        person.setEmploymentStatus(personDto.getEmploymentStatus());
        person.setFullName(personDto.getFullName());
        person.setGender(personDto.getGender());
        person.setHealthStatus(personDto.getHealthStatus());
        person.setPassportInfo(personDto.getPassportInfo());
        person.setPassword(personDto.getPassword());
        person.setPhoneNumber(personDto.getPhoneNumber());
        person.setPinfl(personDto.getPinfl());
        person.setSocicalStatus(personDto.getSocicalStatus());
        person.setUsername(personDto.getUsername());
        person.setRole(role);
        person.setQuarter(quarter);
        personRepository.save(person);
        return true;
    }


    public Person edit(Long empId) {
        Optional<Person> optionalEmployee = personRepository.findById(empId);
        if (optionalEmployee.isEmpty())
            return null;
        return optionalEmployee.get();
    }

    public boolean edited(PersonDto personDto) {
        Optional<Person> optionalPerson = personRepository.findById(personDto.getPersonId());
        if (optionalPerson.isEmpty())
            return false;
        Person person = optionalPerson.get();
        RoleEntity role=null;
        if(personDto.getRoleId()!=null){

            Optional<RoleEntity> optionalRole = roleRepository.findById(personDto.getRoleId());
            if (optionalRole.isEmpty())
                return false;
            role=optionalRole.get();
        }
        else {
            Optional<RoleEntity> byId = roleRepository.findById(2L);
            role=byId.get();
        }
        Quarter quarter=null;
        if(personDto.getQuarterId()!=null){

            Optional<Quarter> optionalQuarter = quarterRepository.findById(personDto.getQuarterId());
            if (optionalQuarter.isEmpty())
                return false;
            quarter=optionalQuarter.get();
        }
        else {
            Optional<Person> byId = personRepository.findById(personDto.getPersonId());
            quarter=byId.get().getQuarter();
        }
        person.setActive(true);
        person.setBirthDate(personDto.getBirthDate());
        person.setEducationStatus(personDto.getEducationStatus());
        person.setEmploymentStatus(personDto.getEmploymentStatus());
        person.setFullName(personDto.getFullName());
        person.setGender(personDto.getGender());
        person.setHealthStatus(personDto.getHealthStatus());
        person.setPassportInfo(personDto.getPassportInfo());
        person.setPassword(personDto.getPassword());
        person.setPhoneNumber(personDto.getPhoneNumber());
        person.setPinfl(personDto.getPinfl());
        person.setSocicalStatus(personDto.getSocicalStatus());
        person.setUsername(personDto.getUsername());
        person.setRole(role);
        person.setQuarter(quarter);
        personRepository.save(person);
        return true;
    }

    public boolean deletePerson(Long empId) {
        personRepository.deleteById(empId);
        return true;
    }
}
