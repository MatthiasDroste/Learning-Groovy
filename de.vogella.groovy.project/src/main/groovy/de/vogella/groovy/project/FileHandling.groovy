package de.vogella.groovy.project

class FileHandling {
	public static void main(def args){
		// Write just the content of the file to the console
		File file = new File("content.txt")
		file.eachLine{ line -> println line }
		// Adds a line number in front of each line to the console
		def lineNumber = 0;
		file = new File("content.txt")
		file.eachLine{ line ->
			lineNumber++
			println "$lineNumber: $line"
		}
	}

}
