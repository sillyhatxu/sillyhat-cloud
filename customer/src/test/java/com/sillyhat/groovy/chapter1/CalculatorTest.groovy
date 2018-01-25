package com.sillyhat.groovy.chapter1

class CalculatorTest extends spock.lang.Specification{

    public void calculator(){
        expect:
        z == i * j + k

        where:
        i | j | k || z
        1 | 1 | 1 || 2
        2 | 2 | 2 || 6
        3 | 3 | 3 || 12
        4 | 4 | 4 || 20
    }
}

