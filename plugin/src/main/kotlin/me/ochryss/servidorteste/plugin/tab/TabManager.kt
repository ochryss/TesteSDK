package me.ochryss.servidorteste.plugin.tab

import item.Components
import me.ochryss.servidorteste.plugin.MainPlugin
import me.ochryss.servidorteste.plugin.utils.PluginConfiguration
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class TabManager(val plugin: MainPlugin) {
    companion object {
        lateinit var config: YamlConfiguration

        val PLACEHOLDER_REGEX = Regex("%(\\w+)%")
        const val TAB_UPDATE_RATE = 250.0
    }

    var tabSections = listOf<List<String>>()
    var animIndexes = mutableMapOf<String, Int>()

    fun reloadConfig() {
        config = PluginConfiguration.loadConfig("tab.yml")

        tabSections = listOf(
            config.getStringList("tab.header"),
            config.getStringList("tab.footer")
        )

        animIndexes = mutableMapOf()
    }

    fun parseLine(line: String): String {
        val placeholders = PLACEHOLDER_REGEX.findAll(line)
        var result = line

        if (placeholders.toList().isEmpty())
            return line

        for (anim in placeholders) {
            result = result.replace(anim.value, parsePlaceholder(anim.groupValues[1]))
        }

        return result
    }

    fun parsePlaceholder(name: String): String {
        val fallback = "%$name%"
        val path = "animations.$name"

        val animation = config.getConfigurationSection(path)
            ?: return fallback

        val updateInterval = (TAB_UPDATE_RATE / animation.getInt("update-interval"))
        var textIndex = ((animIndexes[name] ?: 0) * updateInterval).toInt()
        val texts = animation.getStringList("texts")

        // Loops through the texts
        if (textIndex >= texts.size) {
            textIndex = 0
            animIndexes[name] = 0
        }

        val result = texts[textIndex] ?: fallback

        animIndexes[name] = (animIndexes[name] ?: 0) + 1

        return result.replace("\t", "   ")
    }

    fun updateTab() {
        Bukkit.getOnlinePlayers().forEach(::updateTabForPlayer)
    }

    fun updateTabForPlayer(player: Player) {
        val header = Components.builder()
        val footer = Components.builder()

        tabSections[0].forEach {
            header.addAmpersand(parseLine(it))
        }

        tabSections[1].forEach {
            footer.addAmpersand(parseLine(it))
        }

        player.sendPlayerListHeaderAndFooter(
            header.build(true),
            footer.build(true)
        )
    }

    fun startService() {
        Executors.newSingleThreadScheduledExecutor()
            .scheduleAtFixedRate(::updateTab, 0L, TAB_UPDATE_RATE.toLong(), TimeUnit.MILLISECONDS)
    }
}