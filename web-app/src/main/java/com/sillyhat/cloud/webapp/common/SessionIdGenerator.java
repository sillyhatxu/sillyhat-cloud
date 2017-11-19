package com.sillyhat.cloud.webapp.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class SessionIdGenerator {

    private static char [] chars = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};//"abcdefghijklmnopqrstuvwxyz";

    private static String getUUID(){
        return UUID.randomUUID().toString();
    }

    private static String getJVM(){
        return (System.currentTimeMillis() >>> 8)+"";
    }

    private static String getIP(){
        int result = 0;
        try {
            byte [] bytes = InetAddress.getLocalHost().getAddress();
            for (int i = 0; i < 4; i++) {
                result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
            }
        } catch (UnknownHostException e) {

        }
        return result+"";
    }

    private static String getCharWord(int count){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append(chars[((int)(Math.random() * chars.length))]+"");
        }
        return stringBuilder.toString();
    }


    public static String generateSessionId(){
        String uuid = getUUID();
        return uuid.replaceFirst("-",getCharWord(1)).replaceFirst("-",getCharWord(1)).replaceFirst("-",getCharWord(1)).replaceFirst("-",getCharWord(1)).toUpperCase();
    }

    @Deprecated
    public static String generateSessionIdIP(){
        String uuid = getUUID();
        return uuid.replaceFirst("-",getJVM()).replaceFirst("-",getIP()).replaceFirst("-",getCharWord(1)).replaceFirst("-",getCharWord(1)).toUpperCase();
    }

}
