package pt.iscte.graphicinterface.plugins

import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Text
import org.eclipse.swt.widgets.Tree
import org.eclipse.swt.widgets.TreeItem
import pt.iscte.graphicinterface.mainwindow.Window

interface Action {
    val name: String
    fun execute(tree: Tree, rightLabel: Label, window: Window)
    fun editElementProperty(treeItem: TreeItem): Boolean {return false}
    fun writeTextToFile(tree: Tree, text: Label) {}
    fun validateItem(treeItem: TreeItem) {}
    fun visualizeObject(treeItem: TreeItem) {}
}