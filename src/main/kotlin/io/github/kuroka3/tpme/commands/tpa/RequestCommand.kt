package io.github.kuroka3.tpme.commands.tpa

import io.github.kuroka3.tpme.TPMEPlugin
import io.github.kuroka3.tpme.classes.TPARequest
import io.github.kuroka3.tpme.pdc.TPARequestDataType
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RequestCommand : CommandExecutor {

    val tpaReqKey = NamespacedKey(TPMEPlugin.instance, "TPARequest")

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) { sender.sendMessage("This command can only be executed by players"); return false }
        if (args.isEmpty()) { sender.sendMessage("텔레포트할 플레이어를 적어 시발년아"); return false }

        val target = Bukkit.getPlayer(args[0])
        val p: Player = sender

        if (target == null) { sender.sendMessage("지정된 플레이어가 왜 쳐 없어 이새끼들아"); return false }
        else {
            if (target.persistentDataContainer.has(tpaReqKey)) {
                p.sendMessage("이미 요청 받은거 있음 ${target.name} 저 인싸새기")
                return false
            } else {
                target.persistentDataContainer.set(tpaReqKey, TPARequestDataType(), TPARequest(p))
                target.sendMessage("TPA 요청 받으실? from: ${p.name}")
                target.sendMessage("/ㅇ or /ㅗ")
                p.sendMessage("요청 보내긴 함 ${target.name} 이새기가 받을지 안받을진 모름")
                return true
            }
        }
    }
}