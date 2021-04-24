package pt.iscte.visitor

interface Visited {
    fun accept(visitor: Visitor)
}