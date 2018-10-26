package hu.frontrider.gearcraft.tablet.gui

import hu.frontrider.gearcraft.core.gui.BaseGui
import hu.frontrider.gearcraft.core.gui.widgets.TabButton
import hu.frontrider.gearcraft.core.gui.widgets.traits.ClickAction
import hu.frontrider.gearcraft.tablet.TabletReferences.BOOK
import hu.frontrider.gearcraft.tablet.TabletReferences.REDSTONE
import hu.frontrider.gearcraft.tablet.gui.screens.buildRedstoneScreen
import hu.frontrider.gearcraft.tablet.gui.screens.getGuide
import net.minecraft.item.ItemStack

//the instance for the tablet GUI
object TabletGui : BaseGui() {



    lateinit var itemStack: ItemStack

    var initialised = false

    override fun initGui() {
        if(initialised)
            return

        super.initGui()
        val i = (this.width - xSize) / 2
        val j = (this.height - ySize) / 2

        staticWidgets = arrayOf(
                TabButton(i - 28, j, BOOK, ClickAction {
                    widgets = getGuide(this)
                }),
                TabButton(i - 28, j + 18, REDSTONE, ClickAction {
                    widgets = buildRedstoneScreen(this, itemStack)
                })
        )
        widgets = getGuide(this)
        initialised = true
    }

    override fun doesGuiPauseGame(): Boolean {
        return false
    }
}