package com.kagemusha.drugdemo.mapper.neo;


import com.kagemusha.drugdemo.entity.neo.AgeRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResultMapper {
    List<String> queryDrugInteraction(@Param("drug") List<String> drug);
    List<String> queryAllergy(@Param("drug") List<String> drug, @Param("allergy") List<String> allergy);
    List<String> queryAdverseReaction(@Param("drug") List<String> drug, @Param("disease") List<String> disease, @Param("symptom") List<String> symptom);
    List<String> queryRoute(@Param("drug") String drug, @Param("route") String route);
    List<String> querySpecialCrowd(@Param("drug") List<String> drug, @Param("specialCrowd") List<String> specialCrowd);
    List<String> queryRepeatedUse(@Param("drug") List<String> drug);
    List<String> queryGenderWoman(@Param("drug") List<String> drug);
    List<String> queryGenderMan(@Param("drug") List<String> drug);
    List<String> queryContraindication(@Param("drug") List<String> drug, @Param("disease") List<String> disease, @Param("symptom") List<String> symptom);
    List<String> queryDosage(@Param("drug") String drug, @Param("disease") List<String> disease, @Param("symptom") List<String> symptom, @Param("specialCrowd") List<String> specialCrowd,
                             @Param("route") String route, @Param("age") Integer age, @Param("ageUnit") String ageUnit);
    List<String> queryInjectionCompatibility(@Param("drug") List<String> drug);

    List<AgeRes> queryAge(@Param("drug") List<String> drug);

}