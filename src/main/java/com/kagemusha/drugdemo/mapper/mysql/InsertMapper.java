package com.kagemusha.drugdemo.mapper.mysql;

import com.kagemusha.drugdemo.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InsertMapper {
    Integer insertPerson(Person person);
    Integer insertMedicalOrder(MedicalOrder medicalOrder);
    Integer insertFormula(Integer id);
    Integer insertDrug(Drug drug);
    Integer insertProject(Project project);
    Integer insertCrowd(@Param("id") Integer id, @Param("name") String name);
    Integer insertSpecialCrowd(@Param("id") Integer id, @Param("name") String name);
    Integer insertAllergy(@Param("id") Integer id, @Param("name") String name);
    Integer insertDisease(@Param("id") Integer id, @Param("name") String name);
    Integer insertSymptom(@Param("id") Integer id, @Param("name") String name);

    Integer deleteMO(@Param("id") Integer id);
    Integer deletePerson(@Param("id") Integer id);
}
