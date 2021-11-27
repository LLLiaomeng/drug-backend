package com.kagemusha.drugdemo.service.Impl;

import com.kagemusha.drugdemo.entity.*;
import com.kagemusha.drugdemo.mapper.mysql.InsertMapper;
import com.kagemusha.drugdemo.mapper.mysql.SelectMapper;
import com.kagemusha.drugdemo.service.MysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MysqlServiceImpl implements MysqlService {

    @Autowired
    InsertMapper insertMapper;

    @Autowired
    SelectMapper selectMapper;

    @Override
    public void insert(Info info){
        Person person = info.getPerson();
        Integer cardId = insertMapper.insertPerson(person);

        MedicalOrder medicalOrder = info.getMedicalOrder();
        medicalOrder.setCardId(cardId);
        Integer moId = insertMapper.insertMedicalOrder(medicalOrder);

        for(Formula formula: medicalOrder.getFormulaList()){
            Integer formulaId = insertMapper.insertFormula(moId);
            for(Drug drug: formula.getDrugList()){
                drug.setFormulaId(formulaId);
                Integer id = insertMapper.insertDrug(drug);
            }
        }
        for(Project project: medicalOrder.getProjectList()){
            project.setMoId(moId);
            Integer id = insertMapper.insertProject(project);
        }

        for (String crowd: person.getCrowdList()){
            Integer id = insertMapper.insertCrowd(cardId, crowd);
        }
        for (String specialCrowd: person.getSpecialCrowdList()){
            Integer id = insertMapper.insertSpecialCrowd(cardId, specialCrowd);
        }
        for (String allergy: person.getAllergyList()){
            Integer id = insertMapper.insertAllergy(cardId, allergy);
        }
        for (String disease: medicalOrder.getDiseaseList()){
            Integer id = insertMapper.insertDisease(moId, disease);
        }
        for (String symptom: medicalOrder.getSymptomList()) {
            Integer id = insertMapper.insertSymptom(moId, symptom);
        }
    }

    @Override
    public List<Info> selectAll(){
        List<Info> infoList = new ArrayList<>();
        List<Person> personList = selectMapper.selectPerson();
        for(Person person:personList){
           person.setCrowdList(selectMapper.selectCrowd(person.getCardId()));
            person.setSpecialCrowdList(selectMapper.selectSpecialCrowd(person.getCardId()));
            person.setAllergyList(selectMapper.selectAllergy(person.getCardId()));
            List<MedicalOrder> medicalOrderList = selectMapper.selectMedicalOrder(person.getCardId());
            for(MedicalOrder medicalOrder: medicalOrderList){
                Info info = new Info();
                info.setPerson(person);

                medicalOrder.setDiseaseList(selectMapper.selectDisease(medicalOrder.getMoId()));
                medicalOrder.setSymptomList(selectMapper.selectSymptom(medicalOrder.getMoId()));
                medicalOrder.setProjectList(selectMapper.selectProject(medicalOrder.getMoId()));

                List<Integer> formulaIdList = selectMapper.selectFormula(medicalOrder.getMoId());
                List<Formula> formulaList = new ArrayList<>();
                for(Integer formulaId: formulaIdList){
                    Formula formula = new Formula();
                    formula.setDrugList(selectMapper.selectDrug(formulaId));
                    formulaList.add(formula);
                }
                medicalOrder.setFormulaList(formulaList);
                //诊疗项目
                info.setMedicalOrder(medicalOrder);
                infoList.add(info);
            }
        }
        return infoList;
    }

    @Override
    public void insertPerson(Person person){
        Integer cardId = insertMapper.insertPerson(person);
        for (String crowd: person.getCrowdList()){
            try{
                Integer id = insertMapper.insertCrowd(cardId, crowd);
            }catch(Exception e){
                ;
            }
        }
        for (String specialCrowd: person.getSpecialCrowdList()){
            Integer id = insertMapper.insertSpecialCrowd(cardId, specialCrowd);
        }
        for (String allergy: person.getAllergyList()){
            Integer id = insertMapper.insertAllergy(cardId, allergy);
        }
    }

    @Override
    public List<Person> selectPerson(){
        List<Person> personList = selectMapper.selectPerson();
        for(Person person:personList) {
            person.setCrowdList(selectMapper.selectCrowd(person.getCardId()));
            person.setSpecialCrowdList(selectMapper.selectSpecialCrowd(person.getCardId()));
            person.setAllergyList(selectMapper.selectAllergy(person.getCardId()));
        }
        return personList;
    }

    @Override
    public void insertMedicalOrder(Info info){
        Integer cardId = info.getPerson().getCardId();
        MedicalOrder medicalOrder = info.getMedicalOrder();
        medicalOrder.setCardId(cardId);
        Integer moId = insertMapper.insertMedicalOrder(medicalOrder);

        for(Formula formula: medicalOrder.getFormulaList()){
            Integer formulaId = insertMapper.insertFormula(moId);
            for(Drug drug: formula.getDrugList()){
                drug.setFormulaId(formulaId);
                Integer id = insertMapper.insertDrug(drug);
            }
        }
        for(Project project: medicalOrder.getProjectList()){
            project.setMoId(moId);
            Integer id = insertMapper.insertProject(project);
        }
        for (String disease: medicalOrder.getDiseaseList()){
            Integer id = insertMapper.insertDisease(moId, disease);
        }
        for (String symptom: medicalOrder.getSymptomList()) {
            Integer id = insertMapper.insertSymptom(moId, symptom);
        }
    }

    @Override
    public MedicalOrder selectMedicalOrder(Integer cardId){
        MedicalOrder medicalOrder = selectMapper.selectMedicalOrderLatest(cardId);
        medicalOrder.setDiseaseList(selectMapper.selectDisease(medicalOrder.getMoId()));
        medicalOrder.setSymptomList(selectMapper.selectSymptom(medicalOrder.getMoId()));
        medicalOrder.setProjectList(selectMapper.selectProject(medicalOrder.getMoId()));

        List<Integer> formulaIdList = selectMapper.selectFormula(medicalOrder.getMoId());
        List<Formula> formulaList = new ArrayList<>();
        for(Integer formulaId: formulaIdList){
            Formula formula = new Formula();
            formula.setDrugList(selectMapper.selectDrug(formulaId));
            formulaList.add(formula);
        }
        medicalOrder.setFormulaList(formulaList);
        return medicalOrder;
    }

    @Override
    public void deleteMOById(Integer id){
        insertMapper.deleteMO(id);
    }

    @Override
    public void deletePersonById(Integer id){
        insertMapper.deletePerson(id);
    }

}
