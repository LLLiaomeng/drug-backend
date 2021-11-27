package com.kagemusha.drugdemo.utils;

import java.util.ArrayList;
import java.util.List;

public class Tool {
    public static List<String> addQuote(List<String> stringList){
        List<String> list = new ArrayList<>();
        for (String string: stringList) {
            list.add('"' + string + '"');
        }
        return list;
    }
}
