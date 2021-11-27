package com.kagemusha.drugdemo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Checkout {
    private String type;
    private String decision;
    private String reason;
}
