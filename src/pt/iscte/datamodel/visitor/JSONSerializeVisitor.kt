package pt.iscte.datamodel.visitor

import pt.iscte.datamodel.*

class JSONSerializeVisitor: Visitor {

    var serializedJSON: StringBuilder = StringBuilder()
    private lateinit var jsonRoot: JSONValue
    var rootDepth: Int = 0

    override fun visit(jsonArray: JSONArray): Boolean {
        indentJsonValue(jsonArray)
        serializedJSON.append("[\n")
        return true
    }

    override fun visit(jsonObject: JSONObject): Boolean {
        indentJsonValue(jsonObject)
        serializedJSON.append("{\n")
        return true
    }

    override fun visit(jsonBoolean: JSONBoolean) {
        indentJsonValue(jsonBoolean)
        serializeLeaf(jsonBoolean)
    }

    override fun visit(jsonNumber: JSONNumber) {
        indentJsonValue(jsonNumber)
        serializeLeaf(jsonNumber)
    }

    override fun visit(jsonString: JSONString) {
        indentJsonValue(jsonString)
        serializeLeaf(jsonString)
    }

    override fun visit(jsonNull: JSONNull) {
        indentJsonValue(jsonNull)
        serializeLeaf(jsonNull)
    }

    override fun visit(jsonObjectPair: JSONObjectPair): Boolean {
        indentJson(jsonObjectPair)
        serializedJSON.append("\"${jsonObjectPair.name}\" : ")
        return true
    }

    override fun endVisitJSONArray(jsonArray: JSONArray) {
        closeCompositeIndentation(jsonArray)
        if(isLastElement(jsonArray) || isRoot(jsonArray) || jsonArray.depth - rootDepth == 0)
            serializedJSON.append("]\n")
        else
            serializedJSON.append("],\n")
    }

    override fun endVisitJSONObject(jsonObject: JSONObject) {
        closeCompositeIndentation(jsonObject)
        if(isLastElement(jsonObject) || isRoot(jsonObject) || jsonObject.depth - rootDepth == 0)
            serializedJSON.append("}\n")
        else
            serializedJSON.append("},\n")
    }

    fun initialize(jsonRoot: JSONValue){
        this.jsonRoot=jsonRoot
        rootDepth = jsonRoot.depth
    }

    fun clear(){
        serializedJSON.clear()
    }

    private fun indentJsonValue(jsonValue: JSONValue) {
        if (jsonValue.parent != null && !jsonValue.inObjectPair)
            indentJson(jsonValue)
    }

    private fun closeCompositeIndentation(jsonComposite: JSONValue) {
        if (jsonComposite.parent != null)
            indentJson(jsonComposite)
    }

    private fun indentJson(jsonValue: JSONValue) {
        if (rootDepth == 0)
            serializedJSON.append("\t".repeat(jsonValue.depth))
        else
            serializedJSON.append("\t".repeat(jsonValue.depth - rootDepth))
    }

    private fun serializeLeaf(jsonLeaf: Leaf) {
         if(isLastElement(jsonLeaf) || jsonLeaf.depth - rootDepth == 0)
            serializedJSON.append("${jsonLeaf.serialize()}\n")
         else
            serializedJSON.append("${jsonLeaf.serialize()},\n")
    }

    private fun isLastElement(jsonValue: JSONValue): Boolean {
        val parent = jsonValue.parent
        return if (parent!=null) {
            val elements = jsonValue.parent!!.elements
            val size: Int = elements.size
            val lastElement = elements[size - 1]
            if(lastElement is JSONObjectPair)
                lastElement.value == jsonValue
            else
                lastElement == jsonValue
        } else false
    }

    private fun isRoot(jsonValue: JSONValue): Boolean {
        return jsonValue == jsonRoot
    }
}