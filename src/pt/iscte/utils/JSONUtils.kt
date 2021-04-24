package pt.iscte.utils

import pt.iscte.annotations.ExcludeProperty
import pt.iscte.annotations.Identifier
import pt.iscte.datamodel.JSONNull
import pt.iscte.instantiation.*
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation

class JSONUtils {
    companion object{
        /**
         * Get JSON value of
         * This method converts a Kotlin object to a JSONValue
         * @param it
         */
        fun getJSONValueOf(it: Any) = when (it) {
            is String -> {
                it.toJSONString()
            }
            is Number -> {
                it.toJSONNumber()
            }
            is Boolean -> {
                it.toJSONBoolean()
            }
            is Collection<*> -> {
                it.toJSONArray()
            }
            is Map<*, *> -> {
                it.toJSONObject()
            }
            it::class.isData -> {
                it.toJSONObject()
            }
            else -> JSONNull()
        }

        /**
         * IsExcluded
         * Check if a property has the annotation "Excluded"
         * @param property
         * @return Boolean
         */
        fun isExcluded(property: KProperty1<out Any, Any?>) =
            !property.annotations.contains(element = ExcludeProperty::class.createInstance())

        /**
         * mapIdentifier
         * Check if a property has the annotation "Identifier".
         * If so, it obtains the parameter and use it as an identifier.
         * Otherwise, the property keeps the same name.
         * @param property
         * @return String
         */
        fun mapIdentifier(property: KProperty1<out Any, Any?>) =
            property.findAnnotation<Identifier>()?.identifier ?: property.name
    }
}