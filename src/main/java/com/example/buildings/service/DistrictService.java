package com.example.buildings.service;

import com.example.buildings.entity.District;
import com.example.buildings.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;

    public List<District> getList(Long id){
        return districtRepository.getList(id);
    }
}
