package me.ochryss.servidorteste.plugin.inventory.menus

import me.ochryss.servidorteste.plugin.inventory.Menu
import me.ochryss.servidorteste.plugin.items.ReplanterHoe
import me.ochryss.servidorteste.plugin.items.TotemCool
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer

object ItemsMenu : Menu(3) {
    val giveClickedItem: (ite: ItemStack) -> Consumer<InventoryClickEvent> = { a ->
        Consumer {
            val player = it.whoClicked as Player

            player.give(a)
        }
    }

    override fun onSetItems() {
        setItem(12, TotemCool.item, giveClickedItem(TotemCool.item))
        setItem(14, ReplanterHoe.item, giveClickedItem(ReplanterHoe.item))
    }
}