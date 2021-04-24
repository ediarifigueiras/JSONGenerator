package pt.iscte.visitor

import pt.iscte.datamodel.*

interface Visitor {
    fun visit(jsonArray: JSONArray): Boolean = true
    fun visit(jsonObject: JSONObject): Boolean = true
    fun visit(jsonBoolean: JSONBoolean) {}
    fun visit(jsonNumber: JSONNumber) {}
    fun visit(jsonString: JSONString) {}
    fun visit(jsonNull: JSONNull) {}
    fun visit(jsonObjectPair: JSONObjectPair) {}
}