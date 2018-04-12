package com.sillyhat.cloud.groovytest.eg1

class BlockSpec extends spock.lang.Specification{

    def setupSpec() {
        println "---------- setupSpec method ----------"
    }

    def setup() {
        println "---------- setup method ----------"
    }

    def "method one,"() {
        setup: "setup block"//初始条件(与given作用相似，建议使用given)
        println "---------- [method one] setup block ----------"

        expect: "expect block"//预期结果
        println "---------- [method one] expect block ----------"

    }

    def "method two"() {
        given: "given block"//初始条件(与setup作用相似，建议使用given)
        println "---------- [method two] given block ----------"

        and: "and block"//本身没有功能，主要起拓展作用
        println "---------- [method two] and block ----------"

        when: "when block"//测试用例，测试代码
        println "---------- [method two] when block ----------"

        then: "then block"//预期结果
        println "---------- [method two] then block ----------"
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

        and: "and block"
        println "---------- [method three] and block 1 ----------"

        expect: "expect block"
        println "---------- [method three] expect block ----------"

        when: "when block"
        println "---------- [method three] when block ----------"

        then: "then block"
        println "---------- [method three] then block ----------"

        and: "and block"
        println "---------- [method three] and block 2 ----------"
    }

    def cleanup() {
        println "---------- cleanup method ----------"
    }

    def cleanupSpec() {
        println "---------- cleanupSpec method ----------"
    }
}