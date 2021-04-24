package pt.iscte.datamodel

import pt.iscte.visitor.Visitor

class JSONObject() : Composite() {
    override val elements: MutableList<JSONObjectPair> = mutableListOf<JSONObjectPair>()

    override fun accept(visitor: Visitor) {
        if(visitor.visit(this))
            elements.forEach {
                it.accept(visitor)
            }
    }

    override fun serialize(): String =
        elements.joinToString (prefix = "{", separator = ", ", postfix = "}") { it.serialize() }


    fun add(name: String, value: JSONValue) {
        elements.add(JSONObjectPair(name, value, this))
    }

}