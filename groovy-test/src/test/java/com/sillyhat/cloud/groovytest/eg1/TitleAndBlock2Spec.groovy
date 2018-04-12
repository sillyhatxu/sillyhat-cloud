package com.sillyhat.cloud.groovytest.eg1

import com.sillyhat.cloud.groovytest.model.Basket
import com.sillyhat.cloud.groovytest.model.Project
import com.sillyhat.cloud.groovytest.service.PaymentService
import com.sillyhat.cloud.groovytest.service.impl.PaymentServiceImpl
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Subject
import spock.lang.Title

@Narrative(""" 
这里写一些对测试内容的描述
Test basket model.
""")
@Title("Unit test for basket price")
@Subject(Basket)//测试的主要对象
class TitleAndBlock2Spec extends spock.lang.Specification{

    @Shared
    PaymentService paymentService

    Basket basket

    def setupSpec() {
        println "---------- setupSpec method ----------"
        paymentService = new PaymentServiceImpl()
        paymentService.openSession()
    }

    def setup() {
        println "---------- setup method ----------"
        basket = new Basket()
    }

    def "method three (standard)"() {
        given: "given block"
        println "---------- [method three] given block ----------"
        Project appleProject = new Project(id: "1",name: "apple",quantity: 2,everyPrice: 130)//动态进行类型检查
        Project orangeProject = new Project(id: 2,name: "orange",quantity: 10,everyPrice: 80)
        Project bananaProject = new Project(id: 3,name: "banana",quantity: 5,everyPrice: 125)

        expect: "expect block"
        println "---------- [method three] expect block ----------"
        basket.getCurrentPrice() == 0

        when: "when block"
        println "---------- [method three] when block ----------"
        basket.addProject(appleProject)
        basket.addProject(orangeProject)
        basket.addProject(bananaProject)

        then: "then block"
        println "---------- [method three] then block ----------"
//        paymentService.payment(basket)

        and: "and block"
        println "---------- [method three] and block 2 ----------"
        basket.getCurrentPrice() == 1685
        basket.getProjectQuantity() == 17
    }

    def cleanup() {
        println "---------- cleanup method ----------"
        basket.clearProject()
    }

    def cleanupSpec() {
        println "---------- cleanupSpec method ----------"
        paymentService.closeSession()
    }
}