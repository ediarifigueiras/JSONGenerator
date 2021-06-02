package pt.iscte.datamodel

import pt.iscte.datamodel.visitor.Visitor
import kotlin.ClassCastException

/**
 * Composite
 *
 * This abstract class represents a JSONValue that can have many JSONValues inside of it.
 *
 * @author edifigueiras
 * @see JSONValue
 */
abstract class Composite : JSONValue {

    init {
        parent?.add(this)
    }

    /**
     * Retrieves the number of elements that exist in the Composite.
     */
    var nElements: Int = 0
        get() {
            var count = 0
            elements.forEach {
                if (it is Leaf)
                    count++
                else if (it is Composite)
                    count += 1 + it.nElements
            }
            return count
        }

    /**
     * Mutable List of Elements.
     */
    abstract val elements: MutableList<JSONValue>

    /**
     * Method used to add a JSONValue to the Composite. It puts it inside the elements MutableList attribute.
     *
     * @param element
     */
    open fun add(element: JSONValue) {
        elements.add(element)
    }

    /**
     * Filter
     *
     * @param predicate
     * @receiver
     * @return List<JSONValue> List of filtered values.
     */
    fun filter(predicate: (JSONValue) -> Boolean): List<JSONValue> {
        val jsonValueVisitor = object : Visitor {
            val filteredJSONValues: MutableList<JSONValue> = mutableListOf()
            override fun visit(jsonString: JSONString) {
                if (try {
                        predicate(jsonString)
                    } catch (e: ClassCastException) {
                        false
                    }
                )
                    filteredJSONValues.add(jsonString)
            }

            override fun visit(jsonNumber: JSONNumber) {
                if (try {
                        predicate(jsonNumber)
                    } catch (e: ClassCastException) {
                        false
                    }
                )
                    filteredJSONValues.add(jsonNumber)
            }

            override fun visit(jsonBoolean: JSONBoolean) {
                if (try {
                        predicate(jsonBoolean)
                    } catch (e: ClassCastException) {
                        false
                    }
                )
                    filteredJSONValues.add(jsonBoolean)
            }

            override fun visit(jsonNull: JSONNull) {
                if (try {
                        predicate(jsonNull)
                    } catch (e: ClassCastException) {
                        false
                    }
                )
                    filteredJSONValues.add(jsonNull)
            }
        }
        this.accept(jsonValueVisitor)
        return jsonValueVisitor.filteredJSONValues
    }
}