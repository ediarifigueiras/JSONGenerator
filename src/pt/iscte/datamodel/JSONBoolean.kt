package pt.iscte.datamodel

import pt.iscte.visitor.Visitor

class JSONBoolean(val value: Boolean) : Leaf() {

    override fun serialize(): String = "$value"

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}