package com.example.buildings.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EmploymentStatus {
    DAV_SECTOR_BAND(1, "Davlat sektorida rasmiy band"),
    XUS_SECTOR_BAND(2, "Xususiy sektorda rasmiy band"),
    ARMIYAGA(3, "Muddatli harbiy xizmatda"),
    VAQTINCHA_ISHSIZ(4, "Vaqtincha ishsiz"),
    MIGRATSIYA(5, "Migratsiyaga ketgan");

    private int code;
    private String name;

    public List<EmploymentStatus> getList(){
        return Arrays.asList(EmploymentStatus.values());
    }
}
