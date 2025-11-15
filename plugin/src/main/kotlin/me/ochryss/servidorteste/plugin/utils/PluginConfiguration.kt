package me.ochryss.servidorteste.plugin.utils

import me.ochryss.servidorteste.plugin.MainPlugin
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object PluginConfiguration {
    var plugin = MainPlugin.INSTANCE;
    lateinit var config: YamlConfiguration;

    fun loadConfig(fileName: String): YamlConfiguration {
        val configFile = File(plugin.dataFolder, fileName)

        if (!configFile.exists())
            plugin.saveResource(fileName, false)

        config = YamlConfiguration()

        try {
            config.load(configFile)
        } catch (error: Exception) {
            error.printStackTrace()
        }

        return config
    }
}