package me.ochryss.servidorteste.plugin.inventory

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
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


    companion object {
        fun createWithStack(
            rows: Int,
            title: Component = Component.text(""),
            items: MutableMap<Int, Pair<ItemStack, Consumer<InventoryClickEvent>>>
        ): Menu {
            val menu = AbstractMenu(rows, title)

            for (item in items) {
                val slot = item.key;

                menu.setItem(slot, item.value.first, item.value.second)
            }
            return menu
        }

        fun createWithMaterial(
            rows: Int,
            title: Component = Component.text(""),
            items: MutableMap<Int, Pair<Material, Consumer<InventoryClickEvent>>>
        ): Menu {
            val newItems = mutableMapOf<Int, Pair<ItemStack, Consumer<InventoryClickEvent>>>()
            items.forEach {
                newItems[it.key] = Pair(ItemStack.of(it.value.first), it.value.second)
            }

            return createWithStack(rows, title, newItems)
        }
    }


    override fun setItem(
        slot: Int,
        item: ItemStack,
        consumer: Consumer<InventoryClickEvent>
    ) {
        actions[slot] = consumer
        inventory.setItem(slot, item)
    }

    fun setItem(
        slot: Int,
        item: Material,
        consumer: Consumer<InventoryClickEvent>
    ) {
        actions[slot] = consumer
        inventory.setItem(slot, ItemStack(item))
    }

    fun click(slot: Int, event: InventoryClickEvent) {
        actions[slot]?.accept(event)
    }

    abstract override fun onSetItems()

    override fun getInventory(): Inventory {
        return inv
    }
}