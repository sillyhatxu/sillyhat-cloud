package com.sillyhat.groovy.chapter1.obj

class SimplePerson {
    String firstName
    String lastName
    int age
    String createTitle(){
        return "$lastName,$firstName,$age"
    }
}