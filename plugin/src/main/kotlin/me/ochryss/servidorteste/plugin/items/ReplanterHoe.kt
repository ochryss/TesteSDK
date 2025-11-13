package me.ochryss.servidorteste.plugin.items

import item.ItemBuilder
import item.Components
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material

object ReplanterHoe {
    val item = ItemBuilder(Material.WOODEN_HOE)
        .lore(Components.builder()
                .addAmpersand("&r&6Replanta automaticamente")
                .addAmpersand("&r&f&lEncantável")
                .toList().map { it.decoration(TextDecoration.ITALIC, false) })
        .setBooleanData("tools.replant", true)
        .build()
}