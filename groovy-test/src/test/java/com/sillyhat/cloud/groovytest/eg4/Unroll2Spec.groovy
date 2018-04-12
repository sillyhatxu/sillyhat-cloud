package com.sillyhat.cloud.groovytest.eg4

import com.sillyhat.cloud.groovytest.service.CustomerService
import com.sillyhat.cloud.groovytest.service.TestService
import com.sillyhat.cloud.groovytest.service.impl.TestServiceImpl
import spock.lang.*

@Narrative(""" 
check import file is valid image.
""")
@Title("Unit test for TestService")
@Subject(TestService)//测试的主要对象
class Unroll2Spec extends spock.lang.Specification{

    @Shared
    TestService testService

    @Unroll("check import file is valid image #fileName result #result")
    def "check import file is valid image"() {
        given: "initial unit test"
        testService = new TestServiceImpl()

        expect: "that all filenames are categorized correctly"
        testService.isValidImage(fileName) == result

        where: "import file names are"
        [fileName,result] << [
            ["success.jpg",true],
            ["success.jpeg",true],
            ["success.png",true],
            ["success.jPg",true],
            ["success.Jpeg",true],
            ["success.pnG",true],
            ["success.JPG",true],
            ["success.JPEG",true],
            ["success.PNG",true],
            ["failure.gif",false]
        ]
    }

    @Unroll("get max usable cashback price getMaxUsableCashbackPrice(#customerId,#totalPrice) == #result")
    def "get max usable cashback price"() {
        given: "initial mock data"
        CustomerService customerService = Stub(CustomerService)
        customerService.getCustomerCashback(_) >> 500//getCustomerCashback包含一个参数

        and: "initial unit test"
        testService = new TestServiceImpl(customerService)

        expect: "that all filenames are categorized correctly"
        testService.getMaxUsableCashbackPrice(customerId,totalPrice) == result

        where: "some scenarios are"
        result  ||customerId | totalPrice
        null    ||    1      |   49
        0       ||    1      |   50
        56      ||    1      |   80
        499     ||    1      |   712
        500     ||    1      |   713
        500     ||    1      |   998
        500     ||    1      |   1100
        500     ||    1      |   8000
    }

    @Unroll("get max usable cashback price.multiple customer getMaxUsableCashbackPrice(#customerId,#totalPrice) == #result")
    def "get max usable cashback price.multiple customer"() {
        given: "initial mock data"
        CustomerService customerService = Stub(CustomerService) {
            getCustomerCashback(1) >> 500
            getCustomerCashback(2) >> 888888
        }//指定查询条件返回值

        and: "initial unit test"
        testService = new TestServiceImpl(customerService)

        expect: "that all filenames are categorized correctly"
        result == testService.getMaxUsableCashbackPrice(customerId,totalPrice)

        where: "some scenarios are"
        result  ||customerId | totalPrice
        null    ||    1      |   49
        0       ||    1      |   50
        56      ||    1      |   80
        499     ||    1      |   712
        500     ||    1      |   713
        500     ||    1      |   998
        500     ||    1      |   1100
        500     ||    1      |   8000
        null    ||    2      |   49
        0       ||    2      |   50
        56      ||    2      |   80
        699     ||    2      |   998
        770     ||    2      |   1100
        5600    ||    2      |   8000
    }

    @Unroll("get max usable cashback price.interval getMaxUsableCashbackPrice(1,#totalPrice) == 500")
    def "get max usable cashback price.interval"() {
        given: "initial mock data"
        CustomerService customerService = Stub(CustomerService)
        customerService.getCustomerCashback(_) >> 500//getCustomerCashback包含一个参数

        and: "initial unit test"
        testService = new TestServiceImpl(customerService)

        expect: "that all filenames are categorized correctly"
        testService.getMaxUsableCashbackPrice(1,0) == 500

        where: "some scenarios are"
        totalPrice << (713..800)
    }

}