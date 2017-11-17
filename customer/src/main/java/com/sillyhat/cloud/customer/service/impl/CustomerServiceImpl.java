package com.sillyhat.cloud.customer.service.impl;

import com.sillyhat.cloud.customer.domain.Customer;
import com.sillyhat.cloud.customer.repository.CustomerRepository;
import com.sillyhat.cloud.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepository customerRepository;

    @Override
    public List<Customer> queryAllCustomerList() {
        return customerRepository.findAll();
    }
}
