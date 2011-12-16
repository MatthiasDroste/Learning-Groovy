package de.vogella.groovy.project

class SumItUp {

	static sum(a,b, c = 0){
		a+b+c;
	}

	static void main(args){
		println sum(1,5)
		println sum(1,2)
		println("Optional parameter c used: " + sum(1,2,3))
	}
}

