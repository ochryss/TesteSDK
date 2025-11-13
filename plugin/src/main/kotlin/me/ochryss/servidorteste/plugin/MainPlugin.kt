package me.ochryss.servidorteste.plugin

import me.ochryss.servidorteste.plugin.managers.CommandManager
import me.ochryss.servidorteste.plugin.managers.ListenerManager
import me.ochryss.servidorteste.plugin.managers.VanishManager
import me.ochryss.servidorteste.plugin.tab.TabManager
import net.luckperms.api.*;
import org.bukkit.Bukkit
import org.bukkit.plugin.RegisteredServiceProvider
import org.bukkit.plugin.java.JavaPlugin

class MainPlugin : JavaPlugin() {
    lateinit var commandManager: CommandManager;
    lateinit var eventManager: ListenerManager;

    lateinit var vanishManager: VanishManager;
    lateinit var tabManager: TabManager;

    override fun onEnable() {
        val provider: RegisteredServiceProvider<LuckPerms>? = Bukkit.getServicesManager().getRegistration(LuckPerms::class.java);
        if (provider != null) {
            val permsApi: LuckPerms = provider.provider;
        }

        logger.info("Loading Managers...")
        // ======[ TAB Manager ]======
        tabManager = TabManager(this);
        tabManager.loadConfig();
        tabManager.startService();

        // ======[ Vanish Manager ]======
        vanishManager = VanishManager(this);
        vanishManager.runService();

        logger.info("Loading Commands & Listeners")
        // ======[ Command Registry ]======
        commandManager = CommandManager(this);
        commandManager.registerCommands();

        // ======[ Listener Registry ]======
        eventManager = ListenerManager(this);
        eventManager.registerListeners();
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
