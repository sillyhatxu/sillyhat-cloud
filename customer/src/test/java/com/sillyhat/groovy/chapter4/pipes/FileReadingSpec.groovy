package com.sillyhat.groovy.chapter4.pipes

import com.sillyhat.groovy.chapter4.params.dto.ImageNameValidator
import spock.lang.Unroll

class FileReadingSpec extends spock.lang.Specification{

	
	@Unroll("Checking image name #pictureFile")
	def "Valid images are PNG and JPEG files"() {
		given: "an image extension checker"
		ImageNameValidator validator = new ImageNameValidator()
		
		expect: "that all filenames are accepted"
		validator.isValidImageExtension(pictureFile)
		
		where: "sample image names are"
		pictureFile       << new File("src/test/resources/validImageNames.txt").readLines()
	}
	
	
}

