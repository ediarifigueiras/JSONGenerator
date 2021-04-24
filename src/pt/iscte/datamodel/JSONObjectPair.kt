package pt.iscte.datamodel

import pt.iscte.visitor.Visited
import pt.iscte.visitor.Visitor

class JSONObjectPair constructor (val name: String, val value: JSONValue, val parent: JSONObject): Visited{
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        value.accept(visitor)
    }

    fun serialize():String = "$name: ${value.serialize()}"
}