package me.ochryss.servidorteste.plugin.inventory

import net.kyori.adventure.text.Component

class AbstractMenu(
    rows: Int,
    title: Component = Component.text("")
) : Menu(rows, title) {

    override fun onSetItems() {}
}