package pt.iscte.utils

import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*
import pt.iscte.datamodel.JSONObjectPair
import pt.iscte.datamodel.JSONValue
import pt.iscte.datamodel.visitor.Visitor

class GraphicInterfaceUtils {
    companion object {
        fun showMessageBox(control: Control, title: String, message: String) {
            val box = MessageBox(control.shell, SWT.OK)
            box.text = title
            box.message = message
            box.open()
        }

        fun generateTreeItemFileName(filesFolder: String, treeItem: TreeItem): String {
            val regex = Regex("[^\\w]")
            val selectedTreeItemText = treeItem.text
            val cleanedSelectedTreeItemText = regex.replace(selectedTreeItemText, "")
            return "$filesFolder${cleanedSelectedTreeItemText}.txt"
        }

        fun generateOneFieldPopUp(
            title: String, inputLabel: String,
            buttonLabel: String, shellWidth: Int,
            shellHeight: Int, shellColumns: Int
        ): Triple<Shell, Text, Button> {
            val shell = Shell(Display.getDefault())
            shell.layout = GridLayout(shellColumns, false)
            shell.text = title
            val label = Label(shell, SWT.NONE)
            label.text = "$inputLabel: "
            val text = Text(shell, SWT.BORDER)
            val button = Button(shell, SWT.PUSH)
            button.text = buttonLabel
            shell.pack()
            shell.setSize(shellWidth, shellHeight)
            shell.open()
            return Triple(shell, text, button)
        }

        fun generateViewPopup(
            title: String, content: String, shellWidth: Int,
            shellHeight: Int
        ): Pair<Shell, Button> {
            val shell = Shell(Display.getDefault())
            shell.layout = GridLayout(1, false)
            shell.text = title
            val label = Label(shell, SWT.NONE)
            label.text = "$content"
            val button = Button(shell, SWT.PUSH)
            button.text = "OK"
            button.addSelectionListener(object : SelectionAdapter() {
                override fun widgetSelected(e: SelectionEvent) {
                    shell.close()
                }
            })
            shell.pack()
            shell.open()
            return Pair(shell, button)
        }

        fun TreeItem.depth(): Int =
            if (parentItem == null) 0
            else 1 + parentItem.depth()

        fun Tree.expandAll() = traverse { it.expanded = true }

        private fun Tree.traverse(visitor: (TreeItem) -> Unit) {
            fun TreeItem.traverse() {
                visitor(this)
                items.forEach {
                    it.traverse()
                }
            }
            items.forEach { it.traverse() }
        }

        fun Tree.accept(visitor: Visitor) {
            if (visitor.visit(this))
                items.forEach {
                    it.accept(visitor)
                }
        }

        fun TreeItem.accept(visitor: Visitor) {
            if (visitor.visit(this))
                items.forEach {
                    it.accept(visitor)
                }
        }

        fun TreeItem.getValue(): JSONValue {
            val data = this.data
            return if (data is JSONObjectPair)
                data.getValue()
            else
                (this.data as JSONValue)
        }
    }
}