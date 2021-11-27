# 合理用药审查后端
## 审查
通过接口访问：localhost:8018/drug/check

审核数据样例：
```
{
    "person":{
        "birthday":"2020-10-27",
        "name":"李丽娟",
        "cardId":-1,
        "weight":70,
        "gender":"女",
        "height": 170,
        "allergyList":["阿莫西林"],
        "specialCrowdList":["肝功能不全者"]
    },
    "medicalOrder":{
        "moId": -1,
        "hospital":"成都市温江区人民医院",
        "doctorWay":"普通人员门诊",
        "startDate":"2021-08-03",
        "endDate": "2021-08-06",
        "department":"内科",
        "diseaseList":["扁桃体炎"],
        "symptomList":["咳嗽","声音嘶哑","恶心腹痛"],
        "bsCondition":"嗓子一直不舒服",
        "formulaList":[{
            "drugList":[{
                "name":"喉疾灵胶囊", 
                "code":"YP10012236",
                "route":"口服",
                "dosageNum":"1",
                "dosageUnit":"粒",
                "freqNum":2,
                "freqUnit":"次/天",
                "amount":3
                },{
                "name":"双黄连滴注液",
                "code":"",
                "route":"肌肉滴注",
                "dosageNum":"1",
                "dosageUnit":"支",
                "freqNum":1,
                "freqUnit":"次/天",
                "amount":3
                },{
                "name":"阿莫西林克拉维酸钾咀嚼片",
                "code":"",
                "route":"口服",
                "dosageNum":"1",
                "dosageUnit":"粒",
                "freqNum":2,
                "freqUnit":"次/天",
                "amount":3
                },{
                "name":"盐酸氨溴索片",
                "code":"",
                "route":"口服",
                "dosageNum":"1",
                "dosageUnit":"粒",
                "freqNum":2,
                "freqUnit":"次/天",
                "amount":3
                },{
                "name":"保和咀嚼片",
                "code":"",
                "route":"口服",
                "dosageNum":"1",
                "dosageUnit":"粒",
                "freqNum":2,
                "freqUnit":"次/天",
                "amount":3
                },{
                "name":"复方甲苯咪唑片",
                "code":"",
                "route":"口服",
                "dosageNum":"1",
                "dosageUnit":"粒",
                "freqNum":2,
                "freqUnit":"次/天",
                "amount":3
                },{
                "name":"头孢羟氨苄咀嚼片",
                "code":"",
                "route":"口服",
                "dosageNum":"1",
                "dosageUnit":"粒",
                "freqNum":2,
                "freqUnit":"次/天",
                "amount":3
                },{
                "name":"人血白蛋白",
                "code":"YP10014918",
                "route":"静脉滴注",
                "dosageNum":"1",
                "dosageUnit":"支",
                "freqNum":1,
                "freqUnit":"次/天",
                "amount":3
                },{
                "name":"水溶性维生素",
                "code":"YP10014886",
                "route":"静脉滴注",
                "dosageNum":"1",
                "dosageUnit":"支",
                "freqNum":1,
                "freqUnit":"次/天",
                "amount":3
                },{
                "name":"依达拉奉",
                "code":"YP00009423",
                "route":"静脉滴注",
                "dosageNum":"1",
                "dosageUnit":"支",
                "freqNum":1,
                "freqUnit":"次/天",
                "amount":3
                }]
            }],
        "projectList":[{
            "name":"牙移植术",
            "code":"ZLC330604009",
            "amount":1
            },{
            "name":"皮下注射",
            "code":"ZL12040000104",
            "amount":1
        }]
    }
}
```
审核结果样例：
```
{
    "itemList": [
        {
            "type": "年龄审查",
            "decision": "不通过",
            "reason": "未满2岁的幼儿使用复方甲苯咪唑片的建议为：禁用；",
            "pathList": [
                {
                    "p": {
                        "relationships": [
                            {
                                "start": 64453,
                                "end": 64480,
                                "id": 128238,
                                "type": "用药",
                                "properties": {}
                            },
                            {
                                "start": 64480,
                                "end": 64479,
                                "id": 128237,
                                "type": "用药",
                                "properties": {}
                            },
                            {
                                "start": 64480,
                                "end": 33,
                                "id": 128236,
                                "type": "用药结果",
                                "properties": {}
                            },
                            {
                                "start": 64479,
                                "end": 137280,
                                "id": 228057,
                                "type": "细粒度化",
                                "properties": {}
                            },
                            {
                                "start": 137280,
                                "end": 137281,
                                "id": 228058,
                                "type": "数字",
                                "properties": {}
                            },
                            {
                                "start": 137280,
                                "end": 137066,
                                "id": 228059,
                                "type": "单位",
                                "properties": {}
                            }
                        ],
                        "nodes": [
                            {
                                "id": 64453,
                                "properties": {
                                    "name": "复方甲苯咪唑片",
                                    "id": "b4f57fd2-b169-11eb-b0b9-2cf05d78afbf"
                                },
                                "labels": [
                                    "药品"
                                ]
                            },
                            {
                                "id": 64480,
                                "properties": {
                                    "name": "b515b806-b169-11eb-a342-2cf05d78afbf",
                                    "id": "b515b806-b169-11eb-a342-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 64479,
                                "properties": {
                                    "name": "未满2岁的幼儿",
                                    "id": "b512d7c6-b169-11eb-bf79-2cf05d78afbf"
                                },
                                "labels": [
                                    "人群"
                                ]
                            },
                            {
                                "id": 64480,
                                "properties": {
                                    "name": "b515b806-b169-11eb-a342-2cf05d78afbf",
                                    "id": "b515b806-b169-11eb-a342-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 33,
                                "properties": {
                                    "name": "禁用",
                                    "id": "43809cec-b169-11eb-8649-2cf05d78afbf"
                                },
                                "labels": [
                                    "用药结果级别"
                                ]
                            },
                            {
                                "id": 64479,
                                "properties": {
                                    "name": "未满2岁的幼儿",
                                    "id": "b512d7c6-b169-11eb-bf79-2cf05d78afbf"
                                },
                                "labels": [
                                    "人群"
                                ]
                            },
                            {
                                "id": 137280,
                                "properties": {
                                    "name": "a3e09510-2918-11ec-888b-38fc98f0fb0d",
                                    "id": "a3e09510-2918-11ec-888b-38fc98f0fb0d"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 137281,
                                "properties": {
                                    "name": 730.0,
                                    "id": "a3e0f94c-2918-11ec-b38e-38fc98f0fb0d"
                                },
                                "labels": [
                                    "数字"
                                ]
                            },
                            {
                                "id": 137280,
                                "properties": {
                                    "name": "a3e09510-2918-11ec-888b-38fc98f0fb0d",
                                    "id": "a3e09510-2918-11ec-888b-38fc98f0fb0d"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 137066,
                                "properties": {
                                    "name": "天_以下",
                                    "id": "9b2d2e2e-2918-11ec-8eca-38fc98f0fb0d"
                                },
                                "labels": [
                                    "单位"
                                ]
                            }
                        ],
                        "segments": [
                            {
                                "start": {
                                    "id": 64453,
                                    "properties": {
                                        "name": "复方甲苯咪唑片",
                                        "id": "b4f57fd2-b169-11eb-b0b9-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药品"
                                    ]
                                },
                                "end": {
                                    "id": 64480,
                                    "properties": {
                                        "name": "b515b806-b169-11eb-a342-2cf05d78afbf",
                                        "id": "b515b806-b169-11eb-a342-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 64453,
                                    "end": 64480,
                                    "id": 128238,
                                    "type": "用药",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 64480,
                                    "properties": {
                                        "name": "b515b806-b169-11eb-a342-2cf05d78afbf",
                                        "id": "b515b806-b169-11eb-a342-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 64479,
                                    "properties": {
                                        "name": "未满2岁的幼儿",
                                        "id": "b512d7c6-b169-11eb-bf79-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "人群"
                                    ]
                                },
                                "relationship": {
                                    "start": 64480,
                                    "end": 64479,
                                    "id": 128237,
                                    "type": "用药",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 64480,
                                    "properties": {
                                        "name": "b515b806-b169-11eb-a342-2cf05d78afbf",
                                        "id": "b515b806-b169-11eb-a342-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 33,
                                    "properties": {
                                        "name": "禁用",
                                        "id": "43809cec-b169-11eb-8649-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "用药结果级别"
                                    ]
                                },
                                "relationship": {
                                    "start": 64480,
                                    "end": 33,
                                    "id": 128236,
                                    "type": "用药结果",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 64479,
                                    "properties": {
                                        "name": "未满2岁的幼儿",
                                        "id": "b512d7c6-b169-11eb-bf79-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "人群"
                                    ]
                                },
                                "end": {
                                    "id": 137280,
                                    "properties": {
                                        "name": "a3e09510-2918-11ec-888b-38fc98f0fb0d",
                                        "id": "a3e09510-2918-11ec-888b-38fc98f0fb0d"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 64479,
                                    "end": 137280,
                                    "id": 228057,
                                    "type": "细粒度化",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 137280,
                                    "properties": {
                                        "name": "a3e09510-2918-11ec-888b-38fc98f0fb0d",
                                        "id": "a3e09510-2918-11ec-888b-38fc98f0fb0d"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 137281,
                                    "properties": {
                                        "name": 730.0,
                                        "id": "a3e0f94c-2918-11ec-b38e-38fc98f0fb0d"
                                    },
                                    "labels": [
                                        "数字"
                                    ]
                                },
                                "relationship": {
                                    "start": 137280,
                                    "end": 137281,
                                    "id": 228058,
                                    "type": "数字",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 137280,
                                    "properties": {
                                        "name": "a3e09510-2918-11ec-888b-38fc98f0fb0d",
                                        "id": "a3e09510-2918-11ec-888b-38fc98f0fb0d"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 137066,
                                    "properties": {
                                        "name": "天_以下",
                                        "id": "9b2d2e2e-2918-11ec-8eca-38fc98f0fb0d"
                                    },
                                    "labels": [
                                        "单位"
                                    ]
                                },
                                "relationship": {
                                    "start": 137280,
                                    "end": 137066,
                                    "id": 228059,
                                    "type": "单位",
                                    "properties": {}
                                }
                            }
                        ]
                    }
                }
            ]
        },
        {
            "type": "不良反应审查",
            "decision": "不通过",
            "reason": "头孢羟氨苄咀嚼片会导致恶心腹痛；",
            "pathList": [
                {
                    "p": {
                        "relationships": [
                            {
                                "start": 115544,
                                "end": 115554,
                                "id": 26074,
                                "type": "不良反应",
                                "properties": {}
                            },
                            {
                                "start": 115554,
                                "end": 115550,
                                "id": 26073,
                                "type": "不良反应",
                                "properties": {}
                            }
                        ],
                        "nodes": [
                            {
                                "id": 115544,
                                "properties": {
                                    "name": "头孢羟氨苄咀嚼片",
                                    "id": "0405bf06-b16a-11eb-9a59-2cf05d78afbf"
                                },
                                "labels": [
                                    "药品"
                                ]
                            },
                            {
                                "id": 115554,
                                "properties": {
                                    "name": "04156a3a-b16a-11eb-ac55-2cf05d78afbf",
                                    "id": "04156a3a-b16a-11eb-ac55-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 115550,
                                "properties": {
                                    "name": "恶心腹痛",
                                    "id": "040e67f0-b16a-11eb-a3fe-2cf05d78afbf"
                                },
                                "labels": [
                                    "症状"
                                ]
                            }
                        ],
                        "segments": [
                            {
                                "start": {
                                    "id": 115544,
                                    "properties": {
                                        "name": "头孢羟氨苄咀嚼片",
                                        "id": "0405bf06-b16a-11eb-9a59-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药品"
                                    ]
                                },
                                "end": {
                                    "id": 115554,
                                    "properties": {
                                        "name": "04156a3a-b16a-11eb-ac55-2cf05d78afbf",
                                        "id": "04156a3a-b16a-11eb-ac55-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 115544,
                                    "end": 115554,
                                    "id": 26074,
                                    "type": "不良反应",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 115554,
                                    "properties": {
                                        "name": "04156a3a-b16a-11eb-ac55-2cf05d78afbf",
                                        "id": "04156a3a-b16a-11eb-ac55-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 115550,
                                    "properties": {
                                        "name": "恶心腹痛",
                                        "id": "040e67f0-b16a-11eb-a3fe-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "症状"
                                    ]
                                },
                                "relationship": {
                                    "start": 115554,
                                    "end": 115550,
                                    "id": 26073,
                                    "type": "不良反应",
                                    "properties": {}
                                }
                            }
                        ]
                    }
                }
            ]
        },
        {
            "type": "重复用药审查",
            "decision": "不通过",
            "reason": "双黄连滴注液和喉疾灵胶囊中都含有连翘；双黄连滴注液和保和咀嚼片中都含有连翘；保和咀嚼片和喉疾灵胶囊中都含有连翘；",
            "pathList": [
                {
                    "p": {
                        "relationships": [
                            {
                                "start": 52784,
                                "end": 52790,
                                "id": 112098,
                                "type": "成分",
                                "properties": {}
                            },
                            {
                                "start": 52790,
                                "end": 415,
                                "id": 112097,
                                "type": "成分",
                                "properties": {}
                            },
                            {
                                "start": 43919,
                                "end": 415,
                                "id": 95733,
                                "type": "成分",
                                "properties": {}
                            },
                            {
                                "start": 43909,
                                "end": 43919,
                                "id": 95734,
                                "type": "成分",
                                "properties": {}
                            }
                        ],
                        "nodes": [
                            {
                                "id": 52784,
                                "properties": {
                                    "name": "双黄连滴注液",
                                    "id": "ab2045d2-b169-11eb-b1b0-2cf05d78afbf"
                                },
                                "labels": [
                                    "药品"
                                ]
                            },
                            {
                                "id": 52790,
                                "properties": {
                                    "name": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf",
                                    "id": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 415,
                                "properties": {
                                    "name": "连翘",
                                    "id": "438270d0-b169-11eb-a6f5-2cf05d78afbf"
                                },
                                "labels": [
                                    "药物"
                                ]
                            },
                            {
                                "id": 43919,
                                "properties": {
                                    "name": "a233e014-b169-11eb-92fc-2cf05d78afbf",
                                    "id": "a233e014-b169-11eb-92fc-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 43909,
                                "properties": {
                                    "name": "喉疾灵胶囊",
                                    "id": "a225de58-b169-11eb-8b79-2cf05d78afbf"
                                },
                                "labels": [
                                    "药品"
                                ]
                            }
                        ],
                        "segments": [
                            {
                                "start": {
                                    "id": 52784,
                                    "properties": {
                                        "name": "双黄连滴注液",
                                        "id": "ab2045d2-b169-11eb-b1b0-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药品"
                                    ]
                                },
                                "end": {
                                    "id": 52790,
                                    "properties": {
                                        "name": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf",
                                        "id": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 52784,
                                    "end": 52790,
                                    "id": 112098,
                                    "type": "成分",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 52790,
                                    "properties": {
                                        "name": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf",
                                        "id": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 415,
                                    "properties": {
                                        "name": "连翘",
                                        "id": "438270d0-b169-11eb-a6f5-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药物"
                                    ]
                                },
                                "relationship": {
                                    "start": 52790,
                                    "end": 415,
                                    "id": 112097,
                                    "type": "成分",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 415,
                                    "properties": {
                                        "name": "连翘",
                                        "id": "438270d0-b169-11eb-a6f5-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药物"
                                    ]
                                },
                                "end": {
                                    "id": 43919,
                                    "properties": {
                                        "name": "a233e014-b169-11eb-92fc-2cf05d78afbf",
                                        "id": "a233e014-b169-11eb-92fc-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 43919,
                                    "end": 415,
                                    "id": 95733,
                                    "type": "成分",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 43919,
                                    "properties": {
                                        "name": "a233e014-b169-11eb-92fc-2cf05d78afbf",
                                        "id": "a233e014-b169-11eb-92fc-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 43909,
                                    "properties": {
                                        "name": "喉疾灵胶囊",
                                        "id": "a225de58-b169-11eb-8b79-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药品"
                                    ]
                                },
                                "relationship": {
                                    "start": 43909,
                                    "end": 43919,
                                    "id": 95734,
                                    "type": "成分",
                                    "properties": {}
                                }
                            }
                        ]
                    }
                },
                {
                    "p": {
                        "relationships": [
                            {
                                "start": 52784,
                                "end": 52790,
                                "id": 112098,
                                "type": "成分",
                                "properties": {}
                            },
                            {
                                "start": 52790,
                                "end": 415,
                                "id": 112097,
                                "type": "成分",
                                "properties": {}
                            },
                            {
                                "start": 131522,
                                "end": 415,
                                "id": 133624,
                                "type": "成分",
                                "properties": {}
                            },
                            {
                                "start": 131514,
                                "end": 131522,
                                "id": 133625,
                                "type": "成分",
                                "properties": {}
                            }
                        ],
                        "nodes": [
                            {
                                "id": 52784,
                                "properties": {
                                    "name": "双黄连滴注液",
                                    "id": "ab2045d2-b169-11eb-b1b0-2cf05d78afbf"
                                },
                                "labels": [
                                    "药品"
                                ]
                            },
                            {
                                "id": 52790,
                                "properties": {
                                    "name": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf",
                                    "id": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 415,
                                "properties": {
                                    "name": "连翘",
                                    "id": "438270d0-b169-11eb-a6f5-2cf05d78afbf"
                                },
                                "labels": [
                                    "药物"
                                ]
                            },
                            {
                                "id": 131522,
                                "properties": {
                                    "name": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf",
                                    "id": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 131514,
                                "properties": {
                                    "name": "保和咀嚼片",
                                    "id": "62c3602e-b16b-11eb-92dc-2cf05d78afbf"
                                },
                                "labels": [
                                    "药品"
                                ]
                            }
                        ],
                        "segments": [
                            {
                                "start": {
                                    "id": 52784,
                                    "properties": {
                                        "name": "双黄连滴注液",
                                        "id": "ab2045d2-b169-11eb-b1b0-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药品"
                                    ]
                                },
                                "end": {
                                    "id": 52790,
                                    "properties": {
                                        "name": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf",
                                        "id": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 52784,
                                    "end": 52790,
                                    "id": 112098,
                                    "type": "成分",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 52790,
                                    "properties": {
                                        "name": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf",
                                        "id": "ab24dc6e-b169-11eb-8b12-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 415,
                                    "properties": {
                                        "name": "连翘",
                                        "id": "438270d0-b169-11eb-a6f5-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药物"
                                    ]
                                },
                                "relationship": {
                                    "start": 52790,
                                    "end": 415,
                                    "id": 112097,
                                    "type": "成分",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 415,
                                    "properties": {
                                        "name": "连翘",
                                        "id": "438270d0-b169-11eb-a6f5-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药物"
                                    ]
                                },
                                "end": {
                                    "id": 131522,
                                    "properties": {
                                        "name": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf",
                                        "id": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 131522,
                                    "end": 415,
                                    "id": 133624,
                                    "type": "成分",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 131522,
                                    "properties": {
                                        "name": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf",
                                        "id": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 131514,
                                    "properties": {
                                        "name": "保和咀嚼片",
                                        "id": "62c3602e-b16b-11eb-92dc-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药品"
                                    ]
                                },
                                "relationship": {
                                    "start": 131514,
                                    "end": 131522,
                                    "id": 133625,
                                    "type": "成分",
                                    "properties": {}
                                }
                            }
                        ]
                    }
                },
                {
                    "p": {
                        "relationships": [
                            {
                                "start": 131514,
                                "end": 131522,
                                "id": 133625,
                                "type": "成分",
                                "properties": {}
                            },
                            {
                                "start": 131522,
                                "end": 415,
                                "id": 133624,
                                "type": "成分",
                                "properties": {}
                            },
                            {
                                "start": 43919,
                                "end": 415,
                                "id": 95733,
                                "type": "成分",
                                "properties": {}
                            },
                            {
                                "start": 43909,
                                "end": 43919,
                                "id": 95734,
                                "type": "成分",
                                "properties": {}
                            }
                        ],
                        "nodes": [
                            {
                                "id": 131514,
                                "properties": {
                                    "name": "保和咀嚼片",
                                    "id": "62c3602e-b16b-11eb-92dc-2cf05d78afbf"
                                },
                                "labels": [
                                    "药品"
                                ]
                            },
                            {
                                "id": 131522,
                                "properties": {
                                    "name": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf",
                                    "id": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 415,
                                "properties": {
                                    "name": "连翘",
                                    "id": "438270d0-b169-11eb-a6f5-2cf05d78afbf"
                                },
                                "labels": [
                                    "药物"
                                ]
                            },
                            {
                                "id": 43919,
                                "properties": {
                                    "name": "a233e014-b169-11eb-92fc-2cf05d78afbf",
                                    "id": "a233e014-b169-11eb-92fc-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 43909,
                                "properties": {
                                    "name": "喉疾灵胶囊",
                                    "id": "a225de58-b169-11eb-8b79-2cf05d78afbf"
                                },
                                "labels": [
                                    "药品"
                                ]
                            }
                        ],
                        "segments": [
                            {
                                "start": {
                                    "id": 131514,
                                    "properties": {
                                        "name": "保和咀嚼片",
                                        "id": "62c3602e-b16b-11eb-92dc-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药品"
                                    ]
                                },
                                "end": {
                                    "id": 131522,
                                    "properties": {
                                        "name": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf",
                                        "id": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 131514,
                                    "end": 131522,
                                    "id": 133625,
                                    "type": "成分",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 131522,
                                    "properties": {
                                        "name": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf",
                                        "id": "62d6d62c-b16b-11eb-b81b-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 415,
                                    "properties": {
                                        "name": "连翘",
                                        "id": "438270d0-b169-11eb-a6f5-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药物"
                                    ]
                                },
                                "relationship": {
                                    "start": 131522,
                                    "end": 415,
                                    "id": 133624,
                                    "type": "成分",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 415,
                                    "properties": {
                                        "name": "连翘",
                                        "id": "438270d0-b169-11eb-a6f5-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药物"
                                    ]
                                },
                                "end": {
                                    "id": 43919,
                                    "properties": {
                                        "name": "a233e014-b169-11eb-92fc-2cf05d78afbf",
                                        "id": "a233e014-b169-11eb-92fc-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 43919,
                                    "end": 415,
                                    "id": 95733,
                                    "type": "成分",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 43919,
                                    "properties": {
                                        "name": "a233e014-b169-11eb-92fc-2cf05d78afbf",
                                        "id": "a233e014-b169-11eb-92fc-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 43909,
                                    "properties": {
                                        "name": "喉疾灵胶囊",
                                        "id": "a225de58-b169-11eb-8b79-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药品"
                                    ]
                                },
                                "relationship": {
                                    "start": 43909,
                                    "end": 43919,
                                    "id": 95734,
                                    "type": "成分",
                                    "properties": {}
                                }
                            }
                        ]
                    }
                }
            ]
        },
        {
            "type": "过敏审查",
            "decision": "不通过",
            "reason": "阿莫西林克拉维酸钾咀嚼片含有过敏物质阿莫西林；",
            "pathList": [
                {
                    "p": {
                        "relationships": [
                            {
                                "start": 33928,
                                "end": 33930,
                                "id": 133234,
                                "type": "成分",
                                "properties": {}
                            },
                            {
                                "start": 33930,
                                "end": 25084,
                                "id": 133233,
                                "type": "成分",
                                "properties": {}
                            }
                        ],
                        "nodes": [
                            {
                                "id": 33928,
                                "properties": {
                                    "name": "阿莫西林克拉维酸钾咀嚼片",
                                    "id": "632c914c-b16a-11eb-9c93-2cf05d78afbf"
                                },
                                "labels": [
                                    "药品"
                                ]
                            },
                            {
                                "id": 33930,
                                "properties": {
                                    "name": "63323950-b16a-11eb-b164-2cf05d78afbf",
                                    "id": "63323950-b16a-11eb-b164-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 25084,
                                "properties": {
                                    "name": "阿莫西林",
                                    "id": "450aed2e-b169-11eb-a3a2-2cf05d78afbf"
                                },
                                "labels": [
                                    "药物"
                                ]
                            }
                        ],
                        "segments": [
                            {
                                "start": {
                                    "id": 33928,
                                    "properties": {
                                        "name": "阿莫西林克拉维酸钾咀嚼片",
                                        "id": "632c914c-b16a-11eb-9c93-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药品"
                                    ]
                                },
                                "end": {
                                    "id": 33930,
                                    "properties": {
                                        "name": "63323950-b16a-11eb-b164-2cf05d78afbf",
                                        "id": "63323950-b16a-11eb-b164-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 33928,
                                    "end": 33930,
                                    "id": 133234,
                                    "type": "成分",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 33930,
                                    "properties": {
                                        "name": "63323950-b16a-11eb-b164-2cf05d78afbf",
                                        "id": "63323950-b16a-11eb-b164-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 25084,
                                    "properties": {
                                        "name": "阿莫西林",
                                        "id": "450aed2e-b169-11eb-a3a2-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药物"
                                    ]
                                },
                                "relationship": {
                                    "start": 33930,
                                    "end": 25084,
                                    "id": 133233,
                                    "type": "成分",
                                    "properties": {}
                                }
                            }
                        ]
                    }
                }
            ]
        },
        {
            "type": "药物相互作用审查",
            "decision": "不通过",
            "reason": "盐酸氨溴索片与阿莫西林克拉维酸钾咀嚼片发生相互作用；",
            "pathList": [
                {
                    "p": {
                        "relationships": [
                            {
                                "start": 109400,
                                "end": 109405,
                                "id": 72353,
                                "type": "相互作用",
                                "properties": {}
                            },
                            {
                                "start": 109405,
                                "end": 25084,
                                "id": 72352,
                                "type": "相互作用",
                                "properties": {}
                            },
                            {
                                "start": 33930,
                                "end": 25084,
                                "id": 133233,
                                "type": "成分",
                                "properties": {}
                            },
                            {
                                "start": 33928,
                                "end": 33930,
                                "id": 133234,
                                "type": "成分",
                                "properties": {}
                            }
                        ],
                        "nodes": [
                            {
                                "id": 109400,
                                "properties": {
                                    "name": "盐酸氨溴索片",
                                    "id": "16f0e194-b16b-11eb-9e40-2cf05d78afbf"
                                },
                                "labels": [
                                    "药品"
                                ]
                            },
                            {
                                "id": 109405,
                                "properties": {
                                    "name": "17005eb4-b16b-11eb-a4ce-2cf05d78afbf",
                                    "id": "17005eb4-b16b-11eb-a4ce-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 25084,
                                "properties": {
                                    "name": "阿莫西林",
                                    "id": "450aed2e-b169-11eb-a3a2-2cf05d78afbf"
                                },
                                "labels": [
                                    "药物"
                                ]
                            },
                            {
                                "id": 33930,
                                "properties": {
                                    "name": "63323950-b16a-11eb-b164-2cf05d78afbf",
                                    "id": "63323950-b16a-11eb-b164-2cf05d78afbf"
                                },
                                "labels": [
                                    "fact"
                                ]
                            },
                            {
                                "id": 33928,
                                "properties": {
                                    "name": "阿莫西林克拉维酸钾咀嚼片",
                                    "id": "632c914c-b16a-11eb-9c93-2cf05d78afbf"
                                },
                                "labels": [
                                    "药品"
                                ]
                            }
                        ],
                        "segments": [
                            {
                                "start": {
                                    "id": 109400,
                                    "properties": {
                                        "name": "盐酸氨溴索片",
                                        "id": "16f0e194-b16b-11eb-9e40-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药品"
                                    ]
                                },
                                "end": {
                                    "id": 109405,
                                    "properties": {
                                        "name": "17005eb4-b16b-11eb-a4ce-2cf05d78afbf",
                                        "id": "17005eb4-b16b-11eb-a4ce-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 109400,
                                    "end": 109405,
                                    "id": 72353,
                                    "type": "相互作用",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 109405,
                                    "properties": {
                                        "name": "17005eb4-b16b-11eb-a4ce-2cf05d78afbf",
                                        "id": "17005eb4-b16b-11eb-a4ce-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 25084,
                                    "properties": {
                                        "name": "阿莫西林",
                                        "id": "450aed2e-b169-11eb-a3a2-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药物"
                                    ]
                                },
                                "relationship": {
                                    "start": 109405,
                                    "end": 25084,
                                    "id": 72352,
                                    "type": "相互作用",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 25084,
                                    "properties": {
                                        "name": "阿莫西林",
                                        "id": "450aed2e-b169-11eb-a3a2-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药物"
                                    ]
                                },
                                "end": {
                                    "id": 33930,
                                    "properties": {
                                        "name": "63323950-b16a-11eb-b164-2cf05d78afbf",
                                        "id": "63323950-b16a-11eb-b164-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "relationship": {
                                    "start": 33930,
                                    "end": 25084,
                                    "id": 133233,
                                    "type": "成分",
                                    "properties": {}
                                }
                            },
                            {
                                "start": {
                                    "id": 33930,
                                    "properties": {
                                        "name": "63323950-b16a-11eb-b164-2cf05d78afbf",
                                        "id": "63323950-b16a-11eb-b164-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "fact"
                                    ]
                                },
                                "end": {
                                    "id": 33928,
                                    "properties": {
                                        "name": "阿莫西林克拉维酸钾咀嚼片",
                                        "id": "632c914c-b16a-11eb-9c93-2cf05d78afbf"
                                    },
                                    "labels": [
                                        "药品"
                                    ]
                                },
                                "relationship": {
                                    "start": 33928,
                                    "end": 33930,
                                    "id": 133234,
                                    "type": "成分",
                                    "properties": {}
                                }
                            }
                        ]
                    }
                }
            ]
        }
    ]
}
```
