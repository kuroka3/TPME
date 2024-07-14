package io.github.kuroka3.tpme

import io.github.kuroka3.tpme.commands.GoBackCommand
import io.github.kuroka3.tpme.commands.home.GoHomeCommand
import io.github.kuroka3.tpme.commands.home.SetHomeCommand
import io.github.kuroka3.tpme.commands.tpa.AgreeCommand
import io.github.kuroka3.tpme.commands.tpa.DisagreeCommand
import io.github.kuroka3.tpme.commands.tpa.RequestCommand
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class TPMEPlugin : JavaPlugin() {
    companion object {
        lateinit var instance: TPMEPlugin
    }

    override fun onEnable() {
        logger.info("TPA HOME Plugin Enabled")

        instance = this

        getCommand("tpa")!!.setExecutor(RequestCommand())
        getCommand("agree")!!.setExecutor(AgreeCommand())
        getCommand("disagree")!!.setExecutor(DisagreeCommand())

        getCommand("sethome")!!.setExecutor(SetHomeCommand())
        getCommand("home")!!.setExecutor(GoHomeCommand())

        getCommand("back")!!.setExecutor(GoBackCommand())
    }

    override fun onDisable() {
        logger.info("TPA HOME Plugin Disabled")
    }
}