package pt.iscte.visitor

import pt.iscte.datamodel.*

interface Visitor {
    fun visit(jsonArray: JSONArray): Boolean
    fun visit(jsonNumber: JSONNumber): Boolean
    fun visit(jsonObject: JSONObject): Boolean
    fun visit(jsonString: JSONString): Boolean
    fun visit(jsonValue: JSONValue): Boolean
    fun visit(jsonWhitespace: JSONWhitespace): Boolean
}