package com.example.buildings.service;

import com.example.buildings.entity.Region;
import com.example.buildings.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository repository;

    public List<Region> getList(){
        return repository.findAll();
    }
}
