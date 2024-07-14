package io.github.kuroka3.tpme.pdc

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.util.*

class LocationArrayDataType : PersistentDataType<String, Array<Location>> {
    override fun getPrimitiveType(): Class<String> {
        return String::class.java
    }

    override fun getComplexType(): Class<Array<Location>> {
        return Array<Location>::class.java
    }

    override fun fromPrimitive(p0: String, p1: PersistentDataAdapterContext): Array<Location> {
        val ary = JSONParser().parse(p0) as JSONArray
        val list = mutableListOf<Location>()
        ary.forEach { element ->
            val it = element as JSONObject
            list.add(Location(
                Bukkit.getWorld(UUID.fromString(it["world"] as String)),
                it["x"] as Double,
                it["y"] as Double,
                it["z"] as Double,
                (it["yaw"] as Double).toFloat(),
                (it["pitch"] as Double).toFloat()
            ))
        }
        return list.toTypedArray()
    }

    override fun toPrimitive(p0: Array<Location>, p1: PersistentDataAdapterContext): String {
        val ary = JSONArray()
        p0.forEach { element ->
            ary.add(JSONObject().apply {
                this["world"] = element.world.uid.toString()
                this["x"] = element.x
                this["y"] = element.y
                this["z"] = element.z
                this["yaw"] = element.yaw
                this["pitch"] = element.pitch
            })
        }
        return ary.toJSONString()
    }
}