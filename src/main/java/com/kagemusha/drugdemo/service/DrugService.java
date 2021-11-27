package com.kagemusha.drugdemo.service;


import com.kagemusha.drugdemo.entity.Checkout;
import com.kagemusha.drugdemo.entity.Drug;
import com.kagemusha.drugdemo.entity.Formula;

import java.util.List;

public interface DrugService {
    Checkout drugInteractionCheck(List<String> drug);
    Checkout allergyCheck(List<String> drug, List<String> allergy);
    Checkout adverseReactionCheck(List<String> drug, List<String> disease, List<String> symptom);
    Checkout routeCheck(List<Drug> drug);
    Checkout specialCrowdCheck(List<String> drug, List<String> specialCrowd);
    Checkout repeatedUseCheck(List<String> drug);
    Checkout GenderCheck(List<String> drug, String gender);
    Checkout contraindicationCheck(List<String> drug, List<String> disease, List<String> symptom);
//    Checkout dosageCheck(List<Drug> drug, List<String> disease, List<String> symptom, List<String> specialCrowd, Integer age, String ageUnit);
    Checkout injectionCompatibilityCheck(List<Formula> formulations);
    Checkout ageCheck(int age, List<String> drugList);

}
