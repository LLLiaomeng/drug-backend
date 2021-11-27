package com.kagemusha.drugdemo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kagemusha.drugdemo.entity.Item;
import lombok.Data;

import java.util.Iterator;
import java.util.List;

@Data
public class ResponseBuild {

    public static Item pass(){
        return null;
    }
    public static Item unpass(String type, String decision, List<List<String>> nodeLists, JSONArray pathList){
        Item item = new Item();
        item.setType(type);
        item.setDecision(decision);
        item.setPathList(pathList);
        String reason = "";
        String str = "";
        switch (type){
            case "药物相互作用审查":
                str = "%s与%s发生相互作用；";
                break;
            case "不良反应审查":
                str = "%s会导致%s；";
                break;
            case "过敏审查":
                str = "%s含有过敏物质%s；";
                break;
            case "重复用药审查":
                str = "%s和%s中都含有%s；";
                break;
            case "特殊人群审查":
                str = "%s使用%s的建议为：%s；";
                break;
            case "禁忌症审查":
                str = "%s不能用于治疗%s；";
                break;
            case "配伍审查":
                str = "%s不能与%s进行配伍；";
                break;
        }
        for (List<String> nodeList : nodeLists){
            reason += String.format(str, nodeList.toArray());
        }
        item.setReason(reason);
        return item;
    }

    public static Item unpassAge(int age, JSONArray pathList){
        Item item = new Item();
        String reason = "";
        Iterator<Object> iterator = pathList.iterator();
        while (iterator.hasNext()){
            JSONArray nodeList = ((JSONObject)iterator.next()).getJSONObject("p").getJSONArray("nodes");
            String drug = nodeList.getJSONObject(0).getJSONObject("properties").getString("name");
            String grade = nodeList.getJSONObject(4).getJSONObject("properties").getString("name");
            String unit = nodeList.getJSONObject(9).getJSONObject("properties").getString("name");
            int num = Math.round(Float.parseFloat(nodeList.getJSONObject(7).getJSONObject("properties").getString("name")));
            String crowd = nodeList.getJSONObject(2).getJSONObject("properties").getString("name");
            Integer num2 = null;
            if (nodeList.size() == 12){
               num2 =  Integer.valueOf(nodeList.getJSONObject(11).getJSONObject("properties").getString("name"));
            }
            if(unit == null){
                continue;
            }
            Boolean isUnpass = false;
            if(unit.contains("天_之间") && num2!=null){
                if(age>=num && age <=num2){
                    isUnpass = true;
                }
            }
            else if(unit.contains("天_以上") && age >= num){
                isUnpass = true;
            }
            else if(unit.contains("天_以下") && age <= num){
                isUnpass = true;
            }
            if (isUnpass){
                item.setDecision("不通过");
                reason = reason + crowd + "使用" + drug + "的建议为：" + grade + "；";
            }else{
                iterator.remove();
            }
        }
        if (item.getDecision().isEmpty()){
            return null;
        }else{
            item.setReason(reason);
            item.setType("年龄审查");
            item.setPathList(pathList);
            return item;
        }
    }
}
