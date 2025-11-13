package display

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.entity.Interaction
import org.bukkit.entity.ItemDisplay
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.function.Consumer

class InteractableItemDisplayBuilder() {
    val world = Bukkit.getWorlds()[0]
    val defaultPos = Location(world, 0.0, 0.0, 0.0)
    val display = world.spawn(defaultPos, ItemDisplay::class.java)
    val interaction = world.spawn(defaultPos, Interaction::class.java)

    fun edit(consumer: Consumer<ItemDisplay>) {
        consumer.accept(display)
    }

    fun setItemStack(stack: ItemStack): InteractableItemDisplayBuilder {
        edit {
            it.setItemStack(stack)
        }

        return this
    }
    fun setScale(scale: Float): InteractableItemDisplayBuilder {
        edit {
            val newTransformation = it.transformation
            newTransformation.scale.set(scale)
            it.transformation = newTransformation
        }

        interaction.interactionWidth = scale
        interaction.interactionHeight = scale
        return this
    }

    fun setPersistent(set: Boolean): InteractableItemDisplayBuilder {
        edit {
            it.isPersistent = set
        }
        interaction.isPersistent = set

        return this
    }
    fun setInteractionId(id: String): InteractableItemDisplayBuilder {
        interaction.persistentDataContainer.set(
            NamespacedKey("sdk", "interactionid"),
            PersistentDataType.STRING,
            id
        )

        return this
    }

    fun teleportTo(location: Location): InteractableItemDisplayBuilder {
        location.add(
            0.5*(interaction.interactionWidth/2),
            0.0,
            0.5*(interaction.interactionWidth/2)
        )

        interaction.teleport(location)

        edit {
            it.teleport(location.add(
                0.0,
                0.5*it.transformation.scale.y,
                0.0
            ))
        }
        return this
    }
}