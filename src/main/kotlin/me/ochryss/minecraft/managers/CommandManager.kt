package me.ochryss.minecraft.managers

import me.ochryss.minecraft.ServerSDK
import me.ochryss.minecraft.commands.dev.ItemsCommand
import me.ochryss.minecraft.commands.dev.VanishCommand

class CommandManager(val plugin: ServerSDK) {
    val commandMap = mutableListOf(
        // ======[ Developer ]======
        VanishCommand(plugin),
        ItemsCommand(plugin)
    )

    fun registerCommands() {
        for (command in commandMap) {
            plugin.registerCommand(command.commandName, command.commandAliases, command)
        }
    }
}