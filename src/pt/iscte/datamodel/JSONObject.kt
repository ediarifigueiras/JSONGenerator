package pt.iscte.datamodel

import pt.iscte.datamodel.visitor.Visitor

/**
 * JSON object
 *
 * This class represents a JSON Object.
 * A JSON object is an unordered set of name/value pairs.
 * A JSON object begins with {left brace and ends with }right brace.
 * Each name is followed by :colon and the name/value pairs are separated by ,comma.
 *
 * @author edifigueiras
 * @property parent
 * @constructor Create empty JSON object
 */
class JSONObject(override var parent: Composite?, override var inObjectPair: Boolean=false) : Composite() {
    /**
     * Mutable List of JSON Values that exists in a JSON Array.
     * Actually this will have just JSONObjectPair elements.
     * @see JSONObjectPair
     * @see JSONValue
     */
    override val elements: MutableList<JSONValue> = mutableListOf()

    /**
     * No-arguments constructor. A no-arguments constructor is used when the JSON Object has no parent object.
     */
    constructor() : this(null)

    /**
     * Serializes the JSONObject.
     *
     * @return String Serialized JSON Object
     */
    override fun serialize(): String =
        elements.joinToString (prefix = "{", separator = ", ", postfix = "}") { it.serialize() }

    /**
     * Method of JSONValue. This is used to describe the behaviour of a Visitor object when it visits a JSON Object.
     *
     * @param visitor
     */
    override fun accept(visitor: Visitor) {
        if(visitor.visit(this))
            elements.forEach {
                (it as JSONObjectPair).accept(visitor)
            }
        visitor.endVisitJSONObject(this)
    }

    /**
     * Add a JSONObjectPair to the JSON Object.
     *
     * @param name
     * @param value
     * @see JSONObjectPair
     */
    fun add(name: String, value: JSONValue) {
        value.parent=this
        elements.add(JSONObjectPair(name, value, this))
    }

}