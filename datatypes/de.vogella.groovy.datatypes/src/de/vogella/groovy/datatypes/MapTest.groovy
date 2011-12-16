package de.vogella.groovy.datatypes

class MapTest {
	public static void main(args){
		Map map = [:]
		def map2 = ["Jim":"Knopf", "Thomas":"Edison"]
		println map2["Jim"]
		map2["Test"] = "Tester"
		println map2["Test"]
		
		map.put("aus", "maus")
		map.putAll(1:2, 3:4, "käse":"hoch")
		println(map)
		println(" has type: " + map.getMetaClass().theClass.name)
		println("element types: ")
		map.each {print it.getKey().getClass().name + " "}
		
	}
}
