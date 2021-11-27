package com.kagemusha.drugdemo.entity;

import lombok.Data;

@Data
public class Drug {
    private Integer formulaId;
    private String name;
    private String route;
    private Double dosageNum;
    private String dosageUnit;
    private Integer freqNum;
    private String freqUnit;
    private Integer amount;
    private String code;
}
