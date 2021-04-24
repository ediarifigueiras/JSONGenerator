package pt.iscte.datamodel

import pt.iscte.visitor.Visitor

abstract class Composite: JSONValue {
    var nElements: Int = 0
        get() = elements.size
        set(value) {
            field +=value
        }
    abstract val elements: MutableList<*>

    fun filter(predicate: (JSONValue) -> Boolean): List<JSONValue> {
        val jsonValueVisitor = object : Visitor {
            val listOfJSONValues: MutableList<JSONValue> = mutableListOf()
            override fun visit(jsonString: JSONString) {
                if (predicate(jsonString))
                    listOfJSONValues.add(jsonString)
            }
        }
        this.accept(jsonValueVisitor)
        return jsonValueVisitor.listOfJSONValues
    }
}