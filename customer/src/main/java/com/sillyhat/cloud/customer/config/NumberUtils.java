package com.sillyhat.cloud.customer.config;

import java.text.DecimalFormat;

public class NumberUtils {

    public static boolean isEqual(Long value1,Long value2){
        if(value1 == null || value2 == null) {
            return false;
        }
        if(value1.longValue() == value2.longValue()){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isNotEqual(Long value1,Long value2){
        if(value1 == null || value2 == null) {
            return true;
        }
        if(value1.longValue() == value2.longValue()){
            return false;
        }else{
            return true;
        }
    }

    public static boolean compareMoreThanZero(Long value){
        if(value != null && value.longValue() > 0) {
            return true;
        }else{
            return false;
        }
    }

    public static boolean compareMoreThanValue(Long value1,Long value2){
        if(value1 == null || value2 == null) {
            return false;
        }
        if(value1.longValue() >= value2.longValue()){
            return true;
        }else{
            return false;
        }
    }

    public static boolean compareMoreThanValue(Integer value1,Integer value2){
        if(value1 == null || value2 == null) {
            return false;
        }
        if(value1.intValue() >= value2.intValue()){
            return true;
        }else{
            return false;
        }
    }

    public static String moneyFormatReplaceDoubleType(Double money){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(money);
    }


}