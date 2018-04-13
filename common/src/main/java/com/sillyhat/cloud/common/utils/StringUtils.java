package com.sillyhat.cloud.common.utils;

public class StringUtils extends org.apache.commons.lang.StringUtils {

    public static String removeEnter(String src) {
        return src.replaceAll("\n", "").replaceAll("\r", "");
    }

    public static String trimAll(String src) {
        return isEmpty(src) ? null : src.replace(" ", "");
    }
}
