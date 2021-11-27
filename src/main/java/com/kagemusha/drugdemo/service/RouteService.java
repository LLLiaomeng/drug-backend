package com.kagemusha.drugdemo.service;

import com.alibaba.fastjson.JSONArray;
import com.kagemusha.drugdemo.entity.Checkout;
import com.kagemusha.drugdemo.entity.Drug;
import com.kagemusha.drugdemo.entity.Formula;
import com.kagemusha.drugdemo.entity.Item;

import java.util.List;
import java.util.Map;

public interface RouteService {
    Item drugInteractionCheck(String drug);
    Item allergyCheck(String drug, String allergy);
    Item adverseReactionCheck(String drug, String disease, String symptom);
    Item specialCrowdCheck(String drug, String specialCrowd);
    Item repeatedUseCheck(String drug);
    Item contraindicationCheck(String drug, String disease, String symptom);
    Item injectionCompatibilityCheck(List<String> formulaList);
    Item ageCheck(int age, String drug);
}
