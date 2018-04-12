package com.sillyhat.cloud.groovytest.service;

import com.sillyhat.cloud.groovytest.exception.GroovyTestException;
import com.sillyhat.cloud.groovytest.model.Charge;

public interface StripeService {

    Charge payment() throws GroovyTestException;
}
