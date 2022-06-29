package com.example.buildings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuarterDto {
    private Long userId;
    private Long districtId;
    private Long quarterId;
    private int code;
    private String name;
}
