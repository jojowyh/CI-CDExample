package com.test.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Timestamp now() {
        // 获取当前日期和时间
        Date date = new Date();
        // 将Date对象转换为Timestamp对象
        return new Timestamp(date.getTime());
    }



    public static void main(String[] args) {
        // 测试方法
        Timestamp currentTimestamp = now();
        System.out.println("Current Timestamp: " + currentTimestamp);
    }
}
