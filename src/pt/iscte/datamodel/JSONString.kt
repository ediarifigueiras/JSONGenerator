package pt.iscte.datamodel

import pt.iscte.visitor.Visitor

class JSONString (val value: String): Leaf() {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    override fun serialize(): String = "\"$value\""

}