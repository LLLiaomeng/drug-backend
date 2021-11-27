package com.kagemusha.drugdemo.entity.neo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Path {
    private List<Node> nodeList;
}
