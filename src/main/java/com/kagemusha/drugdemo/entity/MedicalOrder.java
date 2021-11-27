package com.kagemusha.drugdemo.entity;

import lombok.Data;

import java.util.List;

@Data
public class MedicalOrder {
    private Integer moId;
    private Integer cardId;
    private String bsCondition;
    private String hospital;
    private String department;
    private String doctorWay;
    private String startDate;
    private String endDate;
    private String recordDate;
    private List<String> diseaseList;
    private List<String> symptomList;
    private List<Formula> formulaList;
    private List<Project> projectList;
}
