package item

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.slf4j.LoggerFactory
import java.util.function.Consumer
import kotlin.collections.mutableListOf

object Components {
    fun ampersand(value: String): Component {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(value)
    }

    fun builder(): ComponentBuilder {
        return ComponentBuilder()
    }

    class ComponentBuilder {
        val result = mutableListOf<Component>()

        fun edit(consumer: Consumer<MutableList<Component>>) {
            consumer.accept(result)
        }

        fun addText(value: String, color: TextColor?): ComponentBuilder {
            edit {
                it.add(Component.text(value, color))
            }

            return this
        }

        fun addAmpersand(value: String): ComponentBuilder {
            var target = value;
            if (value.isEmpty()) target = "&r";
            edit {
                it.add(ampersand(target))
            }

            return this
        }

        fun toList(): List<Component> {
            return result
        }

        fun build(separateByLines: Boolean = false): Component {
            var resultComponent: Component = Component.empty();

            for (line in result) {
                // Separates the components by lines
                if (separateByLines && line != result[0])
                    resultComponent = resultComponent.appendNewline()

                // Adds the content to the newLine
                resultComponent = resultComponent.append(line)
            }

            return resultComponent
        }
    }
}