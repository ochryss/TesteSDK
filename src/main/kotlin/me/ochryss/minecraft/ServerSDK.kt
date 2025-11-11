package me.ochryss.minecraft

import me.ochryss.minecraft.commands.CommandManager
import me.ochryss.minecraft.managers.VanishManager
import net.luckperms.api.*;
import org.bukkit.Bukkit
import org.bukkit.plugin.RegisteredServiceProvider
import org.bukkit.plugin.java.JavaPlugin

class ServerSDK : JavaPlugin() {
    lateinit var commandManager: CommandManager;
    lateinit var vanishManager: VanishManager;

    override fun onEnable() {
        val provider: RegisteredServiceProvider<LuckPerms>? = Bukkit.getServicesManager().getRegistration(LuckPerms::class.java);
        if (provider != null) {
            val permsApi: LuckPerms = provider.provider;
        }

        // ======[ Vanish Manager ]======
        this.vanishManager = VanishManager(this);
        vanishManager.runService();

        // ======[  Command Registry  ]======
        this.commandManager = CommandManager(this);
        commandManager.registerCommands();
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
