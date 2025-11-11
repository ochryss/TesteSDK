package me.ochryss.minecraft.commands

import me.ochryss.minecraft.ServerSDK
import me.ochryss.minecraft.commands.dev.*;

class CommandManager(val plugin: ServerSDK) {
    val commandMap: MutableList<AbstractCommand> = ArrayList();

    init {
        // ======[ Developer ]======
        commandMap.addAll(listOf(
            VanishCommand(plugin) // Comando teste
        ));
    }

    fun registerCommands() {
        for (command in commandMap) {
            plugin.registerCommand(command.commandName, command.commandAliases, command);
        }
    }
}