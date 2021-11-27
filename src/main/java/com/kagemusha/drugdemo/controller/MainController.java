package com.kagemusha.drugdemo.controller;


import com.alibaba.fastjson.JSONArray;
import com.kagemusha.drugdemo.entity.*;
import com.kagemusha.drugdemo.mapper.neo.RouteMapper;
import com.kagemusha.drugdemo.service.DrugService;
import com.kagemusha.drugdemo.service.MysqlService;
import com.kagemusha.drugdemo.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.kagemusha.drugdemo.utils.Tool.addQuote;

@RestController
@RequestMapping("/drug")
public class MainController {

    @Autowired
    private DrugService drugService;

    @Autowired
    private MysqlService mysqlService;

    @Autowired
    private RouteService routeService;

    @PostMapping("/receive")
    public String receive(@RequestBody Info info){
        System.out.println(info);
        return "success";
    }

    @PostMapping("/receivePerson")
    public String receivePerson(@RequestBody Person person){
        System.out.println(person);
        return "success";
    }

    @PostMapping("/insert")
    public void insert(@RequestBody Info info){
        try{
            info.calculateAge();
        }catch (ParseException e) { // 捕捉到了解析异常
            e.printStackTrace(); // 打印出错时的栈轨迹信息
        }
        mysqlService.insert(info);
    }

    @RequestMapping("/selectAll")
    public List<Info> select(){
        return mysqlService.selectAll();
    }

    @PostMapping("/insertPerson")
    public void insertPerson(@RequestBody Person person){
        try{
            person.calculateAge();
        }catch (ParseException e) { // 捕捉到了解析异常
            e.printStackTrace(); // 打印出错时的栈轨迹信息
        }
        mysqlService.insertPerson(person);
    }

    @RequestMapping("/selectPerson")
    public List<Person> selectPerson(){
        return mysqlService.selectPerson();
    }

    @PostMapping("/insertMedicalOrder")
    public void insertMedicalOrder(@RequestBody Info info){
        try{
            info.calculateAge();
        }catch (ParseException e) { // 捕捉到了解析异常
            e.printStackTrace(); // 打印出错时的栈轨迹信息
        }
        mysqlService.insertMedicalOrder(info);
    }

    @PostMapping("/selectMedicalOrder")
    public MedicalOrder selectMedicalOrder(@RequestBody Info info){
        Integer cardId = info.getPerson().getCardId();
        return mysqlService.selectMedicalOrder(cardId);
    }

    @RequestMapping("/deleteMOById/{id}")
    public void deleteMOById(@PathVariable("id") Integer id){
        mysqlService.deleteMOById(id);
    }
    @RequestMapping("/deletePersonById/{id}")
    public void deletePersonById(@PathVariable("id") Integer id){
        mysqlService.deletePersonById(id);
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/route")
    public Response route(@RequestBody Info info){
        try{
            info.calculateAge();
        }catch (ParseException e) { // 捕捉到了解析异常
            e.printStackTrace(); // 打印出错时的栈轨迹信息
        }
        List<Checkout> resultList = new ArrayList<>();
        List<String> diseaseList = info.getMedicalOrder().getDiseaseList();
        List<String> symptomList = info.getMedicalOrder().getSymptomList();
        List<String> allergyList = info.getPerson().getAllergyList();
        List<String> specialCrowdList = info.getPerson().getSpecialCrowdList();
        List<String> crowdList = info.getPerson().getCrowdList();
        List<String> drugNameList = new ArrayList<>();
        List<Drug> drugList = new ArrayList<>();
        List<Formula> formulaList = info.getMedicalOrder().getFormulaList();
        List<String> formulaStringList = new ArrayList<>();
        for(Formula formula: formulaList){
            List<String> drugStringList = new ArrayList<>();
            for(Drug drug: formula.getDrugList()){
                drugNameList.add(drug.getName());
                drugList.add(drug);
                drugStringList.add(drug.getName());
            }
            formulaStringList.add(addQuote(drugStringList).toString());
        }
        String gender = info.getPerson().getGender();
        int age = info.getPerson().getAge();
        Response response = new Response();
        List<Item> itemList = new ArrayList<>();
        String drugString = addQuote(drugNameList).toString();
        String diseaseString = addQuote(diseaseList).toString();
        String symptomString = addQuote(symptomList).toString();
        String allergyString = addQuote(allergyList).toString();
        String specialCrowdString = addQuote(specialCrowdList).toString();
        String crowdString = addQuote(crowdList).toString();
        itemList.add(routeService.ageCheck(age,drugString));
        itemList.add(routeService.adverseReactionCheck(drugString, diseaseString, symptomString));
        itemList.add(routeService.repeatedUseCheck(drugString));
        itemList.add(routeService.allergyCheck(drugString, allergyString));
        itemList.add(routeService.contraindicationCheck(drugString, diseaseString, symptomString));
        itemList.add(routeService.drugInteractionCheck(drugString));
        itemList.add(routeService.injectionCompatibilityCheck(formulaStringList));
        itemList.add(routeService.specialCrowdCheck(drugString, specialCrowdString));
        itemList.removeAll(Collections.singleton(null));
        response.setItemList(itemList);
        return response;
    }

    @RequestMapping(value = "/check")
    public List<Checkout> check(@RequestBody Info info){
        try{
            info.calculateAge();
        }catch (ParseException e) { // 捕捉到了解析异常
            e.printStackTrace(); // 打印出错时的栈轨迹信息
        }
        List<Checkout> resultList = new ArrayList<>();
        List<String> diseaseList = info.getMedicalOrder().getDiseaseList();
        List<String> symptomList = info.getMedicalOrder().getSymptomList();
        List<String> allergyList = info.getPerson().getAllergyList();
        List<String> specialCrowdList = info.getPerson().getSpecialCrowdList();
        List<String> crowdList = info.getPerson().getCrowdList();
        List<String> drugNameList = new ArrayList<>();
        List<Drug> drugList = new ArrayList<>();
        List<Formula> formulaList = info.getMedicalOrder().getFormulaList();
        for(Formula formula: formulaList){
            for(Drug drug: formula.getDrugList()){
                drugNameList.add(drug.getName());
                drugList.add(drug);
            }
        }
        String gender = info.getPerson().getGender();
        int age = info.getPerson().getAge();
        resultList.add(drugService.drugInteractionCheck(drugNameList));
        resultList.add(drugService.allergyCheck(drugNameList, allergyList));
        resultList.add(drugService.adverseReactionCheck(drugNameList, diseaseList, symptomList));
        resultList.add(drugService.routeCheck(drugList));
        resultList.add(drugService.ageCheck(age, drugNameList));
        resultList.add(drugService.specialCrowdCheck(drugNameList, specialCrowdList));
        resultList.add(drugService.repeatedUseCheck(drugNameList));
//        resultList.add(drugService.GenderCheck(drugNameList, gender));
        resultList.add(drugService.contraindicationCheck(drugNameList, diseaseList, symptomList));
        resultList.add(drugService.injectionCompatibilityCheck(formulaList));
        System.out.println(resultList);
        return resultList;
    }

//    @RequestMapping(value = "/interaction")
//    public Checkout drugInteractionCheck(@RequestBody Info infoList){
//        List<String> drugName = new ArrayList<>();
//        System.out.println(infoList);
//        for(Formula formulation: infoList.getMedicalOrder().getFormulaList()){
//            for(Drug drug: formulation.getDrugList()){
//                drugName.add(drug.getName());
//            }
//        }
//        System.out.println(drugName);
//        return drugService.drugInteractionCheck(drugName);
//    }

//    @PostMapping(value = "/allergy")
//    public Checkout allergyCheck(@RequestBody InfoList infoList){
//        List<String> drug = new ArrayList<>();
//        for(Drug d: infoList.getDrug()){
//            drug.add(d.getName());
//        }
//        List<String> allergy = infoList.getAllergy();
//        return drugService.allergyCheck(drug, allergy);
//    }
//
//    @PostMapping(value = "/adr")
//    public Checkout adverseReactionCheck(@RequestBody InfoList infoList){
//        List<String> drug = new ArrayList<>();
//        for(Drug d: infoList.getDrug()){
//            drug.add(d.getName());
//        }
//        List<String> disease = infoList.getDisease();
//        List<String> symptom = infoList.getSymptom();
//        return drugService.adverseReactionCheck(drug, disease, symptom);
//    }
//
//    @PostMapping(value = "/route")
//    public Checkout routeCheck(@RequestBody InfoList infoList){
//        List<Drug> drug = new ArrayList<>();
//        for(Drug d: infoList.getDrug()){
//            drug.add(d);
//        }
//        return drugService.routeCheck(drug);
//    }
//
//    @PostMapping(value = "/age")
//    public Checkout ageCheck(@RequestBody InfoList infoList){
//        System.out.println(infoList);
////        List<String> list = new ArrayList<>();
////        list.add("成人");
////        infoList.setCrowd(list);
////        System.out.println(infoList);
//
//        try{
//            infoList.calculateAge();
//        }catch (ParseException e) { // 捕捉到了解析异常
//            e.printStackTrace(); // 打印出错时的栈轨迹信息
//        }
//        System.out.println(infoList);
//        long age = infoList.getAge();
//        List<String> crowd = infoList.getCrowd();
//        List<String> drug = new ArrayList<>();
//        for (Drug d : infoList.getDrug()) {
//            drug.add(d.getName());
//        }
//        return drugService.ageCheck(age, crowd, drug);
//    }
//
//    @PostMapping(value = "/special")
//    public Checkout specialCrowdCheck(@RequestBody InfoList infoList){
//        List<String> drug = new ArrayList<>();
//        for(Drug d: infoList.getDrug()){
//            drug.add(d.getName());
//        }
//        List<String> specialCrowd = infoList.getSpecialCrowd();
//        return drugService.specialCrowdCheck(drug, specialCrowd);
//    }
//
//    @PostMapping(value = "/repeated")
//    public Checkout repeatedUseCheck(@RequestBody InfoList infoList){
//        List<String> drug = new ArrayList<>();
//        for(Drug d: infoList.getDrug()){
//            drug.add(d.getName());
//        }
//        return drugService.repeatedUseCheck(drug);
//    }
//
//    @PostMapping(value = "/gender")
//    public Checkout GenderCheck(@RequestBody InfoList infoList){
//        List<String> drug = new ArrayList<>();
//        for(Drug d: infoList.getDrug()){
//            drug.add(d.getName());
//        }
//        String gender = infoList.getGender();
//        return drugService.GenderCheck(drug, gender);
//    }
//
//    @PostMapping(value = "/contraindication")
//    public Checkout contraindicationCheck(@RequestBody InfoList infoList){
//        List<String> drug = new ArrayList<>();
//        for(Drug d: infoList.getDrug()){
//            drug.add(d.getName());
//        }
//        List<String> disease = infoList.getDisease();
//        List<String> symptom = infoList.getSymptom();
//        return drugService.contraindicationCheck(drug, disease, symptom);
//    }
//
//    @PostMapping(value = "/injection")
//    public Checkout injectionCompatibilityCheck(@RequestBody InfoList infoList){
//        List<String> drug = new ArrayList<>();
//        for(Drug d: infoList.getDrug()){
//            drug.add(d.getName());
//        }
//        return drugService.injectionCompatibilityCheck(drug);
//    }
}
