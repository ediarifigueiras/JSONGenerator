package pt.iscte.datamodel

import pt.iscte.datamodel.visitor.Visitor

/**
 * JSON string
 *
 * A JSON string is a sequence of zero or more Unicode characters, wrapped in double quotes, using backslash escapes.
 * A character is represented as a single character string. A string is very much like a C or Java string.
 *
 * @author edifigueiras
 * @property value
 * @property parent
 * @constructor Create empty J s o n string
 */
class JSONString (var value: String, override var parent: Composite?, override var inObjectPair: Boolean=false): Leaf(parent) {

    /**
     * No-arguments constructor. A no-arguments constructor is used when the JSON String has no parent object.
     */
    constructor(value: String) : this(value,null)

    /**
     * Method of JSONValue. This is used to describe the behaviour of a Visitor object when it visits a JSON String.
     *
     * @param visitor
     */
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    /**
     * Serializes the JSONString.
     *
     * @return String Serialized JSON String
     */
    override fun serialize(): String = "\"$value\""
}