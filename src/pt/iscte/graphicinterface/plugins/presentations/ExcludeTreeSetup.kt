package pt.iscte.graphicinterface.plugins.presentations

import pt.iscte.datamodel.JSONBoolean
import pt.iscte.datamodel.JSONString
import pt.iscte.datamodel.JSONValue
import pt.iscte.graphicinterface.plugins.TreeSetup
import pt.iscte.utils.JSONUtils.Companion.isSuchObject


class ExcludeTreeSetup: TreeSetup {
    override fun excludeNode(jsonValue: JSONValue): Boolean {
        return jsonValue.isSuchObject(JSONString::class)
    }
}
