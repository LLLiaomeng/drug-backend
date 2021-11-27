package com.kagemusha.drugdemo.service.Impl;

import com.kagemusha.drugdemo.entity.Item;
import com.kagemusha.drugdemo.entity.neo.Node;
import com.kagemusha.drugdemo.mapper.neo.ResultMapper;
import com.kagemusha.drugdemo.mapper.neo.RouteMapper;
import com.kagemusha.drugdemo.service.RouteService;
import com.kagemusha.drugdemo.utils.GsonUtils;
import com.kagemusha.drugdemo.utils.NeoQuery;
import com.kagemusha.drugdemo.utils.ResponseBuild;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.kagemusha.drugdemo.utils.Tool.addQuote;

@Service
public class RouteServiceImpl implements RouteService {
    @Override
    public Item drugInteractionCheck(String drug) {
        JSONArray pathList = NeoQuery.run("match p=(drug1:`药品`)-[:成分*0..2]->()-[:相互作用*2]->()<-[:成分*0..2]-(drug2:`药品`)\n" +
                "where drug1.name in " +  drug + " and drug2.name in " + drug + "\n" +
                "return p", new int[]{0,-1});
        if (pathList.isEmpty())
            return null;
        int[] indexList = new int[]{0,-1};
        List<List<String>> nodeLists = NeoQuery.filterNode(pathList, indexList);
        return ResponseBuild.unpass("药物相互作用审查", "不通过", nodeLists, pathList);
    }

    @Override
    public Item allergyCheck(String drug, String allergy) {
        JSONArray pathList = NeoQuery.run("match p=(drug1:`药品`)-[:成分*2]->(drug2:`药物`)\n" +
                "where drug1.name in " + drug + " and drug2.name in " + allergy +"\n" +
                "return p", new int[]{0,2});
        if (pathList.isEmpty())
            return null;
        int[] indexList = new int[]{0,2};
        List<List<String>> nodeLists = NeoQuery.filterNode(pathList, indexList);
        return ResponseBuild.unpass("过敏审查", "不通过", nodeLists, pathList);
    }


    @Override
    public Item adverseReactionCheck(String drug, String disease, String symptom) {
        JSONArray pathList = NeoQuery.run("match p=(drug:`药品`)-[:不良反应*2]->(ds)\n" +
                "where drug.name in " + drug + " and (ds.name in " + disease + " or ds.name in " + symptom + ")\n" +
                "return p", new int[]{0,2});
        if (pathList.isEmpty())
            return null;
        int[] indexList = new int[]{0,2};
        List<List<String>> nodeLists = NeoQuery.filterNode(pathList, indexList);
        return ResponseBuild.unpass("不良反应审查", "不通过", nodeLists, pathList);
    }

    @Override
    public Item specialCrowdCheck(String drug, String specialCrowd) {
        JSONArray pathList = NeoQuery.run("match p1=(drug:`药品`)-[:用药]->(fact:`fact`)-[:用药]->(crowd:`人群`),p2=(fact)-[:用药结果]->(useResult:`用药结果级别`)\n" +
                "where drug.name in " + drug + " and crowd.name in " + specialCrowd + "\n" +
                "with {p1:p2,p2:p2} as p\n" +
                "return p", new int[]{0,2,4});
        if (pathList.isEmpty())
            return null;
        int[] indexList = new int[]{2,0,4};
        List<List<String>> nodeLists = NeoQuery.filterNode(pathList, indexList);
        return ResponseBuild.unpass("特殊人群审查", "不通过", nodeLists, pathList);
    }
    @Override
    public Item repeatedUseCheck(String drug) {
        JSONArray pathList = NeoQuery.run("match p=(drug1:药品)-[:成分*0..2]->(:药物)<-[:成分*0..2]-(drug2:药品)\n" +
                "where drug1.name in " + drug + "and drug2.name in" + drug + " and id(drug1) <> id(drug2)\n" +
                "return p", new int[]{0,2,4});
        if (pathList.isEmpty())
            return null;
        int[] indexList = new int[]{0, 4, 2};
        List<List<String>> nodeLists = NeoQuery.filterNode(pathList, indexList);
        return ResponseBuild.unpass("重复用药审查", "不通过", nodeLists, pathList);
    }

    @Override
    public Item contraindicationCheck(String drug, String disease, String symptom) {
        JSONArray pathList = NeoQuery.run("match p=(drug:`药品`)-[:用药*2]->()-[:患有*2]->(ds)\n" +
                "where drug.name in " + drug + " and (ds.name in " + disease + " or ds.name in " + symptom + ")\n" +
                "return p", new int[]{0,4});
        if (pathList.isEmpty())
            return null;
        int[] indexList = new int[]{0,4};
        List<List<String>> nodeLists = NeoQuery.filterNode(pathList, indexList);
        return ResponseBuild.unpass("禁忌症审查", "不通过", nodeLists, pathList);
    }

    @Override
    public Item injectionCompatibilityCheck(List<String> formulaList) {
        JSONArray pathList = new JSONArray();
        for (String formula : formulaList) {
            pathList.addAll(NeoQuery.run("match p=(drug1:`药品`)-[:成分*0..2]->()-[:体外配伍*2]->()-[:成分*0..2]-(drug2:`药品`)\n" +
                    "where drug1.name in " + formula + " and drug2.name in " + formula + "\n" +
                    "return p", new int[]{0,-1}));
        }
        if (pathList.isEmpty())
            return null;
        int[] indexList = new int[]{0, -1};
        List<List<String>> nodeLists = NeoQuery.filterNode(pathList, indexList);
        return ResponseBuild.unpass("配伍审查", "不通过", nodeLists, pathList);
    }
    @Override
    public Item ageCheck(int age, String drug) {
        JSONArray pathList = NeoQuery.run("match (drug:`药品`)\n" +
                "where drug.name in " + drug + "\n" +
                "match p1=(drug)-[:用药]->(fact:`fact`)-[:用药]->(crowd:`人群`), p2=(fact:`fact`)-[:用药结果]-(grade)\n" +
                "match p3=(crowd)-[:细粒度化]-(fact2:`fact`)-[:数字]-(num:`数字`), p4=(fact2)-[:单位]-(unit:`单位`)\n" +
                "optional match p5=(fact2)-[:数字]-(num2:`数字`)\n" +
                "where toInt(num2.name) > toInt(num.name)\n" +
                "with {p1:p1,p2:p2,p3:p3,p4:p4,p5:p5} as p \n" +
                "return p", new int[]{0,2,4,7,9});
//        System.out.println("match (drug:`药品`)\n" +
//                "where drug.name in " + drug + "\n" +
//                "match p1=(drug)-[:用药]->(fact:`fact`)-[:用药]->(crowd:`人群`), p2=(fact)-[:用药结果]-(grade)\n" +
//                "match p3=(crowd)-[:细粒度化]-(fact2)-[:数字]-(num), p4=(fact2)-[:单位]-(unit) \n " +
//                "optional match p5=(fact2)-[:数字]-(num2)\n" +
//                "where toInt(num2.name) > toInt(num.name)\n" +
//                "with {p1:p1,p2:p2,p3:p3,p4:p4,p5:p5} as p \n" +
//                "return p");
        if (pathList.isEmpty())
            return null;
        return ResponseBuild.unpassAge(age, pathList);
    }
}

