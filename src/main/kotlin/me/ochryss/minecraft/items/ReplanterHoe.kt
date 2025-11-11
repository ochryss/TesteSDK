package me.ochryss.minecraft.items

import me.ochryss.minecraft.utils.Components
import org.bukkit.Material

object ReplanterHoe {
    val item = ItemBuilder(Material.WOODEN_HOE)
        .lore(
            listOf(
                Components.ampersand("Replanta plantações automáticamente")
            )
        )
        .setBooleanData("replant", true)
        .build()
}