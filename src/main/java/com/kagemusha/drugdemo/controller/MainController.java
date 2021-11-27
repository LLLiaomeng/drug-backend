package com.kagemusha.drugdemo.controller;


import com.kagemusha.drugdemo.entity.*;
import com.kagemusha.drugdemo.service.DrugService;
import com.kagemusha.drugdemo.service.MysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.kagemusha.drugdemo.utils.Tool.addQuote;

@RestController
@RequestMapping("/drug")
public class MainController {
    @Autowired
    private MysqlService mysqlService;

    @Autowired
    private DrugService DrugService;

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

    @RequestMapping(value = "/check")
    public Response check(@RequestBody Info info){
        try{
            info.calculateAge();
        }catch (ParseException e) { // 捕捉到了解析异常
            e.printStackTrace(); // 打印出错时的栈轨迹信息
        }
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
        itemList.add(DrugService.ageCheck(age,drugString));
        itemList.add(DrugService.adverseReactionCheck(drugString, diseaseString, symptomString));
        itemList.add(DrugService.repeatedUseCheck(drugString));
        itemList.add(DrugService.allergyCheck(drugString, allergyString));
        itemList.add(DrugService.contraindicationCheck(drugString, diseaseString, symptomString));
        itemList.add(DrugService.drugInteractionCheck(drugString));
        itemList.add(DrugService.injectionCompatibilityCheck(formulaStringList));
        itemList.add(DrugService.specialCrowdCheck(drugString, specialCrowdString));
        itemList.removeAll(Collections.singleton(null));
        response.setItemList(itemList);
        return response;
    }
}
