package pt.iscte.graphicinterface.plugins.actions

import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Tree
import org.eclipse.swt.widgets.TreeItem
import pt.iscte.datamodel.Composite
import pt.iscte.datamodel.JSONString
import pt.iscte.datamodel.JSONValue
import pt.iscte.graphicinterface.mainwindow.Window
import pt.iscte.graphicinterface.plugins.Action
import pt.iscte.utils.GraphicInterfaceUtils
import pt.iscte.utils.GraphicInterfaceUtils.Companion.getValue

class ValidateAction: Action {
    override val name: String
        get() = "Validate"

    override fun execute(tree: Tree, rightLabel: Label, window: Window) {
        val selectedTreeItem = tree.selection.first()
        validateItem(selectedTreeItem)
    }

    private fun validateItem(treeItem: TreeItem) {
        val predicate = {_: JSONValue -> true}
        val jsonValue = treeItem.getValue()
        if(jsonValue is Composite){
            val list = jsonValue.filter(predicate)
            if (list.all { it is JSONString })
                GraphicInterfaceUtils.showMessageBox(treeItem.parent, "Valid!", "All children are JSONStrings")
            else
                GraphicInterfaceUtils.showMessageBox(treeItem.parent, "Invalid!", "Not all children are JSONStrings")
        }else{
            GraphicInterfaceUtils.showMessageBox(treeItem.parent, "Can't Search!", "The object you are trying to validate is a Leaf")
        }
    }
}