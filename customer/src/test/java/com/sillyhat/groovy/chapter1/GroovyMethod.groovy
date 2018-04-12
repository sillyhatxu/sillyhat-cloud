package com.sillyhat.groovy.chapter1

def createName(String firstName,String lastName){
    return "$lastName,$firstName"
}

def createMilitaryName(def firstName,def lastName,def rank){
    return "$lastName,$firstName,($rank)"
}

def fullName = createNam( "Shikuan", "Xu")
println fullName

def militaryName = createMilitaryName "Shikuan", "Xu", "Heihei"
println militaryName
