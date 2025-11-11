package me.ochryss.minecraft.commands.dev

import me.ochryss.minecraft.ServerSDK
import me.ochryss.minecraft.commands.AbstractCommand
import me.ochryss.minecraft.inventory.menus.ItemsMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ItemsCommand(plugin: ServerSDK) : AbstractCommand(plugin) {
    override val commandName = "itens"
    override val commandAliases: List<String> = listOf(

    )
    override val permissionNode = null
    override val consoleEnabled = false

    override fun run(author: CommandSender, args: Array<out String>) {
        ItemsMenu.open(author as Player)
    }
}