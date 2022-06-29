package com.example.buildings.repository;

import com.example.buildings.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query(value = "select * from district where region_id = ?1", nativeQuery = true)
    List<District> getList(Long id);
}
