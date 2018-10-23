package hu.frontrider.gearcraft.core.tooltip

import hu.frontrider.arcana.util.strings.formatTranslate
import hu.frontrider.gearcraft.api.traits.ITooltipped

class PowerTooltip(val power:Int): ITooltipped {
    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add(formatTranslate("gearcraft.powertooltip",power.toString()))
    }
}