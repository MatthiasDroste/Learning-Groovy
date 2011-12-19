package de.vogella.groovy.datatypes
// require(groupId:'xmlunit', artifactId:'xmlunit', version:'1.1')
import org.custommonkey.xmlunit.Diff
import org.custommonkey.xmlunit.XMLUnit
import groovy.xml.StreamingMarkupBuilder

def input = '''
<shopping>
 <category type="groceries">
 <item>Chocolate</item>
 <item>Coffee</item>
 </category>
 <category type="supplies">
 <item>Paper</item>
 <item quantity="4">Pens</item>
 </category>
 <category type="present">
 <item when="Aug 10">Kathryn's Birthday</item>
 </category>
</shopping>
'''

def expectedResult = '''
<shopping>
 <category type="groceries">
 </category>
 <category type="supplies">
 <item>Iced Tea</item>
 <item quantity="6" when="Urgent">Pens</item>
 </category>
 <category type="present">
 <item>Mum's Birthday</item>
 <item when="Oct 15">Monica's Birthday</item>
 </category>
 <category>
 <item>Wine</item>
 </category>
</shopping>
'''

def root = new XmlSlurper().parseText(input)

// modify groceries: quality items please
def groceries = root.category.find{ it.@type == 'groceries' }
(0..<groceries.item.size()).each {
 groceries.item[it] = 'Luxury ' + groceries.item[it]
}

// modify supplies: we need extra pens
def pens = root.category.find{ it.@type == 'supplies' }.item.findAll{ it.text() == 'Pens' }
pens.each { p ->
 p.@quantity = (p.@quantity.toInteger() + 2).toString()
 p.@when = 'Urgent'
}

// modify presents: August has come and gone
def presents = root.category.find{ it.@type == 'present' }
presents.replaceNode{ node ->
 category(type:'present'){
 item("Mum's Birthday")
 item("Monica's Birthday", when:'Oct 15')
 }
}

// append child at the end of shopping
root.appendNode {
 category {
 item("Wine")
 }
}

// delete all occurrences of a specific element inside a specific parent
root.category[0].item.replaceNode {}

// update the text of a specific element
root.category[1].item[0] = "Iced Tea"

// check the whole document using XmlUnit
def outputBuilder = new StreamingMarkupBuilder()
String result = outputBuilder.bind{ mkp.yield root }

XMLUnit.setIgnoreWhitespace(true)
def xmlDiff = new Diff(result, expectedResult)
assert xmlDiff.similar()

// check the when attributes (can't do before now due to delayed setting)
def resultRoot = new XmlSlurper().parseText(result)
def removeNulls(list) { list.grep{it} }
assert removeNulls(resultRoot.'*'.item.@when) == [ "Urgent", "Oct 15" ]