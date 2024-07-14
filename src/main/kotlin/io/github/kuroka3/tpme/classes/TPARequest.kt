package io.github.kuroka3.tpme.classes

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.util.*

data class TPARequest(val from: Player?) {
    override fun toString(): String {
        val obj = JSONObject()
        obj["from"] = from?.uniqueId.toString()
        return obj.toJSONString()
    }

    companion object {
        fun fromString(str: String): TPARequest {
            val obj = JSONParser().parse(str) as JSONObject
            return TPARequest(Bukkit.getPlayer(UUID.fromString(obj["from"] as String)))
        }
    }
}