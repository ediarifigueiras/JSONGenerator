package pt.iscte.graphicinterface.plugins.actions

import org.eclipse.swt.widgets.*
import pt.iscte.graphicinterface.mainwindow.Window
import pt.iscte.graphicinterface.plugins.Action
import pt.iscte.utils.GraphicInterfaceUtils
import java.io.File


class WriteToFileAction: Action {

    private val filesFolder: String = "files\\"

    override val name: String
        get() = "Write"

    override fun execute(tree: Tree, rightLabel: Label, window: Window) {
        writeTextToFile(tree, rightLabel)
    }

    override fun writeTextToFile(tree: Tree , rightLabel: Label) {
        val file = File(GraphicInterfaceUtils.generateTreeItemFileName(filesFolder, tree.selection.first()))
        file.createNewFile()
        file.writeText(rightLabel.text)
        showMessageBox(rightLabel)
    }

    private fun showMessageBox(rightLabel: Label){
        GraphicInterfaceUtils.showMessageBox(rightLabel, "Success!", "The file was written with success!")
    }

}