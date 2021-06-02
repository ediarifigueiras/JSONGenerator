package pt.iscte.datamodel

import pt.iscte.datamodel.visitor.Visitor

/**
 * JSON array
 *
 * This class represents a JSON Array.
 * A JSON array is an ordered collection of JSON Values.
 * A JSON array begins with [left bracket and ends with ]right bracket. Values are separated by ,comma.
 *
 * @author edifigueiras
 * @property parent
 * @constructor Create empty JSON array
 */
class JSONArray(override var parent: Composite?, override var inObjectPair: Boolean=false) : Composite() {

    init {

    }

    /**
     * Mutable List of JSON Values that exists in a JSON Array.
     */
    override val elements: MutableList<JSONValue> = mutableListOf()

    /**
     * No-arguments constructor. A no-arguments constructor is used when the JSON Array has no parent object.
     */
    constructor() : this(null)

    /**
     * Serializes the JSONArray.
     *
     * @return String Serialized JSON Array
     */
    override fun serialize(): String =
        elements.joinToString (prefix = "[", separator = ", ", postfix = "]") { it.serialize() }

    /**
     * Method of JSONValue. This is used to describe the behaviour of a Visitor object when it visits a JSON Array.
     *
     * @param visitor
     */
    override fun accept(visitor: Visitor) {
        if(visitor.visit(this))
            elements.forEach {
                it.accept(visitor)
            }
        visitor.endVisitJSONArray(this)
    }

    /**
     * Add a JSON Value to the JSON Array.
     *
     * @param element
     */
    override fun add(element: JSONValue) {
        element.parent=this
        elements.add(element)
    }

}