package hu.frontrider.gearcraft.tablet.gui

import hu.frontrider.gearcraft.core.gui.BaseGui
import hu.frontrider.gearcraft.core.gui.widgets.ItemButton
import hu.frontrider.gearcraft.tablet.gui.screens.getGuidePage
import hu.frontrider.gearcraft.core.gui.widgets.traits.ClickAction
import hu.frontrider.gearcraft.core.util.translateFormatting
import net.minecraft.item.ItemStack

class GuidePageButton(x: Int,
                      y: Int,
                      itemStack: ItemStack,
                      internalPageName: String,
                      name: String,
                      baseGui: BaseGui,
                      xOffset: Int = 16) :
        ItemButton(x, y, itemStack, internalPageName,
                ClickAction {
                    baseGui.widgets = getGuidePage(name, baseGui)
                }, xOffset){

    override fun postRender(guiBase: BaseGui) {
        itemWidget.postRender(guiBase)
        guiBase.setZlevel(201f)
        guiBase.drawText(x,y+4,width, translateFormatting("gearcraft.guide.page.$display"),false,16777215)
        guiBase.setZlevel(0f)
    }
}