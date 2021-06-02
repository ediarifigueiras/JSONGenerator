package pt.iscte.datamodel

import pt.iscte.datamodel.visitor.Visited

/**
 * JSON value
 *
 * Interface to represent every possible value used in JSON.
 *
 * @author edifigueiras
 */
interface JSONValue : Visited {
    /**
     * Parent Value of a JSONValue.
     * Parent can be null.
     */
    var parent: Composite?

    /**
     * Retrieves the depth of a JSONValue in a JSON "Tree".
     * The depth of a root element should be zero.
     */
    val depth: Int
        get() = if(parent!=null) 1+ parent?.depth!! else 0

    /**
     * Serializes the JSONValue.
     *
     * @return String Serialized JSON Value
     */
    fun serialize(): String

    var inObjectPair: Boolean
}