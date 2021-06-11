package com.example.security.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Xiang JiangCheng
 */
public abstract class DateUtils {

    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMAT_NON_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter YYYYMMDDHHMMSS_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter DATE_T_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter SLASHES_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static final String DEFAULT_SPILT = "-";

    public static String localDateTimeToStr(LocalDateTime dateTime, DateTimeFormatter formatter) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(formatter);
    }

    public static String localDateToStr(LocalDate date, DateTimeFormatter formatter) {
        if (date == null) {
            return "";
        }
        return date.format(formatter);
    }

    public static LocalDate parse(String deliveryDate, DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(deliveryDate, formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LocalDateTime parseLocalDateTime(String date, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LocalDate parseLocalDate(String date, DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 特殊字符串转完整时间
     *
     * @param specialTime 特殊时间格式字符串 格式： yyyyMMddHHmm  202104011200
     * @return 返回格式 yyyy-MM-dd HH:mm:ss
     */
    public static String handleSpecialTimeString(String specialTime) {
        if (StringUtils.isEmpty(specialTime)) {
            return null;
        }
        String year = specialTime.substring(0, 4);
        String month = specialTime.substring(4, 6);
        String day = specialTime.substring(6, 8);
        String hour = specialTime.substring(8, 10);
        String minute = specialTime.substring(10, 12);
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00";
    }

    /**
     * 转 20210326 为  2021/03/26
     */
    public static String handleDeliveryPeriod(String deliveryPeriod, String spilt) {
        if (StringUtils.isEmpty(deliveryPeriod)) {
            return null;
        }
        String year = deliveryPeriod.substring(0, 4);
        String month = deliveryPeriod.substring(4, 6);
        String day = deliveryPeriod.substring(6, 8);
        return year + spilt + month + spilt + day;
    }

    /**
     * 转 202103261220 为  2021-03-26 12:20
     */
    public static String handlePlannedDeliveryTime(String deliveryPeriod) {
        if (StringUtils.isEmpty(deliveryPeriod)) {
            return null;
        }
        String year = deliveryPeriod.substring(0, 4);
        String month = deliveryPeriod.substring(4, 6);
        String day = deliveryPeriod.substring(6, 8);
        String hours = deliveryPeriod.substring(8, 10);
        String minute = deliveryPeriod.substring(10, 12);
        return year + DEFAULT_SPILT + month + DEFAULT_SPILT + day + " " + hours + ":" + minute;
    }


    public static void main(String[] args) {
        System.out.println("hamburger".substring(4, 8));
        System.out.println(handleSpecialTimeString("202104011200"));
        System.out.println(handleDeliveryPeriod("20210326", "/"));
        System.out.println(handlePlannedDeliveryTime("202103261220"));

        LocalDate startDate = LocalDate.now();
        System.out.println(DateUtils.localDateToStr(startDate, DateUtils.DATE_FORMAT));

        String startTime = "2021-12-21";
        LocalDate localDate = LocalDate.parse(startTime, DateUtils.DATE_T_FORMAT);
        System.out.println(LocalDateTime.of(localDate, LocalTime.MIN));

        LocalDateTime localDateTime = DateUtils.parseLocalDateTime(startTime + " 00:00:00", DateUtils.DEFAULT_DATE_TIME_FORMAT);
        System.out.println(localDateTime);

        String test1 = "2021-04-22 10:00";
        System.out.println(DateUtils.parseLocalDate(test1.substring(0, 10), DateUtils.DATE_T_FORMAT));

    }
}
