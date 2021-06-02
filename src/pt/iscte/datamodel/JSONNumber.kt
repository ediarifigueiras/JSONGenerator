package pt.iscte.datamodel

import pt.iscte.datamodel.visitor.Visitor

/**
 * JSON number
 *
 * This class represents a JSON Number value.
 *
 * @author edifigueiras
 * @property value
 * @property parent
 * @constructor Create empty JSON number
 */
class JSONNumber(var value: Number, override var parent: Composite?, override var inObjectPair: Boolean= false): Leaf(parent) {

    /**
     * No-arguments constructor. A no-arguments constructor is used when the JSON Number has no parent object.
     */
    constructor(value: Number) : this(value,null)

    /**
     * Serializes the JSONNumber.
     *
     * @return String Serialized JSON Number
     */
    override fun serialize(): String = "$value"

    /**
     * Method of JSONValue. This is used to describe the behaviour of a Visitor object when it visits a JSON Number.
     *
     * @param visitor
     */
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}