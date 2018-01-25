package com.sillyhat.groovy.chapter1

import com.sillyhat.groovy.chapter1.obj.Employee

when:"employee list size more than two"
Employee employee = new Employee()
employee.setAge(15)
employee.setFirstName("Shikuan")
employee.setLastName("Xu")
employee.setInTraining(true)

Employee seasoned = new Employee()
seasoned.setAge(18)
seasoned.setFirstName("Wen")
seasoned.setMiddleName("Bo")
seasoned.setLastName("Xu")

List<Employee> employeeList = Arrays.asList(employee,seasoned)

then: "return list"
assert employeeList.size() >= 2

assert employeeList.first().lastName == "Xu"

println "employee age is 15 is ${employee.age == 15}"
println "seasoned age is 15 is ${seasoned.age == 15}"
println 'seasoned age is 18 is ${seasoned.age == 18}'
println "seasoned last name equal employee ${seasoned.lastName == employee.lastName}"

List<Employee> employeeList2 = [
        new Employee(age: 22,firstName: "Shikuan",lastName: "Xu"),
        new Employee(age: 27,firstName: "Shikuan",lastName: "Xu"),
]
assert employeeList.size() >= 4