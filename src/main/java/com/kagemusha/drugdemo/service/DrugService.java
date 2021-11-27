package com.kagemusha.drugdemo.service;

import com.kagemusha.drugdemo.entity.Item;

import java.util.List;

public interface DrugService {
    Item drugInteractionCheck(String drug);
    Item allergyCheck(String drug, String allergy);
    Item adverseReactionCheck(String drug, String disease, String symptom);
    Item specialCrowdCheck(String drug, String specialCrowd);
    Item repeatedUseCheck(String drug);
    Item contraindicationCheck(String drug, String disease, String symptom);
    Item injectionCompatibilityCheck(List<String> formulaList);
    Item ageCheck(int age, String drug);
}
