package com.kagemusha.drugdemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.neo4j.driver.v1.*;

import java.util.*;

@Data
public class NeoQuery {
    private static String URI = "bolt://localhost:7687";
    private static String USERNAME = "neo4j";
    private static String PASSWORD = "20200202";
    private static Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );

    public static JSONArray run(String cypher, int[] indexList){
        Session session = driver.session();
        StatementResult result = session.run(cypher);
//        JSONArray pathList = removeDuplicate(deleteVal(JSONArray.parseArray(GsonUtils.toJson(result.list(Record::asMap)))),indexList);
        JSONArray pathList1 = JSONArray.parseArray(GsonUtils.toJson(result.list(Record::asMap)));
        System.out.println(pathList1);
        JSONArray pathList2 = mergePath(pathList1);
        System.out.println(pathList2);
        JSONArray pathList3 = deleteVal(pathList2);
        System.out.println(pathList3);
        JSONArray pathList4 = removeDuplicate(pathList3, indexList);
        System.out.println(pathList4);

        return pathList4;
    }

    public static  JSONArray mergePath(JSONArray pathList){
        for(Object path : pathList){
            JSONObject p = ((JSONObject)path).getJSONObject("p");
            JSONArray relationships = new JSONArray();
            JSONArray nodes = new JSONArray();
            JSONArray segments = new JSONArray();
            if (!p.containsKey("p1")){
                return pathList;
            }
            int num = p.size();
            for(int i = 1; i <= num; i++){
                relationships.addAll(p.getJSONObject("p"+i).getJSONArray("relationships"));
                nodes.addAll(p.getJSONObject("p"+i).getJSONArray("nodes"));
                segments.addAll(p.getJSONObject("p"+i).getJSONArray("segments"));
                p.remove("p"+i);
            }
            p.put("relationships", relationships);
            p.put("nodes", nodes);
            p.put("segments", segments);
        }
        return pathList;
    }

    public static JSONArray removeDuplicate(JSONArray pathList, int[] indexList){
        Map<Set<String>, Integer> map = new HashMap<>();
        Iterator<Object> iterator = pathList.iterator();
        while (iterator.hasNext()){
            JSONArray nodeList = ((JSONObject)iterator.next()).getJSONObject("p").getJSONArray("nodes");
            int len = nodeList.size();
            Set<String> set = new HashSet();
            for (int index : indexList){
                if (index == -1){
                    index = len - 1;
                }
                set.add(nodeList.getJSONObject(index).getJSONObject("properties").getString("name"));
            }
            if (map.containsKey(set)){
                iterator.remove();
            }else{
                map.put(set, 1);
            }
        }
        return pathList;
    }

    public static JSONArray deleteVal(JSONArray pathList){
        for (Object path: pathList) {
            JSONObject p = ((JSONObject)path).getJSONObject("p");
            JSONArray nodeList = p.getJSONArray("nodes");
            JSONArray segmentList = p.getJSONArray("segments");
            for (Object node: nodeList){
                JSONObject nodeJson = (JSONObject) node;
                JSONObject properties =  nodeJson.getJSONObject("properties");
                properties.replace("name", properties.getJSONObject("name").get("val"));
                properties.replace("id", properties.getJSONObject("id").get("val"));
            }
            for (Object segment: segmentList){
                JSONObject segmentJson = (JSONObject) segment;
                JSONObject startProperties =  segmentJson.getJSONObject("start").getJSONObject("properties");
                startProperties.replace("name", startProperties.getJSONObject("name").get("val"));
                startProperties.replace("id", startProperties.getJSONObject("id").get("val"));
                JSONObject endProperties =  segmentJson.getJSONObject("end").getJSONObject("properties");
                endProperties.replace("name", endProperties.getJSONObject("name").get("val"));
                endProperties.replace("id", endProperties.getJSONObject("id").get("val"));
            }
        }
        return pathList;
    }

    public static List<List<String>> filterNode(JSONArray pathList, int[] indexList){
        List<List<String>> filterNodeLists = new ArrayList<>();
        for (Object path : pathList){
            JSONArray nodeList = ((JSONObject)path).getJSONObject("p").getJSONArray("nodes");
            int len = nodeList.size();
            List<String> filterNodeList = new ArrayList<>();
            for(int index : indexList){
                if (index == -1){
                    index = len - 1;
                }
                filterNodeList.add(nodeList.getJSONObject(index).getJSONObject("properties").getString("name"));
            }
            filterNodeLists.add(filterNodeList);
        }
        return filterNodeLists;
    }
}
