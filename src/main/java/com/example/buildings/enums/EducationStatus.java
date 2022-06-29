package com.example.buildings.enums;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EducationStatus {
    MAKTABGACHA_TALIM(1, "Maktabgacha talim"),
    OLIY_TALIM(2, "Oliy talim"),
    UMUMIY_ORTA_TRALIM(3, "Umumiy o'rta talim"),
    PROFESSIONAL_TALIM(4, "Professional talim"),
    OLIY_TALIMDAN_KEYINGI_TALIM(5, "Oliy talimdan keyingi talim");

    private int code;
    private String name;

    public List<EducationStatus> getList(){
        return Arrays.asList(EducationStatus.values());
    }
}
