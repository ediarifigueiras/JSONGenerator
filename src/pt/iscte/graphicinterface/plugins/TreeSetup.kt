package pt.iscte.graphicinterface.plugins

import pt.iscte.datamodel.JSONValue

/**
 * Tree setup interface
 *
 * Interface that represents the TreeSetup Plugin
 *
 * @constructor Create empty Tree setup
 */
interface TreeSetup {
    /**
     * Get icon
     *
     * Implement this method to retrieve based on a condition applied to a JSONValue the icon to be used in the item.
     *
     * @param jsonValue
     * @return Icon file
     * @see pt.iscte.graphicinterface.plugins.presentations.IconTreeSetup
     */
    fun getIcon(jsonValue: JSONValue): String? {return null}

    /**
     * Get name
     *
     * Implement this method to set based on a condition applied to a JSONValue the name to be showed in the tree item.
     * You should return null if you want to keep the tree item name.
     *
     * @param jsonValue
     * @return Tree Item name
     * @see pt.iscte.graphicinterface.plugins.presentations.TextTreeSetup
     */
    fun getName(jsonValue: JSONValue): String? {return null}

    /**
     * Exclude node
     *
     * @param jsonValue
     * @return true if you want to exclude the a treeItem else return false.
     * @see pt.iscte.graphicinterface.plugins.presentations.ExcludeTreeSetup
     */
    fun excludeNode(jsonValue: JSONValue): Boolean {
        return false
    }
}