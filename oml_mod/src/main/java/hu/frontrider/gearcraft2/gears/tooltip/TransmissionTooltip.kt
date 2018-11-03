package hu.frontrider.gearcraft.gears.tooltip

import hu.frontrider.gearcraft.core.util.ChatFormat
import hu.frontrider.gearcraft.core.util.formatTranslate
import hu.frontrider.gearcraft2.api.traits.ITooltipped

class TransmissionTooltip: ITooltipped {
    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add(formatTranslate("gearcraft.transmission", ChatFormat.BLUE))
    }
}