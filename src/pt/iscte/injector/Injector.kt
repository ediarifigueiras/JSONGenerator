package pt.iscte.injector

import java.io.File
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.isAccessible

class Injector {

    companion object{
        private const val configurationFilePathName: String = "conf/conf.properties"
        private val mutableMap : MutableMap<String, List<KClass<*>>> = mutableMapOf()

        init {
            val scanner = Scanner(File(configurationFilePathName))
            while (scanner.hasNextLine()){
                val line = scanner.nextLine()
                val parts = line.split("=")
                mutableMap[parts[0]] = parts[1].split(",")
                    .map{ Class.forName(it).kotlin }
            }
            scanner.close()
        }

        fun <T: Any> create(type: KClass<T>): T{
            val o = type.createInstance()
            type.declaredMemberProperties.forEach { it ->
                if(it.hasAnnotation<Inject>()){
                    it.isAccessible = true
                    val key = type.simpleName + "." + it.name
                    val obj = mutableMap[key]!!.first().createInstance()
                    (it as KMutableProperty<*>).setter.call(o, obj)
                }else if(it.hasAnnotation<InjectAdd>()){
                    it.isAccessible = true
                    val key = type.simpleName + "." + it.name
                    val obj = mutableMap[key]!!.map { it.createInstance() }
                    (it.getter.call(o) as MutableList<Any>).addAll(obj)
                }
            }
            return o
        }
    }
}