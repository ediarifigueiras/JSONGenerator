package pt.iscte.datamodel.visitor

/**
 * Visited
 * Interface to represent a class that can be visited by a Visitor interface implementation.
 */
interface Visited {
    /**
     * Accept
     *
     * @param visitor
     */
    fun accept(visitor: Visitor)
}