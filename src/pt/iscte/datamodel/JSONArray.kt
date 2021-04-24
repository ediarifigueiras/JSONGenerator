package pt.iscte.datamodel

import pt.iscte.visitor.Visitor

class JSONArray() : Composite() {
    override val elements: MutableList<JSONValue> = mutableListOf<JSONValue>()

    override fun serialize(): String =
        elements.joinToString (prefix = "[", separator = ", ", postfix = "]") { it.serialize() }

    override fun accept(visitor: Visitor) {
        if(visitor.visit(this))
            elements.forEach {
                it.accept(visitor)
            }
    }

    fun add(element: JSONValue) {
        elements.add(element)
    }

}