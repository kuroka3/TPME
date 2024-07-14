package io.github.kuroka3.tpme.commands.home

import io.github.kuroka3.tpme.TPMEPlugin
import io.github.kuroka3.tpme.pdc.LocationDataType
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetHomeCommand : CommandExecutor {

    val homeKey = NamespacedKey(TPMEPlugin.instance, "home")

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) { sender.sendMessage("This command can only be executed by players"); return false }

        val p: Player = sender

        p.persistentDataContainer.set(homeKey, LocationDataType(), p.location)
        p.sendMessage("집 여기로 정함")
        return true
    }
}