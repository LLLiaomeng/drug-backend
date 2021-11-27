//package com.kagemusha.drugdemo.service.Impl;
//
//import com.kagemusha.drugdemo.entity.Drug;
//import com.kagemusha.drugdemo.entity.InfoList;
//import com.kagemusha.drugdemo.service.DrugService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class DrugServiceImplTest {
//    @Autowired
//    DrugService drugService;
//
//    @Test
//    void find(){
//        List<Drug> drug = new ArrayList<>();
//        Drug drug1 = new Drug();
//        drug1.setName("注射用青霉素钠");
//        drug.add(drug1);
//        Drug drug2 = new Drug();
//        drug2.setName("红霉素软膏");
//        drug.add(drug2);
//        List<String> specialCrowd = new ArrayList<>();
//        String specialCrowd1 = "成人";
//        specialCrowd.add(specialCrowd1);
//        InfoList infolist = new InfoList();
//        infolist.setDrug(drug);
//        infolist.setSpecialCrowd(specialCrowd);
////        System.out.println(drugService.ageCheck(infolist));
//    }
//}