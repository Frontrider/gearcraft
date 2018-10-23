package hu.frontrider.gearcraft.core.tooltip

import hu.frontrider.arcana.util.strings.ChatFormat
import hu.frontrider.arcana.util.strings.formatTranslate
import hu.frontrider.gearcraft.api.traits.ITooltipped

class DoNotBreakTooltip: ITooltipped {
    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add(formatTranslate("gearcraft.do_not_break",ChatFormat.RED))
    }
}