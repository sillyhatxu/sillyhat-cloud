package com.sillyhat.cloud.webapp.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Slf4j
public class CookieUtils {

    public static void addCookie(HttpServletRequest request,
                                 HttpServletResponse response, String name, String value,
                                 Integer maxAge, String path, String domain, Boolean secure) {
        try {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
            Cookie cookie = new Cookie(name, value);
            if (maxAge != null) {
                cookie.setMaxAge(maxAge);
            }
            if (StringUtils.isNotEmpty(path)) {
                cookie.setPath(path);
            }
            if (StringUtils.isNotEmpty(domain)) {
                cookie.setDomain(domain);
            }
            if (secure != null) {
                cookie.setSecure(secure);
            }
            response.addCookie(cookie);
        } catch (Exception e) {
            log.error("增加cookie出现异常",e);
        }
    }

    public static void addCookie(HttpServletRequest request,HttpServletResponse response, String name, String value) {
        try {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
            Cookie cookie = new Cookie(name, value);
            response.addCookie(cookie);
        } catch (Exception e) {
            log.error("增加cookie出现异常",e);
        }
    }

    /**
     * 根据request获得cookie
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            try {
                name = URLEncoder.encode(name, "UTF-8");
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    }
                }
            } catch (Exception e) {
                log.error("获取cookie出现异常",e);
            }
        }
        return null;
    }
    
}
