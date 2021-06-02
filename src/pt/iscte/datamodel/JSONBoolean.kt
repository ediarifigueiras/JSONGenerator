package pt.iscte.datamodel

import pt.iscte.datamodel.visitor.Visitor

/**
 * JSON Boolean
 *
 * This class represents a JSON Boolean value.
 *
 * @author edifigueiras
 * @property value
 * @property parent
 * @constructor Create empty JSON boolean
 */
class JSONBoolean(var value: Boolean, override var parent: Composite?, override var inObjectPair: Boolean=false) : Leaf(parent) {

    /**
     * No-arguments constructor. A no-arguments constructor is used when the JSON Boolean has no parent object.
     */
    constructor(value: Boolean) : this(value,null)

    /**
     * Serializes the JSONBoolean.
     *
     * @return String Serialized JSON Boolean
     */
    override fun serialize(): String = "$value"

    /**
     * Method of JSONValue. This is used to describe the behaviour of a Visitor object when it visits a JSON Boolean.
     *
     * @param visitor
     */
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}