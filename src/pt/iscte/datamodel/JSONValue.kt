package pt.iscte.datamodel

import pt.iscte.visitor.Visited

interface JSONValue : Visited {
    fun serialize(): String
}