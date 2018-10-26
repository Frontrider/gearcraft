package hu.frontrider.gearcraft.tablet.gui.screens

import hu.frontrider.gearcraft.core.gui.BaseGui
import hu.frontrider.gearcraft.core.gui.widgets.TextArea
import hu.frontrider.gearcraft.core.gui.widgets.interfaces.Widget

fun getGuidePage(name:String,baseGui: BaseGui): Array<Widget>{
    val i = (baseGui.getWidth() - BaseGui.xSize) / 2
    val j = (baseGui.getHeight() - BaseGui.ySize) / 2

    return arrayOf(
            TextArea(i + 8, j + 8, BaseGui.ySize - 16, "gearcraft.guide.$name")
    )
}