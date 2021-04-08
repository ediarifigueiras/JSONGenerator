package pt.iscte.visitor

import pt.iscte.datamodel.*

// TODO Ver passagem deste generics
class JSONVisitor: Visitor {
    override fun visit(jsonArray: JSONArray): Boolean {
        TODO("Not yet implemented")
    }

    override fun visit(jsonNumber: JSONNumber): Boolean {
        TODO("Not yet implemented")
    }

    override fun visit(jsonObject: JSONObject): Boolean {
        TODO("Not yet implemented")
    }

    override fun visit(jsonString: JSONString): Boolean {
        TODO("Not yet implemented")
    }

    override fun visit(jsonValue: JSONValue): Boolean {
        TODO("Not yet implemented")
    }

    override fun visit(jsonWhitespace: JSONWhitespace): Boolean {
        TODO("Not yet implemented")
    }


}