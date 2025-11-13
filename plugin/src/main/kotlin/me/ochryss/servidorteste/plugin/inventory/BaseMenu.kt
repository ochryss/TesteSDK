package me.ochryss.servidorteste.plugin.inventory

import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer

interface BaseMenu : InventoryHolder {

    fun setItem(slot: Int, item: ItemStack, consumer: Consumer<InventoryClickEvent>)

    fun setItem(slot: Int, item: ItemStack) {
        setItem(slot, item) {}
    }

    fun onSetItems()

    fun open(player: Player) {
        onSetItems()
        player.openInventory(inventory)
    }
}