package com.sillyhat.cloud.webapp.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
public class WelcomeController {

//    @Value("${application.message:Hello World}")
//    private String message = "Hello World";

    @GetMapping("/login")
    public String loginPage(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "Hello World");
        return "login";
    }

    @GetMapping("/index")
    public String index(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "Hello World");
        return "index";
    }

    @PostMapping("/login")
    public String login(Map<String, Object> model,
            @RequestParam(value = "loginName") String loginName,
            @RequestParam(value = "password") String password) {
        model.put("time", new Date());
        model.put("message", "Hello World");
        return "index";
    }

    @GetMapping(value = "/check_login/{loginName}/pd/{password}")
    public String check_login(
            @PathVariable(value = "loginName") Long loginName,
            @PathVariable(value = "password") String password) {
        return "login_error";
    }

}
