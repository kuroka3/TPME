package io.github.kuroka3.tpme.commands.home

import io.github.kuroka3.tpme.TPMEPlugin
import io.github.kuroka3.tpme.pdc.LocationDataType
import io.github.kuroka3.tpme.utils.TeleportPlayerManager
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GoHomeCommand : CommandExecutor {

    val homeKey = NamespacedKey(TPMEPlugin.instance, "home")

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) { sender.sendMessage("This command can only be executed by players"); return false }

        val p: Player = sender

        if (p.persistentDataContainer.has(homeKey)) {
            val home = p.persistentDataContainer.get(homeKey, LocationDataType())!!
            p.sendMessage("3초후 집으로 감")
            TeleportPlayerManager.teleportPlayertoLocationLater(p, home, 60L, Runnable {
                p.sendMessage("키랏☆")
            })
        } else {
            p.sendMessage("집이 없는데 어딜 가 병신아")
        }

        return false
    }
}