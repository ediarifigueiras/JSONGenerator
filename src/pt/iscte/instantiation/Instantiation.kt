package pt.iscte.instantiation

import pt.iscte.datamodel.*
import pt.iscte.utils.JSONUtils
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

/**
 * Data Class to JSONArray
 * This method converts a Data Class to a JSONObject
 * @return JSONObject
 */
fun Any.toJSONObject(): JSONObject {
    val clazz: KClass<out Any> = this::class
    if (clazz.isData) {
        val jsonObject = JSONObject()
        clazz.declaredMemberProperties.forEach {
            if (JSONUtils.isExcluded(it)) {
                it.call(this)
                    ?.let { it1 -> JSONUtils.getJSONValueOf(it1) }
                    ?.let { it2 -> jsonObject.add(JSONUtils.mapIdentifier(it), it2) }
            }
        }
        return jsonObject
    } else
        throw Exception()
}


/**
 * Map to JSONObject
 * This method converts a Map<*, *> object to a JSONObject
 * @return JSONObject
 */
fun Map<*, *>.toJSONObject(): JSONObject {
    val jsonObject = JSONObject()
    this.forEach { (any, u) ->
        u?.let { JSONUtils.getJSONValueOf(it) }?.let { jsonObject.add(any.toString(), it) }
    }
    return jsonObject
}

/**
 * Collection to JSONArray
 * This method converts a Collection<*> object to a JSONArray
 * @return JSONArray
 */
fun Collection<*>.toJSONArray(): JSONArray {
    val jsonArray = JSONArray()
    this.forEach {
        if (it != null) {
            val value = JSONUtils.getJSONValueOf(it)
            jsonArray.add(value)
        } else {
            jsonArray.add(JSONNull())
        }
    }
    return jsonArray
}

/**
 * Enum to JSONString
 * This method converts an enum class to a JSONString
 * @return JSONString
 */
fun Enum<*>.toJSONString(): JSONString = JSONString(this.name)

/**
 * To JSON number
 * Extension function that converts Number to JSONNumber
 * @return JSONNumber
 */
fun Number.toJSONNumber(): JSONNumber = JSONNumber(this)

/**
 * To JSON number
 * Extension function that converts Byte to JSONNumber
 * @return JSONNumber
 */
fun Byte.toJSONNumber(): JSONNumber = JSONNumber(this)

/**
 * To JSON number
 * Extension function that converts Short to JSONNumber
 * @return JSONNumber
 */
fun Short.toJSONNumber(): JSONNumber = JSONNumber(this)

/**
 * To JSON number
 * Extension function that converts Int to JSONNumber
 * @return JSONNumber
 */
fun Int.toJSONNumber(): JSONNumber = JSONNumber(this)

/**
 * To JSON number
 * Extension function that converts Long to JSONNumber
 * @return JSONNumber
 */
fun Long.toJSONNumber(): JSONNumber = JSONNumber(this)

/**
 * To JSON number
 * Extension function that converts Float to JSONNumber
 * @return JSONNumber
 */
fun Float.toJSONNumber(): JSONNumber = JSONNumber(this)

/**
 * To JSON number
 * Extension function that converts Double to JSONNumber
 * @return JSONNumber
 */
fun Double.toJSONNumber(): JSONNumber = JSONNumber(this)

/**
 * To JSON Boolean
 * Extension function that converts Boolean to JSONBoolean
 * @return JSONBoolean
 */
fun Boolean.toJSONBoolean(): JSONBoolean = JSONBoolean(this)

/**
 * To JSON String
 * Extension function that converts Char to JSONString
 * @return JSONString
 */
fun Char.toJSONString(): JSONString = JSONString(this.toString())

/**
 * To JSON String
 * Extension function that converts String to JSONString
 * @return JSONString
 */
fun String.toJSONString(): JSONString = JSONString(this)