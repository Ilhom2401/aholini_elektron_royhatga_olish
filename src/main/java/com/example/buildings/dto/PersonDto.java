package com.example.buildings.dto;

import com.example.buildings.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private Long userId;
    private Long personId;
    private String fullName;
    private String passportInfo;
    private String password;
    private String birthDate;
    private String phoneNumber;
    private String pinfl;
    private String username;
    private EducationStatus educationStatus;
    private EmploymentStatus employmentStatus;
    private Gender gender;
    private HealthStatus healthStatus;
    private SocicalStatus socicalStatus;
    private Long roleId;
    private Long quarterId;

}
