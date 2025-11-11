package me.ochryss.minecraft.utils

import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

object ItemUtils {
    fun getBooleanData(item: ItemStack, dataName: String): Boolean? {
        return item.itemMeta.persistentDataContainer.get(
            NamespacedKey("sdk", dataName),
            PersistentDataType.BOOLEAN
        )
    }

    fun getStringData(item: ItemStack, dataName: String): String? {
        return item.itemMeta.persistentDataContainer.get(
            NamespacedKey("sdk", dataName),
            PersistentDataType.STRING
        )
    }

    fun getIntData(item: ItemStack, dataName: String): Int? {
        return item.itemMeta.persistentDataContainer.get(
            NamespacedKey("sdk", dataName),
            PersistentDataType.INTEGER
        )
    }

    fun getFloatData(item: ItemStack, dataName: String): Float? {
        return item.itemMeta.persistentDataContainer.get(
            NamespacedKey("sdk", dataName),
            PersistentDataType.FLOAT
        )
    }

    fun getLongData(item: ItemStack, dataName: String): Long? {
        return item.itemMeta.persistentDataContainer.get(
            NamespacedKey("sdk", dataName),
            PersistentDataType.LONG
        )
    }
}