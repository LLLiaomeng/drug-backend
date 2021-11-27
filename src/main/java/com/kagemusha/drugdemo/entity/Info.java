package com.kagemusha.drugdemo.entity;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Info {
    private Person person;
    private MedicalOrder medicalOrder;

    public void calculateAge() throws ParseException {
        //将字符串日期，转换成Date对象
        //创建SimpleDateFormat 对象，写日期模式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdayDate = new Date();
        //调用方法prase,将字符串转换成日期对象
        try {
            birthdayDate = sdf.parse(this.person.getBirthday());
        }catch (ParseException e) { // 捕捉到了解析异常
            e.printStackTrace(); // 打印出错时的栈轨迹信息
        }
        //获取今天的日期对象
        Date todayDate = new Date();
        this.getMedicalOrder().setRecordDate(sdf.format(todayDate));
        //将两个日期转换成毫秒值，Date类的方法getTime
        long birthdaySecond = birthdayDate.getTime();
        long todaySecond = todayDate.getTime();
        //计算两个日期的差值
        int  age = (int)((todaySecond - birthdaySecond)/1000/24/3600);

        if(age < 0) {
            age = 0;
        }
        this.person.setAge(age);

        List<String> crowdList = new ArrayList<>();
        if (age < 365 * 18){
            crowdList.add("儿童");
            if (age <= 28){
                crowdList.add("新生儿");
            }
        } else if(age < 365 * 60){
            crowdList.add("成人");
        } else{
            crowdList.add("老人");
        }
        this.person.setCrowdList(crowdList);
    }
}
