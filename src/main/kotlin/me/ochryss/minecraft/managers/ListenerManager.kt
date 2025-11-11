package me.ochryss.minecraft.managers

import me.ochryss.minecraft.ServerSDK
import me.ochryss.minecraft.listeners.*
import org.bukkit.Bukkit

class ListenerManager(val plugin: ServerSDK) {
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