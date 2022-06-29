package com.example.buildings.service;

import com.example.buildings.dto.QuarterDto;
import com.example.buildings.entity.District;
import com.example.buildings.entity.Quarter;
import com.example.buildings.repository.DistrictRepository;
import com.example.buildings.repository.PersonRepository;
import com.example.buildings.repository.QuarterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuarterService {

    private final QuarterRepository quarterRepository;
    private final DistrictRepository districtRepository;
    private final PersonRepository personRepository;

    public List<Quarter> getList(Long id) {
        return quarterRepository.getQuarters(id);
    }

    public List<Quarter> getList() {
        return quarterRepository.findAll();
    }

    public boolean saveQuarter(QuarterDto quarterDto) {
        Optional<Quarter> optionalQuarter = quarterRepository.findByName(quarterDto.getName());
        if (optionalQuarter.isPresent())
            return false;
        Optional<District> optionalDistrict = districtRepository.findById(quarterDto.getDistrictId());
        if (optionalDistrict.isEmpty())
            return false;
        District district = optionalDistrict.get();
        Quarter quarter = new Quarter();
        quarter.setActive(true);
        quarter.setCode(quarterDto.getCode());
        quarter.setName(quarterDto.getName());
        quarter.setDistrict(district);
        quarterRepository.save(quarter);
        return true;
    }

    public Quarter edit(QuarterDto quarterDto) {
        Optional<Quarter> optionalQuarter = quarterRepository.findById(quarterDto.getQuarterId());
        if (optionalQuarter.isEmpty())
            return null;
        return optionalQuarter.get();
    }

    public boolean edited(QuarterDto quarterDto) {
        Optional<Quarter> optionalQuarter = quarterRepository.findById(quarterDto.getQuarterId());
        if (optionalQuarter.isEmpty())
            return false;
        Quarter quarter = optionalQuarter.get();
        Optional<District> optionalDistrict = districtRepository.findById(quarterDto.getDistrictId());
        if (optionalDistrict.isEmpty())
            return false;
        District district = optionalDistrict.get();
        quarter.setActive(true);
        quarter.setCode(quarterDto.getCode());
        quarter.setName(quarterDto.getName());
        quarter.setDistrict(district);
        quarterRepository.save(quarter);
        return true;
    }

    public void deleteQuarter(Long id) {
        if (personRepository.existsByQuarterId(id))
            personRepository.deleteByQuarterId(id);
        quarterRepository.deleteById(id);
    }
}
