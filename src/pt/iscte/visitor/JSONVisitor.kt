package pt.iscte.visitor

import pt.iscte.datamodel.*

class JSONVisitor: Visitor {
    /*override fun visit(jsonArray: JSONArray): Boolean {
        //print("\t".repeat(jsonArray.depth))
        println("JSON Array")
        return true
    }

    override fun visit(jsonObject: JSONObject): Boolean {
        //print("\t".repeat(jsonObject.depth))
        println("JSON Object")
        return true
    }

    override fun visit(jsonNumber: JSONNumber): Unit {
        //print("\t".repeat(jsonNumber.depth))
        println(jsonNumber.value)
    }

    override fun visit(jsonString: JSONString): Unit {
        //print("\t".repeat(jsonString.depth))
        println(jsonString.value)
    }

    override fun visit(jsonBoolean: JSONBoolean): Unit {
        //print("\t".repeat(jsonBoolean.depth))
        println(jsonBoolean.value)
    }

    override fun visit(jsonNull: JSONNull) {
        //print("\t".repeat(jsonNull.depth))
        println(jsonNull.serialize())
    }

    override fun visit(jsonObjectPair: JSONObjectPair) {
        //print("\t".repeat(jsonObjectPair.parent.depth))
        println("JSON Object Pair")
        //print("${jsonObjectPair.pair.first}: ")
    }*/

}