package me.ochryss.servidorteste.plugin.commands.dev

import me.ochryss.servidorteste.plugin.MainPlugin
import me.ochryss.servidorteste.plugin.commands.AbstractCommand
import me.ochryss.servidorteste.plugin.inventory.menus.ItemsMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ItemsCommand(plugin: MainPlugin) : AbstractCommand(plugin) {
    override val commandName = "itens"
    override val commandAliases: List<String> = listOf(

    )
    override val permissionNode = null
    override val consoleEnabled = false

    override fun run(author: CommandSender, args: Array<out String>) {
        ItemsMenu.open(author as Player)
    }
}