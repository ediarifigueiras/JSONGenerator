package pt.iscte.graphicinterface.tree

import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Tree
import org.eclipse.swt.widgets.TreeItem
import pt.iscte.datamodel.*
import pt.iscte.graphicinterface.plugins.TreeSetup
import pt.iscte.graphicinterface.plugins.presentations.ExcludeTreeSetup
import pt.iscte.graphicinterface.plugins.presentations.IconTreeSetup
import pt.iscte.graphicinterface.plugins.presentations.TextTreeSetup
import pt.iscte.datamodel.visitor.Visitor

class JSONTreeVisitor(private val tree: Tree, private val treeSetup: TreeSetup): Visitor {

    private var activatedTreeItem: TreeItem? = null
    private var activatedObjectPairTreeItem: TreeItem? = null

    override fun visit(jsonArray: JSONArray): Boolean {
        if ((treeSetup is ExcludeTreeSetup && !treeSetup.excludeNode(jsonArray)) || treeSetup !is ExcludeTreeSetup) {
            if(activatedObjectPairTreeItem!=null){
                activatedObjectPairTreeItem!!.text += "(Array)"
                if(treeSetup is IconTreeSetup){
                    activatedObjectPairTreeItem!!.image = Image(Display.getDefault(), treeSetup.getIcon(jsonArray))
                }
                activatedTreeItem = activatedObjectPairTreeItem
                activatedObjectPairTreeItem=null
            }else{
                val treeItem: TreeItem = if (activatedTreeItem == null){
                    TreeItem(tree, SWT.NONE)
                }else{
                    TreeItem(activatedTreeItem, SWT.NONE)
                }
                treeItem.text = "(Array)"
                treeItem.data = jsonArray
                if(treeSetup is IconTreeSetup){
                    treeItem.image = Image(Display.getDefault(), treeSetup.getIcon(jsonArray))
                }
                activatedTreeItem = treeItem
            }
        }
        return true
    }

    override fun visit(jsonObject: JSONObject): Boolean {
        if ((treeSetup is ExcludeTreeSetup && !treeSetup.excludeNode(jsonObject)) || treeSetup !is ExcludeTreeSetup) {
            if(activatedObjectPairTreeItem!=null){
                activatedObjectPairTreeItem!!.text += "(Object)"
                if(treeSetup is IconTreeSetup){
                    activatedObjectPairTreeItem!!.image = Image(Display.getDefault(), treeSetup.getIcon(jsonObject))
                }
                activatedTreeItem = activatedObjectPairTreeItem
                activatedObjectPairTreeItem=null
            }else{
                val treeItem: TreeItem = if (activatedTreeItem == null){
                    TreeItem(tree, SWT.NONE)
                }else{
                    TreeItem(activatedTreeItem, SWT.NONE)
                }
                treeItem.text = "(Object)"
                treeItem.data = jsonObject
                if(treeSetup is IconTreeSetup){
                    treeItem.image = Image(Display.getDefault(), treeSetup.getIcon(jsonObject))
                }
                activatedTreeItem = treeItem
            }
        }
        return true
    }

    override fun visit(jsonBoolean: JSONBoolean) {
        if ((treeSetup is ExcludeTreeSetup && !treeSetup.excludeNode(jsonBoolean)) || treeSetup !is ExcludeTreeSetup) {
            if(activatedObjectPairTreeItem!=null){
                activatedObjectPairTreeItem!!.text += jsonBoolean.serialize()
                activatedObjectPairTreeItem=null
            }else{
                val treeItem = TreeItem(activatedTreeItem, SWT.NONE)
                if (treeSetup is TextTreeSetup && treeSetup.getName(jsonBoolean)!=null){
                    treeItem.text = treeSetup.getName(jsonBoolean)
                }else{
                    treeItem.text = jsonBoolean.serialize()
                }
                treeItem.data = jsonBoolean
                if(treeSetup is IconTreeSetup){
                    treeItem.image = Image(Display.getDefault(), treeSetup.getIcon(jsonBoolean))
                }
            }
        }
    }

    override fun visit(jsonNumber: JSONNumber) {
        if ((treeSetup is ExcludeTreeSetup && !treeSetup.excludeNode(jsonNumber)) || treeSetup !is ExcludeTreeSetup) {
            if(activatedObjectPairTreeItem!=null){
                activatedObjectPairTreeItem!!.text += jsonNumber.serialize()
                activatedObjectPairTreeItem=null
            }else{
                val treeItem = TreeItem(activatedTreeItem, SWT.NONE)
                if (treeSetup is TextTreeSetup && treeSetup.getName(jsonNumber)!=null){
                    treeItem.text = treeSetup.getName(jsonNumber)
                }else{
                    treeItem.text = jsonNumber.serialize()
                }
                treeItem.data = jsonNumber
                if(treeSetup is IconTreeSetup){
                    treeItem.image = Image(Display.getDefault(), treeSetup.getIcon(jsonNumber))
                }
            }
        }
    }

    override fun visit(jsonString: JSONString) {
        if ((treeSetup is ExcludeTreeSetup && !treeSetup.excludeNode(jsonString)) || treeSetup !is ExcludeTreeSetup) {
            if(activatedObjectPairTreeItem != null){
                if (activatedObjectPairTreeItem!!.text.contains(":")) {
                    activatedObjectPairTreeItem!!.text += jsonString.serialize()
                }
                activatedObjectPairTreeItem = null
            } else{
                val treeItem = TreeItem(activatedTreeItem, SWT.NONE)
                if (treeSetup is TextTreeSetup && treeSetup.getName(jsonString)!=null){
                    treeItem.text = treeSetup.getName(jsonString)
                }else{
                    treeItem.text = jsonString.serialize()
                }
                treeItem.data = jsonString
                if(treeSetup is IconTreeSetup){
                    treeItem.image = Image(Display.getDefault(), treeSetup.getIcon(jsonString))
                }
            }
        }
    }

    override fun visit(jsonNull: JSONNull) {
        if ((treeSetup is ExcludeTreeSetup && !treeSetup.excludeNode(jsonNull)) || treeSetup !is ExcludeTreeSetup) {
            if(activatedObjectPairTreeItem!=null){
                activatedObjectPairTreeItem!!.text += jsonNull.serialize()
                activatedObjectPairTreeItem=null
            } else{
                val treeItem = TreeItem(activatedTreeItem, SWT.NONE)
                if (treeSetup is TextTreeSetup && treeSetup.getName(jsonNull)!=null){
                    treeItem.text = treeSetup.getName(jsonNull)
                }else{
                    treeItem.text = jsonNull.serialize()
                }
                treeItem.data = jsonNull
                if(treeSetup is IconTreeSetup){
                    treeItem.image = Image(Display.getDefault(), treeSetup.getIcon(jsonNull))
                }
            }
        }
    }

    override fun visit(jsonObjectPair: JSONObjectPair): Boolean {
        if ((treeSetup is ExcludeTreeSetup && !treeSetup.excludeNode(jsonObjectPair)) || treeSetup !is ExcludeTreeSetup) {
            val treeItem = TreeItem(activatedTreeItem, SWT.NONE)
            if (treeSetup is TextTreeSetup && treeSetup.getName(jsonObjectPair)!=null){
                treeItem.text = treeSetup.getName(jsonObjectPair)
            }else{
                treeItem.text = "\"${jsonObjectPair.name}\": "
            }
            treeItem.data = jsonObjectPair
            if(treeSetup is IconTreeSetup){
                treeItem.image = Image(Display.getDefault(), treeSetup.getIcon(jsonObjectPair))
            }
            activatedObjectPairTreeItem = treeItem
        }
        return true
    }

    override fun endVisitComposite(composite: Composite) {
        activatedTreeItem = activatedTreeItem!!.parentItem
    }

    override fun endVisitJSONArray(jsonArray: JSONArray) {
        activatedTreeItem = activatedTreeItem!!.parentItem
    }
}