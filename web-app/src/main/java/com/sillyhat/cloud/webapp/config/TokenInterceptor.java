package com.sillyhat.cloud.webapp.config;

import com.sillyhat.cloud.webapp.common.BasePathUtils;
import com.sillyhat.cloud.webapp.common.Constants;
import com.sillyhat.cloud.webapp.common.CookieUtils;
import com.sillyhat.cloud.webapp.common.SessionIdGenerator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@NoArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

//    @Autowired
//    private RedisService redisService;

    /**
     * 在【业务处理器】处理请求之前被调用<br/>
     * <br/>如果返回false<br/>
     * 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链<br/>
     * 如果返回true<br/>
     * 执行下一个拦截器,直到所有的拦截器都执行完毕<br/>
     * 再执行被拦截的Controller<br/>
     * 然后进入拦截器链<br/>
     * 从最后一个拦截器往回执行所有的postHandle()<br/>
     * 接着再从最后一个拦截器往回执行所有的afterCompletion()<br/>
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("【顺序】2【拦截器】TokenInterceptor【操作】preHandle||转交给业务处理器处理请求之前调用此方法。");
        String jsessionid = CookieUtils.getCookie(request, Constants.DEFAULT_COOKIE_JSESSIONID);
        if(jsessionid == null || "".equals(jsessionid)){
            CookieUtils.addCookie(request, response, Constants.DEFAULT_COOKIE_JSESSIONID, SessionIdGenerator.generateSessionId());
        }
        return true;
//        String token = CookieUtils.getCookie(request, "token");
//        if (request.getMethod().equalsIgnoreCase("POST")) {
//            //header为null则为传统同步请求,若为XMLHttpRequest则为Ajax请求
//            String header = request.getHeader("X-Requested-With");
//            if (header != null && header.equalsIgnoreCase("XMLHttpRequest")) {
//                if (token != null && token.equals(request.getHeader("token"))) {
//                    return true;
//                }
//                response.addHeader("tokenStatus", "accessDenied");
//            } else if (token != null && token.equals(request.getParameter("token"))) {
//                return true;
//            }
//            if (token == null) {
//                token = UUID.randomUUID().toString();
//                //CookieUtils.addCookie(request, response, "token", token);
//            }
//            response.sendError(403, "Bad or missing token!");
//            return false;
//        }
//        if (token == null) {
//            token = UUID.randomUUID().toString();
//            //CookieUtils.addCookie(request, response, "token", token);
//        }
//        request.setAttribute("token", token);
//        return true;
    }

    /**
     * 在业务处理器处理请求、执行完成后,生成视图之前执行的动作<br/>
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("【顺序】2【拦截器】TokenInterceptor【操作】postHandle||在响应客户端请求,生成试图请求之前调用此方法。");
        //添加basepath
        if(modelAndView!=null){
            String basePath = BasePathUtils.getBasePath(request, response);
            modelAndView.addObject("ctx", basePath);
        }
    }


    /**
     * 在DispatcherServlet完全处理完请求后被调用
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String jsessionid = CookieUtils.getCookie(request, Constants.DEFAULT_COOKIE_JSESSIONID);
//        if(redisService.exists(Constants.CURRENT_USER+jsessionid)){
//            log.info("当前登录人：{}", (UserDTO) redisService.get(Constants.CURRENT_USER+jsessionid));
//        }
        log.info("【顺序】2【拦截器】TokenInterceptor【操作】afterCompletion||响应完客服端的请求之后调用此方法。");
    }
}
