package me.ochryss.servidorteste.plugin.managers

import me.ochryss.servidorteste.plugin.MainPlugin
import me.ochryss.servidorteste.plugin.listeners.InventoryListener
import me.ochryss.servidorteste.plugin.listeners.PlayerListener
import org.bukkit.Bukkit

class ListenerManager(val plugin: MainPlugin) {
    val listenerList = mutableListOf(
        PlayerListener(plugin),
        InventoryListener(plugin)
    )

    fun registerListeners() {
        for (listener in listenerList) {
            Bukkit.getPluginManager().registerEvents(listener, plugin)
        }
    }
}