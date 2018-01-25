package com.sillyhat.groovy.chapter1

import com.sillyhat.groovy.chapter1.obj.ShopItem
import com.sillyhat.groovy.chapter1.obj.WordDetector

class ReadFile extends spock.lang.Specification{

    def "demo for reading a csv file all line"() {
        when: "read csv"
        List<ShopItem> shopItemList = new ArrayList<>()
        File csvFile = new File("src/test/resources/ShopItem.csv");
        csvFile.eachLine {
            line, number ->
                String[] fields = line.split(",")
                shopItemList.add(new ShopItem(id: fields[0].toLong(),name: fields[1],currentPrice: fields[2].toLong(),color: fields[3],category: fields[4].toInteger()))
        }
        then: "word frequency should be correct"
        println shopItemList.size()
        assert !shopItemList.isEmpty()
        assert shopItemList.size() == 20
    }

    def "demo for reading a csv file"() {
        when: "a paragraph is processed"
        WordDetector wordDetector = new WordDetector();
        String inputText = new File("src/test/resources/ShopItem.csv").text
        wordDetector.parseText(inputText);

        then: "word frequency should be correct"
        wordDetector.wordsFound() == 61

    }

    def "demo for reading a csv file line by line"() {
        when: "a paragraph is processed"
        List<String> inputText = new File("src/test/resources/ShopItem.csv").readLines()
        WordDetector wordDetector = new WordDetector();
        for(String line:inputText)
        {
            wordDetector.feedText(line)
        }

        then: "word count should be correct"
        println wordDetector.duplicatesFound()
        wordDetector.wordsFound() == 61
    }

}