package io.github.kuroka3.tpme.events

import io.github.kuroka3.tpme.utils.TeleportPlayerManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class DeathEvent : Listener {
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        TeleportPlayerManager.addPosition(event.player, event.player.location)
        event.player.sendMessage("/back 으로 죽은장소로 ㄱㄱ")
    }
}