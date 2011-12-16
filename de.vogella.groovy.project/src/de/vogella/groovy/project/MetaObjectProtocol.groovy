package de.vogella.groovy.project

/** pre-defined handling of non-existing methods/properties */
class MetaObjectProtocol {
	def map

	/** called by default for a non-existing getter */
	Object getProperty (String property){
		println "Setting this propery"
		return 5;
	}

	/** called by default for a non-existing setter */
	void setProperty (String property, Object o ){
		println "Hallo"
	}

	/** called by default for a non-existing method */
	def methodMissing (String name, args){
		def s = name.toUpperCase();
		if (s.startsWith("HELLO")) {
			println "This method stats with Hello. Full name $name"
		} else {
			println "This method is missing"
		}
	}

	public static void main (args){
		def test = new MetaObjectProtocol ();
		test.hall();
		test.helloMethod();
		test.Hallo();
		println "Before: " + test.test
		test.test = 7; //this executes setProperty() BUT DOES NOT SET/CREATE a Property test!!!
		println "After: " + test.test + " => property always returns the same value!";
	}
}
