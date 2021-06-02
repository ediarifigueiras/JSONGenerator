package test

import org.junit.Test
import pt.iscte.datamodel.*
import pt.iscte.instantiation.toJSONBoolean
import pt.iscte.instantiation.toJSONNumber
import pt.iscte.instantiation.toJSONString
import kotlin.test.assertEquals

class SearchTest {
    @Test
    fun searchJSONString(){
        val lambda = {jsonValue: JSONValue -> "string2" == (jsonValue as JSONString).value}

        // 1 - Initializing JSONString objects
        val jsonString1: JSONString = "string1".toJSONString()
        val jsonString2: JSONString = "string2".toJSONString()
        val jsonString3: JSONString = "string3".toJSONString()

        // 2 - Initializing JSONNumber objects
        val jsonNumber1: JSONNumber = 2.toJSONNumber()
        val jsonNumber2: JSONNumber = 3.4.toJSONNumber()
        val jsonNumber3: JSONNumber = (3.toDouble() / 2).toJSONNumber()

        // 3 - Initializing JSONBoolean objects
        val jsonBoolean1: JSONBoolean = true.toJSONBoolean()
        val jsonBoolean2: JSONBoolean = false.toJSONBoolean()

        // 4 - Initializing JSONNull objects
        val jsonNull1 = JSONNull()
        val jsonNull2 = JSONNull()

        // 5 - Initializing JSONArray objects
        val jsonArray1 = JSONArray()
        jsonArray1.add(jsonString1)
        jsonArray1.add(jsonNumber1)
        jsonArray1.add(jsonNull1)
        val jsonArray2 = JSONArray()
        jsonArray2.add(jsonBoolean1)
        jsonArray2.add(jsonString2)
        jsonArray2.add(jsonNumber2)
        jsonArray2.add(jsonNull2)
        jsonArray2.add(jsonArray1)

        // 6 - Initializing JSONObject objects
        val jsonObject1 = JSONObject()
        jsonObject1.add("stringName3", jsonString3)
        jsonObject1.add("numberName3", jsonNumber3)
        jsonObject1.add("arrayName2", jsonArray2)
        val jsonObject2 = JSONObject()
        jsonObject2.add("objectName1", jsonObject1)
        jsonObject2.add("booleanName2", jsonBoolean2)

        assertEquals(mutableListOf(JSONString("string2"))[0].value,
            (jsonObject1.filter(lambda)[0] as JSONString).value)
    }
}