package me.ochryss.servidorteste.plugin.commands.dev

import me.ochryss.servidorteste.plugin.MainPlugin
import me.ochryss.servidorteste.plugin.commands.AbstractCommand
import me.ochryss.servidorteste.plugin.inventory.menus.ItemsMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ReloadTab(plugin: MainPlugin) : AbstractCommand(plugin) {
    override val commandName = "tabreload"
    override val commandAliases: List<String> = listOf(

    )
    override val permissionNode = "sdk.reload"
    override val consoleEnabled = true

    override fun run(author: CommandSender, args: Array<out String>) {
        plugin.tabManager.reloadConfig();
    }
}