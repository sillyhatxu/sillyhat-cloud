package com.sillyhat.groovy.chapter1

import com.sillyhat.groovy.chapter1.obj.Person

class GettersSettersDemo{

    public static void main(String[] args) {
        Person person1 = new Person()
        person1.setFirstName("FirstName")
        person1.setLastName("LastName")
        person1.setAge(11)
        System.out.println("Person first name is" + person1.getFirstName())
        System.out.println("Person last name is" + person1.getLastName())
        System.out.println("Person age is" + person1.getAge())
        println(person1)


        Person person2 = new Person()
        person2.firstName = "First Name 2"
        person2.lastName = "Last Name 2"
        person2.age = 22;
        println("Person first name is" + person2.firstName)
        println("Person last name is" + person2.lastName)
        println("Person age is" + person2.age)
        println(person2)
    }

}