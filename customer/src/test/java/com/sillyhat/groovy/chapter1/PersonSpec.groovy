package com.sillyhat.groovy.chapter1

import com.sillyhat.groovy.chapter1.obj.SimplePerson

class PersonSpec extends spock.lang.Specification{

    def "Testing getters and setters"(){
        when: "a person has both first name and last name"
        SimplePerson person = new SimplePerson()
        person.firstName = "Shikuan"
        person.lastName = "Xu"
        person.age = 28

        then: "its title should be surname,name,age"
        person.createTitle() == "Xu,Shikuan,28"
    }
}