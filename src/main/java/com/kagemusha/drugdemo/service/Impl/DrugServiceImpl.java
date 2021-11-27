package com.kagemusha.drugdemo.service.Impl;


import com.kagemusha.drugdemo.entity.*;
import com.kagemusha.drugdemo.entity.neo.AgeRes;
import com.kagemusha.drugdemo.mapper.neo.ResultMapper;
import com.kagemusha.drugdemo.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    private ResultMapper resultMapper;

    @Override
    public Checkout drugInteractionCheck(List<String> drug) {
        Checkout checkout = new Checkout();
        checkout.setType("药物相互作用审查");
        checkout.setDecision("通过");
        String reason = "";
        List<String> result = resultMapper.queryDrugInteraction(drug);
        for (String str : result) {
            checkout.setDecision("不通过");
            reason = reason + str;
        }
        checkout.setReason(reason);
        return checkout;
    }

    @Override
    public Checkout allergyCheck(List<String> drug, List<String> allergy) {
        Checkout checkout = new Checkout();
        checkout.setType("过敏审查");
        checkout.setDecision("通过");
        String reason = "";
        List<String> result = resultMapper.queryAllergy(drug, allergy);
        for (String str : result) {
            checkout.setDecision("不通过");
            reason = reason + str;
        }
        checkout.setReason(reason);
        return checkout;
    }

    @Override
    public Checkout adverseReactionCheck(List<String> drug, List<String> disease, List<String> symptom) {
        Checkout checkout = new Checkout();
        checkout.setType("不良反应审查");
        checkout.setDecision("通过");
        String reason = "";
        List<String> result = resultMapper.queryAdverseReaction(drug, disease, symptom);
        for (String str : result) {
            checkout.setDecision("不通过");
            reason = reason + str;
        }
        checkout.setReason(reason);
        return checkout;
    }

    @Override
    public Checkout routeCheck(List<Drug> drug) {
        Checkout checkout = new Checkout();
        checkout.setType("给药途径审查");
        checkout.setDecision("通过");
        String reason = "";
//        for (Drug d : drug) {
//            if (resultMapper.queryRoute(d.getName(), d.getRoute()).isEmpty()) {
//                checkout.setDecision("不通过");
//                reason = reason + String.format("%s 不能 %s;", d.getName(), d.getRoute());
//            }
//        }
        checkout.setReason(reason);
        return checkout;
    }

    @Override
    public Checkout specialCrowdCheck(List<String> drug, List<String> specialCrowd){
        Checkout checkout = new Checkout();
        checkout.setType("特殊人群审查");
        checkout.setDecision("通过");
        String reason = "";
        List<String> result = resultMapper.querySpecialCrowd(drug, specialCrowd);
        for (String str : result) {
            checkout.setDecision("不通过");
            reason = reason + str;

        }
        checkout.setReason(reason);
        return checkout;
    }

    @Override
    public Checkout repeatedUseCheck(List<String> drug){
        Checkout checkout = new Checkout();
        checkout.setType("重复用药审查");
        checkout.setDecision("通过");
        String reason = "";
        List<String> result = resultMapper.queryRepeatedUse(drug);
        for (String str : result) {
            checkout.setDecision("不通过");
            reason = reason + str;
        }
        checkout.setReason(reason);
        return checkout;
    }

    @Override
    public Checkout GenderCheck(List<String> drug, String gender){
        Checkout checkout = new Checkout();
        checkout.setType("性别用药审查");
        checkout.setDecision("通过");
        String reason = "";
        if (gender.equals("男")) {
            List<String> result = resultMapper.queryGenderWoman(drug);
            for (String str : result) {
                checkout.setDecision("不通过");
                reason = reason + str;
            }
        }
        if (gender.equals("女")) {
            List<String> result = resultMapper.queryGenderMan(drug);
            for (String str : result) {
                checkout.setDecision("不通过");
                reason = reason + str;
            }
        }
        checkout.setReason(reason);
        return checkout;
    }

    @Override
    public Checkout contraindicationCheck(List<String> drug, List<String> disease, List<String> symptom){
        Checkout checkout = new Checkout();
        checkout.setType("禁忌症审查");
        checkout.setDecision("通过");
        String reason = "";
        List<String> result = resultMapper.queryContraindication(drug, disease, symptom);
        for (String str : result) {
            checkout.setDecision("不通过");
            reason = reason + str;
        }
        checkout.setReason(reason);
        return checkout;
    }

    @Override
    public Checkout injectionCompatibilityCheck(List<Formula> formulations){
        Checkout checkout = new Checkout();
        checkout.setType("体外注射剂配伍审查");
        checkout.setDecision("通过");
        String reason = "";
        for(Formula formulation: formulations) {
            List<String> drugs = new ArrayList<>();
            for (Drug drug : formulation.getDrugList())
                drugs.add(drug.getName());
//            System.out.println(drugs);
            List<String> result = resultMapper.queryInjectionCompatibility(drugs);
            for (String str : result) {
                checkout.setDecision("不通过");
                reason = reason + str;
            }
        }
        checkout.setReason(reason);
        return checkout;
    }

//    @Override
//    public Checkout dosageCheck(List<Drug> drug, List<String> disease, List<String> symptom, List<String> specialCrowd,Integer age, String ageUnit){
//        Checkout checkout = new Checkout();
//        checkout.setDecision("剂量审查：通过");
//        String reason = "";
//
//        return checkout;
//    }

    @Override
    public Checkout ageCheck(int age, List<String> drugList) {
        Checkout checkout = new Checkout();
        checkout.setType("年龄审查");
        checkout.setDecision("通过");
        String reason = "";
        List<AgeRes> ageResList = resultMapper.queryAge(drugList);
        for (AgeRes ageRes: ageResList){
            String grade = ageRes.getGrade();
            String unit = ageRes.getUnit();
            Integer num = ageRes.getNum();
            Integer num2 = ageRes.getNum2();
            String crowd = ageRes.getCrowd();
            if(unit == null){
                continue;
            }
            if(unit.contains("天_之间") && num2!=null){
                if(age>=num && age <=num2){
                    checkout.setDecision("不通过");
                    reason = reason + crowd + "," + grade;
                }
            }
            else if(unit.contains("天_以上") && age >= num){
                checkout.setDecision("不通过");
                reason = reason + crowd + "," + grade;
            }
            else if(unit.contains("天_以下") && age <= num){
                checkout.setDecision("不通过");
                reason = reason + crowd + "," + grade;
            }
        }
        checkout.setReason(reason);
        return checkout;
    }

}
