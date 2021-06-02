package pt.iscte.graphicinterface.plugins

import org.eclipse.swt.widgets.*
import pt.iscte.graphicinterface.mainwindow.Window

interface Action {
    val name: String
    fun execute(tree: Tree, rightLabel: Label, window: Window)
    fun editElementProperty(treeItem: TreeItem, shell: Shell, text: Text): Boolean {return false}
    fun writeTextToFile(tree: Tree, text: Label) {}
    fun validateItem(treeItem: TreeItem) {}
    fun visualizeObject(treeItem: TreeItem) {}
}