package pt.iscte.datamodel

import pt.iscte.datamodel.visitor.Visitor

/**
 * JSON null
 *
 * This class represents a JSON Null value.
 *
 * @author edifigueiras
 * @property parent
 * @constructor Create empty JSON null
 */
class JSONNull(override var parent: Composite?, override var inObjectPair: Boolean=false) : Leaf(parent) {

    /**
     * No-arguments constructor. A no-arguments constructor is used when the JSON Null has no parent object.
     */
    constructor() : this(null)

    /**
     * Serializes the JSONNull.
     *
     * @return String Serialized JSON Null
     */
    override fun serialize(): String = "null"

    /**
     * Method of JSONValue. This is used to describe the behaviour of a Visitor object when it visits a JSON Null.
     *
     * @param visitor
     */
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}