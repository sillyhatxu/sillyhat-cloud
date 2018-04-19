package com.sillyhat.cloud.customer.controller;


import com.sillyhat.cloud.customer.model.Customer;
import com.sillyhat.cloud.customer.repository.CustomerRepository;
import com.sillyhat.cloud.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class TestController {


    @GetMapping(value = "/test")
    public Customer test(){
        return Customer.builder().id(1L).build();
    }

}
