package me.ochryss.minecraft

import me.ochryss.minecraft.managers.CommandManager
import me.ochryss.minecraft.managers.ListenerManager
import me.ochryss.minecraft.managers.VanishManager
import net.luckperms.api.*;
import org.bukkit.Bukkit
import org.bukkit.plugin.RegisteredServiceProvider
import org.bukkit.plugin.java.JavaPlugin

class ServerSDK : JavaPlugin() {
    lateinit var commandManager: CommandManager;
    lateinit var vanishManager: VanishManager;
    lateinit var eventManager: ListenerManager;

    override fun onEnable() {
        val provider: RegisteredServiceProvider<LuckPerms>? = Bukkit.getServicesManager().getRegistration(LuckPerms::class.java);
        if (provider != null) {
            val permsApi: LuckPerms = provider.provider;
        }

        // ======[ Vanish Manager ]======
        vanishManager = VanishManager(this);
        vanishManager.runService();

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
