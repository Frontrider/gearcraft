package hu.frontrider.gearcraft.gears.tooltip

import hu.frontrider.gearcraft.core.util.formatTranslate
import hu.frontrider.gearcraft2.api.traits.ITooltipped

class PowerTooltip(val power:Int): ITooltipped {
    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add(formatTranslate("gearcraft.powertooltip", power.toString()))
    }
}