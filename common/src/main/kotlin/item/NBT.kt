package item

import io.papermc.paper.persistence.PersistentDataViewHolder
import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType

object NBT {
    fun getBooleanData(item: PersistentDataViewHolder, dataName: String): Boolean? {
        return item.persistentDataContainer.get(
            NamespacedKey("sdk", dataName),
            PersistentDataType.BOOLEAN
        )
    }

    fun getStringData(item: PersistentDataViewHolder, dataName: String): String? {
        return item.persistentDataContainer.get(
            NamespacedKey("sdk", dataName),
            PersistentDataType.STRING
        )
    }

    fun getIntData(item: PersistentDataViewHolder, dataName: String): Int? {
        return item.persistentDataContainer.get(
            NamespacedKey("sdk", dataName),
            PersistentDataType.INTEGER
        )
    }

    fun getFloatData(item: PersistentDataViewHolder, dataName: String): Float? {
        return item.persistentDataContainer.get(
            NamespacedKey("sdk", dataName),
            PersistentDataType.FLOAT
        )
    }

    fun getLongData(item: PersistentDataViewHolder, dataName: String): Long? {
        return item.persistentDataContainer.get(
            NamespacedKey("sdk", dataName),
            PersistentDataType.LONG
        )
    }
}