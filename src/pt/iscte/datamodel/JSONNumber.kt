package pt.iscte.datamodel

import pt.iscte.visitor.Visitor

class JSONNumber(val value: Number): Leaf() {

    override fun serialize(): String = "$value"

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}