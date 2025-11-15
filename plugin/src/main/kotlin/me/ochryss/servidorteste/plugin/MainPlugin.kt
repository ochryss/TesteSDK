package me.ochryss.servidorteste.plugin

import me.ochryss.servidorteste.plugin.managers.CommandManager
import me.ochryss.servidorteste.plugin.managers.ListenerManager
import me.ochryss.servidorteste.plugin.managers.VanishManager
import me.ochryss.servidorteste.plugin.tab.TabManager
import net.luckperms.api.LuckPerms
import org.bukkit.Bukkit
import org.bukkit.plugin.RegisteredServiceProvider
import org.bukkit.plugin.java.JavaPlugin

class MainPlugin : JavaPlugin() {
    companion object {
        lateinit var INSTANCE: MainPlugin
    }

    val tabManager = TabManager(this)
    val vanishManager = VanishManager(this)
    val eventManager = ListenerManager(this)
    val commandManager = CommandManager(this)

    override fun onEnable() {
        INSTANCE = this

        val provider: RegisteredServiceProvider<LuckPerms>? =
            Bukkit.getServicesManager().getRegistration(LuckPerms::class.java)
        if (provider != null) {
            val permsApi: LuckPerms = provider.provider
        }

        logger.info { "Configurando TAB" }
        // ======[ TAB Manager ]======
        tabManager.reloadConfig()
        tabManager.startService()

        logger.info { "Sucesso! Configurando Vanish" }
        // ======[ Vanish Manager ]======
        vanishManager.runService()

        logger.info { "Sucesso! Carregando comandos & eventos" }
        // ======[ Registry ]======
        commandManager.registerCommands()
        eventManager.registerListeners()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
