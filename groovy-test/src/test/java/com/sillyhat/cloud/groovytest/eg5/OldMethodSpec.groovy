package com.sillyhat.cloud.groovytest.eg5

import com.sillyhat.cloud.groovytest.model.Payment
import com.sillyhat.cloud.groovytest.service.PaymentService
import com.sillyhat.cloud.groovytest.service.TestService
import com.sillyhat.cloud.groovytest.service.impl.PaymentServiceImpl
import spock.lang.*

@Narrative(""" 
check import file is valid image.
""")
@Title("Unit test for TestService")
@Subject(TestService)//测试的主要对象
class OldMethodSpec extends spock.lang.Specification{

    @Shared
    PaymentService paymentService

    @Unroll("payment and get total price : #id")
    def "get max usable cashback price.interval"() {
        given: "initial PaymentService and Payment"
        paymentService = new PaymentServiceImpl()
        Payment payment = new Payment(id: id,totalPrice: totalPrice)

        when: "payment"
        payment = paymentService.payment(payment)

        then: "total price need subtract discountPrice"
        println payment
        payment.getTotalPrice() == old(payment.getTotalPrice()) - payment.getDiscountPrice()

        where: "some scenarios are"
        id | totalPrice
        1  | 800
        2  | 900
        3  | 1111
        4  | 2222
        5  | 3333
        6  | 5555
        7  | 9898

    }

}