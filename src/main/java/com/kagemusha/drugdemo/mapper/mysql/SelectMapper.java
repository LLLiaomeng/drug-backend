package com.kagemusha.drugdemo.mapper.mysql;

import com.kagemusha.drugdemo.entity.Drug;
import com.kagemusha.drugdemo.entity.MedicalOrder;
import com.kagemusha.drugdemo.entity.Person;
import com.kagemusha.drugdemo.entity.Project;

import java.util.List;

public interface SelectMapper {
    List<Person> selectPerson();
    List<MedicalOrder> selectMedicalOrder(Integer cardId);
    MedicalOrder selectMedicalOrderLatest(Integer cardId);
    List<Integer> selectFormula(Integer moId);
    List<Drug> selectDrug(Integer formulaId);
    List<Project> selectProject(Integer moId);
    List<String> selectCrowd(Integer cardId);
    List<String> selectSpecialCrowd(Integer cardId);
    List<String> selectAllergy(Integer cardId);
    List<String> selectDisease(Integer moId);
    List<String> selectSymptom(Integer moId);



}
