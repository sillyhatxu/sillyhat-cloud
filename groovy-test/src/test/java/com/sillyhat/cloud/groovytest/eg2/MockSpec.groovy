package com.sillyhat.cloud.groovytest.eg2

import com.sillyhat.cloud.groovytest.exception.GroovyTestException
import com.sillyhat.cloud.groovytest.model.Basket
import com.sillyhat.cloud.groovytest.model.Charge
import com.sillyhat.cloud.groovytest.model.Project
import com.sillyhat.cloud.groovytest.service.PaymentService
import com.sillyhat.cloud.groovytest.service.StripeService
import com.sillyhat.cloud.groovytest.service.impl.PaymentServiceImpl
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Subject
import spock.lang.Title

@Narrative(""" 
这里写一些对测试内容的描述
Test basket model.
""")
@Title("Unit test for PaymentService")
@Subject(PaymentService)//测试的主要对象
class MockSpec extends spock.lang.Specification{

    @Shared
    PaymentService paymentService

    Basket basket

    def setup() {
        basket = new Basket()
    }

    def "mock stripeService.payment()"() {
        given: "given block"
        Project appleProject = new Project(id: "1",name: "apple",quantity: 2,everyPrice: 130)//动态进行类型检查
        Project orangeProject = new Project(id: 2,name: "orange",quantity: 10,everyPrice: 80)
        Project bananaProject = new Project(id: 3,name: "banana",quantity: 5,everyPrice: 125)

        and:"mock data"
        StripeService stripeService = Stub(StripeService)
        stripeService.payment() >> new Charge(id: 1,uid: 1,price: 1685,result: true,msg: "payment success")
        paymentService = new PaymentServiceImpl(stripeService)

        expect: "expect block"
        basket.getCurrentPrice() == 0

        when: "when block"
        basket.addProject(appleProject)
        basket.addProject orangeProject
        basket.addProject bananaProject
        basket.uid = 1;
        Charge charge = paymentService.payment(basket)

        then: "then block"
        charge.result

        and: "and block"
        charge!=null
        charge.msg == "payment success"
    }

    def "mock stripeService.payment() [every]"() {
        given: "given block"
        Project appleProject = new Project(id: "1",name: "apple",quantity: 2,everyPrice: 130)//动态进行类型检查
        Project orangeProject = new Project(id: 2,name: "orange",quantity: 10,everyPrice: 80)
        Project bananaProject = new Project(id: 3,name: "banana",quantity: 5,everyPrice: 125)

        and:"mock data"
        StripeService stripeService = Stub(StripeService)
        stripeService.payment() >>>
        [
            new Charge(id: 1,uid: 1,price: 1685,result: true,msg: "payment success"),
            {throw new GroovyTestException("payment error")}
        ]

        paymentService = new PaymentServiceImpl(stripeService)

        expect: "expect block"
        basket.getCurrentPrice() == 0

        when: "when block"
        basket.addProject(appleProject)
        basket.addProject orangeProject
        basket.addProject bananaProject
        basket.uid = 1;
        Charge chare1 = paymentService.payment(basket)
        Charge chare2 = paymentService.payment(basket)
        println chare1
        println chare2

        then: "then block"
        chare1.result
        !chare2.result

        and: "and block"
        chare1!=null
        chare1.msg == "payment success"
        chare2!=null
        chare2.msg == "payment error"
    }

    def cleanup() {
        basket.clearProject()
    }
}