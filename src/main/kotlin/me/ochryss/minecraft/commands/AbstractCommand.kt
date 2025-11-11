package me.ochryss.minecraft.commands

import io.papermc.paper.command.brigadier.BasicCommand
import io.papermc.paper.command.brigadier.CommandSourceStack
import me.ochryss.minecraft.ServerSDK
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class AbstractCommand(val plugin: ServerSDK) : BasicCommand {
    abstract val commandName: String;
    abstract val commandAliases: List<String>;
    abstract val permissionNode: String?;
    abstract val consoleEnabled: Boolean;

    override fun permission(): String? {
        return permissionNode
    }

    override fun execute(p0: CommandSourceStack, p1: Array<out String>) {
        if (!consoleEnabled && p0.sender !is Player) {
            p0.sender.sendMessage(
                "Este comando não pode ser executado no console!"
            );
            return
        };

        run(p0.sender, p1);
    }

    abstract fun run(author: CommandSender, args: Array<out String>);
 }