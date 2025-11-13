package me.ochryss.servidorteste.plugin.commands.dev

import display.InteractableItemDisplayBuilder
import io.papermc.paper.math.Rotation
import me.ochryss.servidorteste.plugin.MainPlugin
import me.ochryss.servidorteste.plugin.commands.AbstractCommand
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.math.floor
import kotlin.math.round

class SpawnChestCommand(plugin: MainPlugin) : AbstractCommand(plugin) {
    override val commandName = "chest"
    override val commandAliases: List<String> = listOf(

    )
    override val permissionNode = null
    override val consoleEnabled = false

    override fun run(author: CommandSender, args: Array<out String>) {
        val player = author as Player
        val world = player.world;
        val location = player.location.block.location;

        location.rotation = Rotation.rotation(
            0-(round(player.location.rotation.yaw()/90)*90),
            0F
        )

        val itemDisplay = InteractableItemDisplayBuilder()
            .setItemStack(ItemStack.of(Material.CHEST))
            .setPersistent(true)
            .setScale(4F)
            .setInteractionId("bau")
            .teleportTo(location)
    }
}