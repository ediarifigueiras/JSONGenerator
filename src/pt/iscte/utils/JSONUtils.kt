package pt.iscte.utils

import org.eclipse.swt.widgets.Text
import pt.iscte.annotations.ExcludeProperty
import pt.iscte.annotations.Identifier
import pt.iscte.datamodel.Composite
import pt.iscte.datamodel.JSONNull
import pt.iscte.datamodel.JSONObjectPair
import pt.iscte.datamodel.JSONValue
import pt.iscte.datamodel.visitor.JSONSerializeVisitor
import pt.iscte.instantiation.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation

/**
 * JSONUtils
 *
 * Helper class which contains just static methods that can be reused across the application
 *
 * @constructor Create empty JSONUtils
 */
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
            else -> if(it::class.isData) it.toJSONObject() else JSONNull()
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

        /**
         * Is Composite Value
         * Check if a some value is a JSONComposite or not
         * @param o: Any
         * @return Boolean
         */
        fun isCompositeValue(o: Any): Boolean {
            return if (o is JSONObjectPair) {
                o.value is Composite
            } else o is Composite
        }

        /**
         * Is Leaf Value
         * Check if a some value is a JSONLeaf or not
         * @param o: Any
         * @return Boolean
         */
        fun isLeafValue(o: Any) = !isCompositeValue(o)

        /**
         * Is Such Object
         * Check if two JSON values are of the same type
         * @param jsonValue
         * @return
         */
        fun JSONValue.isSuchObject(clazz: KClass<*>): Boolean {
            return if (this::class == JSONObjectPair::class){
                (this as JSONObjectPair).value::class == clazz
            } else {
                clazz == this::class
            }
        }

        fun Text.toValue(): Any? {
            val text = this.text
            return if (text.toIntOrNull()!=null){
                text.toInt()
            }else if(text.matches("-?\\d+(\\.\\d+)?".toRegex())){
                text.toDouble()
            }else if(text.toBooleanStrictOrNull()!=null){
                text.toBoolean()
            }else if(!text.matches("-?\\d+(\\.\\d+)?".toRegex())){
                text
            } else null
        }

        fun JSONValue.serializeWithIndentation(): String {
            val jsonSerializeVisitor = JSONSerializeVisitor()
            jsonSerializeVisitor.initialize(this)
            this.accept(jsonSerializeVisitor)
            val result = jsonSerializeVisitor.serializedJSON.toString()
            jsonSerializeVisitor.clear()
            return result
        }
    }
}