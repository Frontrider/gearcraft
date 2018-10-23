package hu.frontrider.gearcraft.core.tooltip

import hu.frontrider.arcana.util.strings.ChatFormat
import hu.frontrider.arcana.util.strings.formatTranslate
import hu.frontrider.gearcraft.api.traits.ITooltipped

class TransmissionTooltip: ITooltipped {
    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add(formatTranslate("gearcraft.transmission",ChatFormat.BLUE))
    }
}