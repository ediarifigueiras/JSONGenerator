package pt.iscte.graphicinterface.plugins

import org.eclipse.swt.widgets.*
import pt.iscte.graphicinterface.mainwindow.Window

interface Action {
    val name: String
    fun execute(tree: Tree, rightLabel: Label, window: Window)
}