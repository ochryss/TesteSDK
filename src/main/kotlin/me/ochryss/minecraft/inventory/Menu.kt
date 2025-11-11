package me.ochryss.minecraft.inventory

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer

abstract class Menu(
    rows: Int,
    title: Component = Component.text("")
) : BaseMenu {
    val inv = Bukkit.createInventory(this, 9 * rows, title)
    val actions = mutableMapOf<Int, Consumer<InventoryClickEvent>>()

    override fun setItem(
        slot: Int,
        item: ItemStack,
        consumer: Consumer<InventoryClickEvent>
    ) {
        actions[slot] = consumer
        inventory.setItem(slot, item)
    }

    fun click(slot: Int, event: InventoryClickEvent) {
        actions[slot]?.accept(event)
    }

    abstract override fun onSetItems()

    override fun getInventory(): Inventory {
        return inv
    }
}