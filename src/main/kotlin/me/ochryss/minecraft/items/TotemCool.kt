package me.ochryss.minecraft.items

import me.ochryss.minecraft.utils.Components
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType

object TotemCool {
    val item = ItemBuilder(Material.PAPER)
        .itemName(Components.ampersand("&4Totem :)"))
        .itemModel(NamespacedKey.minecraft("totem_of_undying"))
        .setStringData("itemid", "totemm")
        .build()
}