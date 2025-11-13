package me.ochryss.servidorteste.plugin.managers

import me.ochryss.servidorteste.plugin.MainPlugin
import item.Components
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class VanishManager(
    val plugin: MainPlugin
) {
    val bypassPermissionNode = "sdk.vanish.see"
    val playerList: MutableList<Player> = ArrayList()

    val updateService: () -> Unit = {
        for (vanished in playerList) {
            vanished.sendActionBar(Components.ampersand("&bVocê está &linvisível&r&b! &8(/v)"))

            for (player in plugin.server.onlinePlayers) {
                if (player.hasPermission(bypassPermissionNode)) {
                    player.showPlayer(plugin, vanished)
                } else {
                    player.hidePlayer(plugin, vanished)
                }
            }
        }
    }

    fun runService() {
        Bukkit.getScheduler().runTaskTimer(plugin, updateService, 5, 5)
    }

    fun addPlayer(player: Player) {
        playerList.add(player)
    }

    fun removePlayer(player: Player) {
        playerList.remove(player)
        for (onlinePlayer in plugin.server.onlinePlayers) {
            onlinePlayer.showPlayer(plugin, player)
        }
    }
}