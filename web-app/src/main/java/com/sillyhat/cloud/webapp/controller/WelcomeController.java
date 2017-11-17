package com.sillyhat.cloud.webapp.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
public class WelcomeController {

//    @Value("${application.message:Hello World}")
//    private String message = "Hello World";

    @GetMapping("/login")
    public String login(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "Hello World");
        return "login";
    }

}
