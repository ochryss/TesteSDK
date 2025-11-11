package me.ochryss.minecraft.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import java.util.function.Consumer

object Components {
    fun ampersand(value: String): Component {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(value)
    }

    fun builder(): ComponentBuilder {
        return ComponentBuilder()
    }

    class ComponentBuilder {
        val result: Component = Component.empty()

        fun edit(consumer: Consumer<Component>) {
            consumer.accept(result)
        }

        fun addText(value: String, color: TextColor?): ComponentBuilder {
            edit {
                it.append { Component.text(value, color) }
            }

            return this
        }

        fun newline(): ComponentBuilder {
            edit {
                it.appendNewline()
            }

            return this
        }

        fun build(): Component {
            return result
        }
    }
}