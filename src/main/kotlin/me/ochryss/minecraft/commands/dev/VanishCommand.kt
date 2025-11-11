package me.ochryss.minecraft.commands.dev

import me.ochryss.minecraft.ServerSDK
import me.ochryss.minecraft.commands.AbstractCommand
import me.ochryss.minecraft.features.VanishManager
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class VanishCommand(plugin: ServerSDK) : AbstractCommand(plugin) {
    override val commandName = "vanish";
    override val commandAliases: List<String> = listOf(
        "v"
    );
    override val permissionNode = "sdk.vanish";
    override val consoleEnabled = false;

    override fun run(author: CommandSender, args: Array<out String>) {
        val player: Player = author as Player;
        val manager: VanishManager = plugin.vanishManager;

        when (manager.playerList.contains(player)) {
            false -> manager.addPlayer(player);
            else -> manager.removePlayer(player);
        }
    }
}