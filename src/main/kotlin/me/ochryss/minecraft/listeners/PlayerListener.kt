package me.ochryss.minecraft.listeners

import com.destroystokyo.paper.ParticleBuilder
import me.ochryss.minecraft.ServerSDK
import me.ochryss.minecraft.items.TotemCool
import me.ochryss.minecraft.utils.ItemUtils
import org.bukkit.EntityEffect
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.block.data.Ageable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PlayerListener(val plugin: ServerSDK) : Listener {
    val blockItems = mutableMapOf(
        Pair(Material.WHEAT, Material.WHEAT_SEEDS)
    )

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        val block = event.clickedBlock
        val player = event.player

        if (block == null) return

        val blockData = block.blockData

        if (blockData !is Ageable) return
        if (blockData.age < blockData.maximumAge) return

        val blockType = block.type
        val requiredItem = blockItems[blockType]

        val heldItem = player.inventory.itemInMainHand
        if (ItemUtils.getBooleanData(heldItem, "replant") != true) return
        if (requiredItem == null) return

        if (!player.inventory.contains(requiredItem)) return

        player.breakBlock(block)
        block.type = blockType
    }

    @EventHandler
    fun onCustomTotemActivation(event: PlayerDeathEvent) {
        val player = event.player
        val heldItems = listOf(
            player.inventory.itemInMainHand,
            player.inventory.itemInOffHand
        )

        val requiredItem = TotemCool.item

        // Ensures the player is holding the required item
        // (TotemCool)
        val holdingTotem = heldItems.find { v -> v.isSimilar(requiredItem) }
        if (holdingTotem == null) return

        // Cancels the death and sets the player's hearts to 2
        event.isCancelled = true
        player.health = 4.0

        // Gives the player the respective effects
        player.addPotionEffects(
            listOf(
                PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 1),
                PotionEffect(PotionEffectType.ABSORPTION, 60 * 20, 4),
                PotionEffect(PotionEffectType.STRENGTH, 60 * 20, 4)
            )
        )

        // VFX
        player.world.playSound(player, Sound.ITEM_TOTEM_USE, 1.toFloat(), 1.toFloat())
        player.playEffect(EntityEffect.PROTECTED_FROM_DEATH)

        ParticleBuilder(Particle.TOTEM_OF_UNDYING)
            .location(player.location)
            .count(50)
            .spawn()
    }
}