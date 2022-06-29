package com.example.buildings.controller;

import com.example.buildings.entity.District;
import com.example.buildings.entity.Quarter;
import com.example.buildings.service.DistrictService;
import com.example.buildings.service.QuarterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;
    private final QuarterService quarterService;

    @PostMapping("/get-district/{id}")
    public List<District> getList(@PathVariable("id") Long id) {
        return districtService.getList(id);
    }

    @PostMapping("/get-quarter/{id}")
    public List<Quarter> getQuarterList(@PathVariable("id") Long id){
        return quarterService.getList(id);
    }
}
