package hu.frontrider.gearcraft.gears.tooltip

import hu.frontrider.gearcraft.core.util.ChatFormat
import hu.frontrider.gearcraft.core.util.formatTranslate
import hu.frontrider.gearcraft.api.traits.ITooltipped

class DoNotBreakTooltip: ITooltipped {
    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add(formatTranslate("gearcraft.do_not_break", ChatFormat.RED))
    }
}