package pt.iscte.graphicinterface.plugins.presentations

import pt.iscte.datamodel.JSONValue
import pt.iscte.graphicinterface.plugins.TreeSetup
import pt.iscte.utils.JSONUtils

class IconTreeSetup : TreeSetup {
    private val iconsPath = "icons/"
    private val folderIcon = "folder.png"
    private val fileIcon = "file.png"

    override fun getIcon(jsonValue: JSONValue): String {
        return if (JSONUtils.isCompositeValue(jsonValue)){
            iconsPath + folderIcon
        } else {
            iconsPath + fileIcon
        }
    }
}