package me.ochryss.minecraft.listeners

import me.ochryss.minecraft.ServerSDK
import me.ochryss.minecraft.inventory.Menu
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryListener(val plugin: ServerSDK) : Listener {
    @EventHandler
    fun menuClickEvent(event: InventoryClickEvent) {
        if (event.whoClicked !is Player) return
        if (event.clickedInventory == null) return
        if (event.clickedInventory!!.holder !is Menu) return
        val menu = event.inventory.holder as Menu

        event.isCancelled = true
        event.result = Event.Result.DENY
        menu.click(event.slot, event)
    }
}