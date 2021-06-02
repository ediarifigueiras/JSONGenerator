package pt.iscte.datamodel

import pt.iscte.datamodel.visitor.Visitor

/**
 * JSON object pair
 *
 * A JSONObjectPair is the unit used to store each element of a JSONObject.
 * A JSONObjectPair is represented by a name mapped and followed after: with a JSONValue.
 *
 * @author edifigueiras
 * @property name
 * @property value
 * @property parent
 * @constructor Create empty JSON object pair
 */
class JSONObjectPair constructor (var name: String, var value: JSONValue, override var parent: Composite?): JSONValue {

    init {
        value.inObjectPair=true
    }

    /**
     * Method of JSONValue. This is used to describe the behaviour of a Visitor object when it visits a JSONObjectPair.
     *
     * @param visitor
     */
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        value.accept(visitor)
        visitor.endVisitJSONObjectPair(this)
    }

    /**
     * Serializes the JSONObjectPair.
     *
     * @return String Serialized JSON Object Pair
     */
    override fun serialize():String = "\"$name\": ${value.serialize()}"

    override var inObjectPair: Boolean = false

    @JvmName("getObjectPairValue")
    fun getValue(): JSONValue{
        return this.value
    }

    @JvmName("setObjectPairValue")
    fun setValue(jsonValue: JSONValue){
        this.value=jsonValue
    }

    @JvmName("getObjectPairName")
    fun getName(): String{
        return this.name
    }

    @JvmName("setObjectPairName")
    fun setName(name: String){
        this.name=name
    }
}