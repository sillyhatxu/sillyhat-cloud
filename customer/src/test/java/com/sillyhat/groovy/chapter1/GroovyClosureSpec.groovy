package com.sillyhat.groovy.chapter1

import com.sillyhat.groovy.chapter1.obj.FileExtensionFilter

class GroovyClosureSpec extends spock.lang.Specification{

    def "Testing Jpeg files"() {
        when: "only jpeg files are selected from a list of filenames"
        FileExtensionFilter myFilter = new FileExtensionFilter()
        myFilter.addValidExtension("jpeg")
        myFilter.addValidExtension("jpg")

        List<String> testInput = ["image1.jpg","image2.png","image3.jpeg","image4.gif","image5.jpg","image6.tiff"]
        List<String> result = myFilter.filterFileNames(testInput)

        then: "result should not contain other types"
        result.every{
            filename ->
                println filename
                filename.endsWith("jpeg") || filename.endsWith("jpg")
        }
        //Returns true if at least one element satisfies closure
        result.any{
            filename ->
                filename == "image5.jpg"
        }
        String filename = result.find{
            filename ->
                filename == "image5.jpg"
        }
        println "find this file : $filename"
//        any(closure)—Returns true if at least one element satisfies closure
//        find(closure)—Finds the first element that satisfies closure
//        findAll(closure)—Finds all elements that satisfy closure
    }

}

