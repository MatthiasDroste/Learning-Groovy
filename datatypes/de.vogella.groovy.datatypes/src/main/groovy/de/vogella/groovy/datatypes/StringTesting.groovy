package de.vogella.groovy.datatypes

class StringTesting {
	public static void main(String[] args) {
		def name = "John"
		def s1 = "Hello $name" // $name will be replaced
		def s2 = 'Hello $name' // $name will not be replaced
		println s1
		println s2
		println "double-quoted string's type: " + s1.getClass().getName();
		println "single-quoted string's type: " + s2.getClass().getName();
	}
}
