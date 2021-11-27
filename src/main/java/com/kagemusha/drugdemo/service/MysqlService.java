package com.kagemusha.drugdemo.service;

import com.kagemusha.drugdemo.entity.Info;
import com.kagemusha.drugdemo.entity.MedicalOrder;
import com.kagemusha.drugdemo.entity.Person;

import java.util.List;

public interface MysqlService {
    void insert(Info info);
    List<Info> selectAll();
    void insertPerson(Person person);
    List<Person> selectPerson();
    void insertMedicalOrder(Info info);
    MedicalOrder selectMedicalOrder(Integer cardId);
    void deletePersonById(Integer id);
    void deleteMOById(Integer id);
}
