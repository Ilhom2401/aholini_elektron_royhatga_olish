package com.example.buildings.entity;

import com.example.buildings.entity.base.BaseEntity;
import com.example.buildings.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Person extends BaseEntity {

    private String fullName;
    @Column(unique = true, nullable = false)
    private String passportInfo;

    @Column(unique = true, nullable = false)
    private String pinfl;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String birthDate;
    private String phoneNumber;

    @OneToOne
    private RoleEntity role;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @Enumerated(EnumType.STRING)
    private EducationStatus educationStatus;

    @Enumerated(EnumType.STRING)
    private SocicalStatus socicalStatus;

    @Enumerated(EnumType.STRING)
    private HealthStatus healthStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    private Quarter quarter;

}
