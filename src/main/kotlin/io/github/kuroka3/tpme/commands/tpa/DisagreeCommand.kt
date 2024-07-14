package io.github.kuroka3.tpme.commands.tpa

import io.github.kuroka3.tpme.TPMEPlugin
import io.github.kuroka3.tpme.pdc.TPARequestDataType
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class DisagreeCommand: CommandExecutor {

    val tpaReqKey = NamespacedKey(TPMEPlugin.instance, "TPARequest")

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) { sender.sendMessage("This command can only be executed by players"); return false }

        val p: Player = sender
        if (p.persistentDataContainer.has(tpaReqKey)) {
            val tpaReq = p.persistentDataContainer.get(tpaReqKey, TPARequestDataType())!!
            val from = tpaReq.from
            if (from != null) {
                from.sendMessage("거절당함 ㅋ to: ${p.name}")
                p.sendMessage("거절함 ㅋ from: ${from.name}")
            } else {
                p.sendMessage("요청한 샛기 나갓서")
            }

            p.persistentDataContainer.remove(tpaReqKey)
        } else {
            p.sendMessage("받은 요청이 없는데 뭘 거절해 병신새끼야")
        }

        return false
    }
}