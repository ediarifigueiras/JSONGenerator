package pt.iscte.graphicinterface.plugins.presentations

import pt.iscte.datamodel.JSONObjectPair
import pt.iscte.datamodel.JSONString
import pt.iscte.datamodel.JSONValue
import pt.iscte.graphicinterface.plugins.TreeSetup


class TextTreeSetup: TreeSetup {
    override fun getName(jsonValue: JSONValue): String? {
        if(jsonValue is JSONObjectPair){
            if(jsonValue.value is JSONString)
            return jsonValue.value.serialize()
        }
        return null
    }
}
