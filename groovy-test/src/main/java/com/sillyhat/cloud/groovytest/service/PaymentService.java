package com.sillyhat.cloud.groovytest.service;


import com.sillyhat.cloud.groovytest.model.Basket;
import com.sillyhat.cloud.groovytest.model.Charge;
import com.sillyhat.cloud.groovytest.model.Payment;

public interface PaymentService {

    void openSession();

    Charge payment(Basket basket);

    Payment payment(Payment payment);

    void closeSession();
}
