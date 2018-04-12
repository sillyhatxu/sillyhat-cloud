package com.sillyhat.cloud.groovytest.service.impl;

import com.sillyhat.cloud.groovytest.service.CustomerService;
import com.sillyhat.cloud.groovytest.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService{

    private CustomerService customerService;

    public boolean isValidImage(String fileName){
        String lowerCase = fileName.toLowerCase(Locale.ENGLISH);
        return lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".jpg") || lowerCase.endsWith(".png");
    }

    @Override
    public Long getMaxUsableCashbackPrice(Long customerId, Long totalPrice) {
        Long customerCashback = customerService.getCustomerCashback(customerId);
        if(totalPrice < 50)
            return null;
        Long maxUsableCashbackPrice = calculatorMaxUsableCashbackPrice(totalPrice);
        if (maxUsableCashbackPrice >= 50){
            return customerCashback > maxUsableCashbackPrice ? maxUsableCashbackPrice : customerCashback;
        } else {
            return (totalPrice - 50) > customerCashback ? customerCashback : (totalPrice - 50);
        }
    }

    private Long calculatorMaxUsableCashbackPrice(Long totalPrice){
        BigDecimal result = new BigDecimal(totalPrice).multiply(new BigDecimal(70).divide(new BigDecimal(100)));
        return (long) Math.ceil(result.doubleValue());
    }
}
