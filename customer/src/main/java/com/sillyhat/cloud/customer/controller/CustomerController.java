package com.sillyhat.cloud.customer.controller;


import com.sillyhat.cloud.customer.domain.Customer;
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
public class CustomerController {

    private CustomerService customerService;

    private CustomerRepository customerRepository;

    @GetMapping(value = "/customers")
    public List<Customer> queryAllCustomerList(){
        return customerService.queryAllCustomerList();
    }

    @PostMapping(value = "/customer")
    public void addCustomer(@RequestBody @NotNull Customer customer){
        customerRepository.save(customer);
    }

    @PutMapping(value = "/customer")
    public void updateCustomer(@RequestBody @NotNull Customer customer){
        customerRepository.save(customer);
    }

    @DeleteMapping(value = "/customer/{id}")
    public void deleteCustomerByPrimaryKey(@PathVariable(value = "id")Long id){
        customerRepository.delete(id);
    }

}
