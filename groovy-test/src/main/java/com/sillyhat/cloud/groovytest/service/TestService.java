package com.sillyhat.cloud.groovytest.service;


public interface TestService {

    boolean isValidImage(String fileName);

    Long getMaxUsableCashbackPrice(Long customerId, Long totalPrice);

}
