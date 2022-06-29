package com.example.buildings.repository;

import com.example.buildings.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "select e.id from person e " +
            "    inner join role r on r.id = e.role_id " +
            "    inner join role_actions ra on r.id = ra.role_entity_id " +
            "    inner join action a on a.id = ra.actions_id and a.name = ?1 " +
            "WHERE e.id = ?2", nativeQuery = true)
    Long access( String action, Long userId);

    Optional<Person> findByUsername(String username);

    @Query(value = "select * from person where role_id = 3", nativeQuery = true)
    List<Person> getChairmansByRoleId();

    Optional<Person> findByUsernameAndPassword(String username, String password);

    @Query(value = "select * from employee where project_id=?1", nativeQuery = true)
    List<Person> getListByProjectId(Long id);

    @Query(value = "delete from person where quarter_id in (?1)", nativeQuery = true)
    void deleteByQuarterId(Long id);

    @Query(value = "select * from person where quarter_id = ?1 and role_id = 2", nativeQuery = true)
    List<Person> getListByQurtId(Long id);

    boolean existsByQuarterId(Long aLong);
}
