package com.kagemusha.drugdemo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SDF {
    public static void main(String[] args) {
        Map<Set<String>, Integer> map = new HashMap<>();
        Set<String> s = new HashSet();
        s.add("双黄连滴注液");
        s.add("连翘");
        s.add("喉疾灵胶囊");
        if(!map.containsKey(s))
            System.out.println("sss");
        map.put(s,1);
        Set<String> d = new HashSet();
        d.add("双黄连滴注液");
        d.add("连翘");
        d.add("喉疾灵胶囊");
        System.out.println(map.get(d));
        System.out.println("1"+1);
        JSONObject p = new JSONObject();
        p.put("p1","string");
        JSONArray p2 = new JSONArray();
        JSONObject p3 = new JSONObject();
        p3.put("p3", "p3");
        p2.add(p3);
        p2.add(p3);
        p.put("p2", p2);
        System.out.println(p);
        System.out.println(p.size());
        JSONArray sss = new JSONArray();
        if (sss.isEmpty()){
            System.out.println("ssssssss");
        }
        System.out.println(p2.getJSONObject(1));
        System.out.println(Math.round(Float.parseFloat("33.0")));
    }
}
