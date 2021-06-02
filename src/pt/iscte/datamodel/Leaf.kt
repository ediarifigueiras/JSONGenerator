package pt.iscte.datamodel

/**
 * Leaf
 *
 * This abstract class represents a JSONValue that can't have many JSONValues inside of it.
 * Is the opposite of Composite. Is a terminal path of a root.
 *
 * @author edifigueiras
 * @param parent
 */
abstract class Leaf(parent: Composite?) : JSONValue {
    init {
        parent?.add(this)
    }
}