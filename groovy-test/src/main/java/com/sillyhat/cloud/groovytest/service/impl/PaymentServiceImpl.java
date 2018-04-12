package com.sillyhat.cloud.groovytest.service.impl;

import com.sillyhat.cloud.groovytest.exception.GroovyTestException;
import com.sillyhat.cloud.groovytest.model.Basket;
import com.sillyhat.cloud.groovytest.model.Charge;
import com.sillyhat.cloud.groovytest.model.Payment;
import com.sillyhat.cloud.groovytest.service.PaymentService;
import com.sillyhat.cloud.groovytest.service.StripeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService{

    private StripeService stripeService;

    @Override
    public void openSession() {

    }

    @Override
    public Charge payment(Basket basket){
        try {
            if(basket.isValid()){
                return stripeService.payment();
            }else{
                return Charge.builder().result(false).msg("check error").build();
            }
        } catch (Exception e) {
            return Charge.builder().result(false).msg("payment error").build();
        }
    }

    @Override
    public Payment payment(Payment payment) {
        long discountPrice = getDiscountPrice(1,100);
        payment.setDiscountPrice(discountPrice);
        payment.setTotalPrice(payment.getTotalPrice() - discountPrice);
        return payment;
    }

    private long getDiscountPrice(int min,int max){
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }

    @Override
    public void closeSession() {

    }
}
