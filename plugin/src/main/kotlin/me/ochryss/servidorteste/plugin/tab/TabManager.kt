package me.ochryss.servidorteste.plugin.tab

import item.Components
import me.ochryss.servidorteste.plugin.MainPlugin
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class TabManager(val plugin: MainPlugin) {
    lateinit var config: FileConfiguration

    val tabUpdateRate = 250.0

    lateinit var headerLines: List<String>
    lateinit var footerLines: List<String>
    lateinit var animationsIndexes: MutableMap<String, Double>

    fun reloadConfig() {
        plugin.reloadConfig()
        loadConfig()
    }

    fun loadConfig() {
        plugin.saveDefaultConfig()
        config = plugin.config

        headerLines = config.getStringList("tab.header")
        footerLines = config.getStringList("tab.footer")

        animationsIndexes = mutableMapOf()
    }

    fun parseLine(line: String): String {
        val placeholderRegex = Regex("%(\\w+)%")
        val placeholder = placeholderRegex.find(line)

        if (placeholder?.groupValues[1] == null) return line
        val name = placeholder.groupValues[1]
        val path = "tab.animations.$name"
        val configSection = config.getConfigurationSection(path)
            ?: return line

        val updateInterval: Double = (tabUpdateRate / configSection.getInt("update-interval"))
        val texts = configSection.getStringList("texts")

        var textIndex = (animationsIndexes[name] ?: 0.0)
        if (textIndex.toInt() >= texts.size)
            textIndex = 0.0

        val result = texts[textIndex.toInt()] ?: line

        animationsIndexes[name] = textIndex + (updateInterval)

        return result.replace("\t", "   ")
    }

    fun updateTab() {
        Bukkit.getOnlinePlayers().forEach(::updateTabForPlayer)
    }

    fun updateTabForPlayer(player: Player) {
        val header = Components.builder()
        val footer = Components.builder()

        for (line in headerLines) {
            header.addAmpersand(parseLine(line))
        }

        for (line in footerLines) {
            footer.addAmpersand(parseLine(line))
        }

        player.sendPlayerListHeaderAndFooter(
            header.build(true),
            footer.build(true)
        )
    }

    fun startService() {
        Executors.newSingleThreadScheduledExecutor()
            .scheduleAtFixedRate(::updateTab, 0L, tabUpdateRate.toLong(), TimeUnit.MILLISECONDS)
    }
}