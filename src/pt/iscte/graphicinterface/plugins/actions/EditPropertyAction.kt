package pt.iscte.graphicinterface.plugins.actions

import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*
import pt.iscte.datamodel.*
import pt.iscte.graphicinterface.mainwindow.Window
import pt.iscte.graphicinterface.plugins.Action
import pt.iscte.utils.GraphicInterfaceUtils
import pt.iscte.utils.JSONUtils.Companion.toValue

class EditPropertyAction: Action {
    override val name: String
        get() = "Edit"

    override fun execute(tree: Tree, rightLabel: Label, window: Window) {
        val (shell, text, button) = GraphicInterfaceUtils.generateOneFieldPopUp("Edit", "name", "OK",
            250, 100, 2)
        button.addSelectionListener(object: SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                val item = tree.selection.first()
                if (editElementProperty(item,shell, text)) return
                GraphicInterfaceUtils.showMessageBox(text.shell, "Success!", "Item edited with success!")
                shell.close()
                window.initOrRefreshJsonViewer()
            }
        })
    }

    fun editElementProperty(item: TreeItem, shell: Shell, text: Text): Boolean {
        when (val itemData = item.data) {
            is JSONString -> {
                if (text.toValue() is String) {
                    itemData.value = text.toValue() as String
                    item.text = "\"${text.text}\""
                } else {
                    GraphicInterfaceUtils.showMessageBox(
                        text.shell, "Can't Edit!",
                        "Numbers are not available for JSONString"
                    )
                    shell.close()
                    return true
                }
            }
            is JSONNumber -> {
                if (text.toValue() is Number) {
                    itemData.value = text.toValue() as Number
                    item.text = "\"${text.text}\""
                } else {
                    GraphicInterfaceUtils.showMessageBox(
                        text.shell, "Can't Edit!",
                        "You should insert numbers"
                    )
                    shell.close()
                    return true
                }
            }
            is JSONBoolean -> {
                if (text.toValue() is Boolean) {
                    itemData.value = text.toValue() as Boolean
                    item.text = "\"${text.text}\""
                } else {
                    GraphicInterfaceUtils.showMessageBox(
                        text.shell, "Can't Edit!",
                        "You should insert a boolean value"
                    )
                    shell.close()
                    return true
                }
            }
            is JSONObjectPair -> {
                (itemData as JSONObjectPair).name = text.text
                val string = item.text
                item.text = item.text.replace(
                    string.substring(0, string.indexOf(":")),
                    "\"${text.text}\""
                )
            }
            is JSONNull -> {
                GraphicInterfaceUtils.showMessageBox(
                    text.shell, "Can't Edit!",
                    "You should not edit a JSSONNull Value!"
                )
                return true
            }
        }
        return false
    }
}