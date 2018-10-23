package hu.frontrider.gearcraft.core.tooltip

import hu.frontrider.gearcraft.api.traits.ITooltipped

class MultiTooltip(vararg val tooltips: ITooltipped): ITooltipped {
    override fun setTooltip(tooltip: MutableList<String>?) {
        tooltips.forEach {
            it.setTooltip(tooltip)
        }
    }
}