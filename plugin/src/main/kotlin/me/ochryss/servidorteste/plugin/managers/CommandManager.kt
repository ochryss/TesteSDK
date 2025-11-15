package me.ochryss.servidorteste.plugin.managers

import me.ochryss.servidorteste.plugin.MainPlugin
import me.ochryss.servidorteste.plugin.commands.dev.ItemsCommand
import me.ochryss.servidorteste.plugin.commands.dev.ReloadTab
import me.ochryss.servidorteste.plugin.commands.dev.VanishCommand

class CommandManager(val plugin: MainPlugin) {
    val commandMap = mutableListOf(
        // ======[ Developer ]======
        VanishCommand(plugin),
        ItemsCommand(plugin),
        ReloadTab(plugin)
    )

    fun registerCommands() {
        for (command in commandMap) {
            plugin.registerCommand(command.commandName, command.commandAliases, command)
        }
    }
}