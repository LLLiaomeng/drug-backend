package com.kagemusha.drugdemo.entity;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

@Data
public class Item {
    private String type;
    private String decision;
    private String reason;
    private JSONArray pathList;
}
