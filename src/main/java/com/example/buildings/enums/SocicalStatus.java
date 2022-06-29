package com.example.buildings.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SocicalStatus {
    IJTIM_HIM_MUHTOJ(1, "Ijtimoiy himoyaga muhtoj"),
    NOGIRONLIGI_BOR(2, "Nogironligi mavjud"),
    YAXSHI(3, "o'ziga to'q");

    private int code;
    private String name;

    public List<SocicalStatus> getList(){
        return Arrays.asList(SocicalStatus.values());
    }
}
