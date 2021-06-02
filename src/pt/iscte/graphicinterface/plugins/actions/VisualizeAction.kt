package pt.iscte.graphicinterface.plugins.actions

import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Text
import org.eclipse.swt.widgets.Tree
import org.eclipse.swt.widgets.TreeItem
import pt.iscte.graphicinterface.mainwindow.Window
import pt.iscte.graphicinterface.plugins.Action
import pt.iscte.utils.GraphicInterfaceUtils.Companion.generateViewPopup
import pt.iscte.utils.GraphicInterfaceUtils.Companion.getValue

class VisualizeAction: Action {
    override val name: String
        get() = "Visualize"

    override fun execute(tree: Tree, rightLabel: Label, window: Window) {
        val selectedTreeItem = tree.selection.first()
        visualizeObject(selectedTreeItem)
    }

    private fun visualizeObject(treeItem: TreeItem) {
        val value = treeItem.getValue()
        generateViewPopup("Item Visualization", value.serialize(), 250,250)
    }
}