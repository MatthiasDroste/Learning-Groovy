package de.vogella.groovy.datatypes

import de.vogella.groovy.project.Person

class ListMapTest {

	public static void main(args){
		List<Integer> list = [1,2,3,4]
		println list[0]
		println list[1]
		println list[2]
		List<Person> persons = list[]
//		Person p = new Person("Jim", "Knopf") // result would be: groovy.lang.GroovyRuntimeException: Could not find matching constructor for:
		Person p = new Person(firstName: "Jim",lastName: "Knopf"  )
		persons[0] = p
		println persons.size()
		println persons[0].firstName
		println persons.get(0).firstName
	}

}