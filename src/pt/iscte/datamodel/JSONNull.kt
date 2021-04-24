package pt.iscte.datamodel

import pt.iscte.visitor.Visitor

class JSONNull() : Leaf() {

    override fun serialize(): String = "null"

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}