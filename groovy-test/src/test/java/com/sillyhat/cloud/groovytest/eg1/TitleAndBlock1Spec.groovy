package com.sillyhat.cloud.groovytest.eg1

import com.sillyhat.cloud.groovytest.model.Basket
import com.sillyhat.cloud.groovytest.model.Project
import spock.lang.Narrative
import spock.lang.Subject
import spock.lang.Title

@Narrative(""" 
这里写一些对测试内容的描述
Test basket model.
""")
@Title("Unit test for basket price")
@Subject(Basket)//测试的主要对象
class TitleAndBlock1Spec extends spock.lang.Specification{

    def "method one"() {
        setup: "setup block"//初始条件(与given作用相似，建议使用given)
        println "---------- [method one] setup block ----------"
        Basket basket = new Basket()

        expect: "expect block"//预期结果
        println "---------- [method one] expect block ----------"
        basket.getCurrentPrice() == 0

    }

    def "method two"() {
        given: "given block"//初始条件(与setup作用相似，建议使用given)
        println "---------- [method two] given block ----------"
        Basket basket = new Basket()

        and: "and block"//本身没有功能，主要起拓展作用
        println "---------- [method two] and block ----------"
        Project appleProject = new Project(id: "1",name: "apple",quantity: 2,everyPrice: 130)
        Project orangeProject = new Project(id: 2,name: "orange",quantity: 10,everyPrice: 80)
        Project bananaProject = new Project(id: 3,name: "banana",quantity: 5,everyPrice: 125)

        when: "when block"//测试用例，测试代码
        println "---------- [method two] when block ----------"
        basket.addProject(appleProject)
        basket.addProject(orangeProject)
        basket.addProject(bananaProject)

        then: "then block"//预期结果
        println "---------- [method two] then block ----------"
        basket.getCurrentPrice() == 1685
    }

    /**
     * BDD 范式
     Given the account is in credit
     And the card is valid
     And the cash dispenser contains cash
     When the customer requests cash
     Then ensure the account is debited
     And ensure cash is dispensed
     And ensure the card is returned
     */
    def "method three (standard)"() {
        given: "given block"
        println "---------- [method three] given block ----------"
        Basket basket = new Basket()

        and: "and block"
        println "---------- [method three] and block 1 ----------"
        Project appleProject = new Project(id: "1",name: "apple",quantity: 2,everyPrice: 130)
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
        basket.getCurrentPrice() == 1685

        and: "and block"
        println "---------- [method three] and block 2 ----------"
        basket.getProjectQuantity() == 17
    }
}