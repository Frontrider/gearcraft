package hu.frontrider.gearcraft.gears.tooltip

import hu.frontrider.gearcraft.core.util.ChatFormat
import hu.frontrider.gearcraft.core.util.formatTranslate
import hu.frontrider.gearcraft.api.traits.ITooltipped

class InvertedToolTip(val invertMessage: String) : ITooltipped {
    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add(formatTranslate("gearcraft.inverted", ChatFormat.BLUE))
        tooltip.add(formatTranslate(invertMessage, ChatFormat.BLUE))
    }
}