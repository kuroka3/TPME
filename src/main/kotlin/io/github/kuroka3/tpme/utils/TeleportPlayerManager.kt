package io.github.kuroka3.tpme.utils

import io.github.kuroka3.tpme.TPMEPlugin
import io.github.kuroka3.tpme.pdc.LocationArrayDataType
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask

object TeleportPlayerManager {

    val originLocKey = NamespacedKey(TPMEPlugin.instance, "originLoc")

    fun teleportPlayertoLocationLater(target: Player, to: Location, wait: Long, callback: Runnable = Runnable { } ): BukkitTask {
        return Bukkit.getScheduler().runTaskLater(TPMEPlugin.instance, Runnable {
            teleportConfirm(target, to)
            callback.run()
        }, wait)
    }

    fun teleportPlayertoPlayerLater(target: Player, to: Player, wait: Long, callback: Runnable = Runnable { } ): BukkitTask {
        return Bukkit.getScheduler().runTaskLater(TPMEPlugin.instance, Runnable {
            teleportConfirm(target, to.location)
            callback.run()
        }, wait)
    }

    private fun teleportConfirm(target: Player, to: Location) {
        val archive: Array<Location> = target.persistentDataContainer.get(originLocKey, LocationArrayDataType()) ?: emptyArray()
        val new: Array<Location> = Array(minOf(archive.size + 1, 10)) { i ->
            when (i) {
                0 -> target.location
                else -> archive[i - 1]
            }
        }
        target.persistentDataContainer.set(originLocKey, LocationArrayDataType(), new)
        target.teleport(to)
    }
}