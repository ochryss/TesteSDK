package me.ochryss.servidorteste.plugin.inventory.menus.shop

import me.ochryss.servidorteste.plugin.inventory.Menu
import org.bukkit.Material

object BlocksMenu : Menu(6) {
    val blocks = mapOf(
        Pair(Material.GRASS_BLOCK, 2.0),
        Pair(Material.DIRT, 1.0),
        Pair(Material.STONE, 10.0)
    )

    override fun onSetItems() {
        var index = 10

        for (block in blocks) {
            setItem(index, block.key, {
                it.whoClicked.sendMessage("blablabla ${block.value} dinheiros")
            })
            index++
        }
    }
}