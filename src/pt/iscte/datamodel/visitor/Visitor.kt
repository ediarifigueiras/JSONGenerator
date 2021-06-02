package pt.iscte.datamodel.visitor

import org.eclipse.swt.widgets.Tree
import org.eclipse.swt.widgets.TreeItem
import pt.iscte.datamodel.*

interface Visitor {
    fun visit(jsonArray: JSONArray): Boolean = true
    fun visit(jsonObject: JSONObject): Boolean = true
    fun visit(jsonBoolean: JSONBoolean) {}
    fun visit(jsonNumber: JSONNumber) {}
    fun visit(jsonString: JSONString) {}
    fun visit(jsonNull: JSONNull) {}
    fun visit(jsonObjectPair: JSONObjectPair): Boolean = true
    fun visit(tree: Tree): Boolean = true
    fun visit(treeItem: TreeItem): Boolean = true
    fun endVisitComposite(composite: Composite) {}
    fun endVisitJSONArray(jsonArray: JSONArray) {}
    fun endVisitJSONObject(jsonObject: JSONObject) {}
    fun endVisitJSONObjectPair(jsonObjectPair: JSONObjectPair) {}
}