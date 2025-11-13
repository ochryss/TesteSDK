package item

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.util.function.Consumer

class ItemBuilder {
    var item: ItemStack

    constructor(material: Material) {
        item = ItemStack.of(material)
    }

    fun edit(consumer: Consumer<ItemMeta>) {
        val meta = item.itemMeta
        consumer.accept(meta)
        item.itemMeta = meta
    }

    fun itemName(name: Component): ItemBuilder {
        edit {
            it.itemName(name)
        }

        return this
    }

    fun lore(lore: List<Component>): ItemBuilder {
        edit {
            it.lore(lore)
        }

        return this
    }

    fun addEnchant(enchantment: Enchantment, level: Int = 1, force: Boolean = false): ItemBuilder {
        edit {
            it.addEnchant(enchantment, level, force)
        }

        return this
    }

    fun itemModel(key: NamespacedKey): ItemBuilder {
        edit {
            it.itemModel = key
        }

        return this
    }

    fun setBooleanData(dataName: String, value: Boolean): ItemBuilder {
        edit {
            it.persistentDataContainer.set(
                NamespacedKey("sdk", dataName),
                PersistentDataType.BOOLEAN, value
            )
        }

        return this
    }

    fun setStringData(dataName: String, value: String): ItemBuilder {
        edit {
            it.persistentDataContainer.set(
                NamespacedKey("sdk", dataName),
                PersistentDataType.STRING, value
            )
        }

        return this
    }

    fun setIntData(dataName: String, value: Int): ItemBuilder {
        edit {
            it.persistentDataContainer.set(
                NamespacedKey("sdk", dataName),
                PersistentDataType.INTEGER, value
            )
        }

        return this
    }

    fun setFloatData(dataName: String, value: Float): ItemBuilder {
        edit {
            it.persistentDataContainer.set(
                NamespacedKey("sdk", dataName),
                PersistentDataType.FLOAT, value
            )
        }

        return this
    }

    fun setLongData(dataName: String, value: Long): ItemBuilder {
        edit {
            it.persistentDataContainer.set(
                NamespacedKey("sdk", dataName),
                PersistentDataType.LONG, value
            )
        }

        return this
    }

    fun build(): ItemStack {
        return item
    }
}