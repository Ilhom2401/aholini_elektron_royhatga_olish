package com.example.buildings.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum HealthStatus {
    SOGLOM_YOSH(1, "Sog'lom "),
    RUHIY_KASAL(2, "Ruhiy_kasal"),
    KARONA_VIRUS(3, "Korona virus"),
    OITS(4, "Oits"),
    NARKOMAND(5, "Giyohvand"),
    QANDLI_DIABED(6, "Qadli diabed");

    private int code;
    private String name;

    public List<HealthStatus> getList(){
        return Arrays.asList(HealthStatus.values());
    }
}
