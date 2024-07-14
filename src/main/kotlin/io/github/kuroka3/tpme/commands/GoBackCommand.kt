package io.github.kuroka3.tpme.commands

import io.github.kuroka3.tpme.TPMEPlugin
import io.github.kuroka3.tpme.pdc.LocationArrayDataType
import io.github.kuroka3.tpme.utils.TeleportPlayerManager
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GoBackCommand : CommandExecutor {

    val originLocKey = NamespacedKey(TPMEPlugin.instance, "originLoc")

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) { sender.sendMessage("This command can only be executed by players"); return false }

        val p: Player = sender

        if (p.persistentDataContainer.has(originLocKey)) {
            val origin = p.persistentDataContainer.get(originLocKey, LocationArrayDataType())!!
            if (args.isEmpty()) {
                p.sendMessage("3초후 이전 위치로 감")
                TeleportPlayerManager.teleportPlayertoLocationLater(p, origin[0], 60L, Runnable {
                    p.sendMessage("키랏☆")
                })
            } else {
                val count = args[0].toIntOrNull()
                if (count == null) {
                    p.sendMessage("숫자적어 병신아")
                } else if (origin.size < count) {
                    p.sendMessage("니가 그만큼 이동한적이 없는데 뭔 지랄을")
                } else if (count > 10) {
                    p.sendMessage("안타깝게도 back 위치는 10번밖에 저장되지 않습니다 ㅋ")
                } else {
                    p.sendMessage("3초후 ${count}번 전 위치로 감")
                    TeleportPlayerManager.teleportPlayertoLocationLater(p, origin[count - 1], 60L, Runnable {
                        p.sendMessage("키랏☆")
                    })
                }
            }
        } else {
            p.sendMessage("이전에 이동한 적이 없음 님")
        }

        return false
    }
}