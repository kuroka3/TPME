package io.github.kuroka3.tpme.pdc

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.util.*

class LocationDataType : PersistentDataType<String, Location> {
    override fun getPrimitiveType(): Class<String> {
        return String::class.java
    }

    override fun getComplexType(): Class<Location> {
        return Location::class.java
    }

    override fun fromPrimitive(p0: String, p1: PersistentDataAdapterContext): Location {
        val obj = JSONParser().parse(p0) as JSONObject
        return Location(
            Bukkit.getWorld(UUID.fromString(obj["world"] as String)),
            obj["x"] as Double,
            obj["y"] as Double,
            obj["z"] as Double,
            (obj["yaw"] as Double).toFloat(),
            (obj["pitch"] as Double).toFloat()
        )
    }

    override fun toPrimitive(p0: Location, p1: PersistentDataAdapterContext): String {
        return JSONObject().apply {
            this["world"] = p0.world.uid.toString()
            this["x"] = p0.x
            this["y"] = p0.y
            this["z"] = p0.z
            this["yaw"] = p0.yaw
            this["pitch"] = p0.pitch
        }.toJSONString()
    }
}