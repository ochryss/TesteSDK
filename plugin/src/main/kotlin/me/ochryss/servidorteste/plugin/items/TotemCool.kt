package me.ochryss.servidorteste.plugin.items

import item.ItemBuilder
import item.Components
import org.bukkit.Material
import org.bukkit.NamespacedKey

object TotemCool {
    val item = ItemBuilder(Material.PAPER)
        .itemName(Components.ampersand("&4Totem :)"))
        .itemModel(NamespacedKey.minecraft("totem_of_undying"))
        .setStringData("itemid", "totemm")
        .build()
}