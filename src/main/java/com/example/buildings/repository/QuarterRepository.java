package com.example.buildings.repository;

import com.example.buildings.entity.Quarter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuarterRepository extends JpaRepository<Quarter, Long> {

    @Query(value = "select * from quarter where district_id = ?1", nativeQuery = true)
    List<Quarter> getQuarters(Long id);

    Optional<Quarter> findByName(String name);

    @Query(value = "select * from quarter q where q.i = ?1", nativeQuery = true)
    void deletequarter(Long empId);
}
