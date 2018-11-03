package hu.frontrider.gearcraft2.gears.tooltip

import hu.frontrider.gearcraft2.api.traits.ITooltipped


class MultiTooltip(vararg val tooltips: ITooltipped): ITooltipped {
    override fun setTooltip(tooltip: MutableList<String>?) {
        tooltips.forEach {
            it.setTooltip(tooltip)
        }
    }
}