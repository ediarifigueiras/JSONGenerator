package test

import pt.iscte.annotations.Identifier
import pt.iscte.instantiation.toJSONObject
import org.junit.Test
import pt.iscte.datamodel.JSONObject
import pt.iscte.datamodel.JSONString
import kotlin.test.assertEquals

class ReflectionInferenceTest {

    enum class Direction {
        NORTH, SOUTH, WEST, EAST
    }

    @Test
    fun dataClassInference(){
        data class Test(@Identifier("nome") val a: String)
        val jsonObjectTest: JSONObject = Test("Joaquim").toJSONObject()
        assertEquals("Joaquim", ((jsonObjectTest.elements[0].value) as JSONString).value)
        assertEquals("nome", jsonObjectTest.elements[0].name)
    }
}