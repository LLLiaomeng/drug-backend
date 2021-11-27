package com.kagemusha.drugdemo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Formula {
    private List<Drug> drugList;
}
