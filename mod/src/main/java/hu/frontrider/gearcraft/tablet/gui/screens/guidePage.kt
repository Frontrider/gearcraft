package hu.frontrider.gearcraft.tablet.gui.screens

import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import hu.frontrider.gearcraft.core.gui.BaseGui
import hu.frontrider.gearcraft.tablet.gui.GuidePageButton
import hu.frontrider.gearcraft.core.gui.widgets.ImageWidget
import hu.frontrider.gearcraft.core.gui.widgets.ListWidget
import hu.frontrider.gearcraft.core.gui.widgets.TextButton

import hu.frontrider.gearcraft.core.gui.widgets.interfaces.Widget
import hu.frontrider.gearcraft.core.gui.widgets.traits.ClickAction
import hu.frontrider.gearcraft.tablet.TabletReferences
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

fun getGuide(baseGui: BaseGui): Array<Widget> {
    val i = (baseGui.getWidth() - BaseGui.xSize) / 2
    val j = (baseGui.getHeight() - BaseGui.ySize) / 2
    val listWidgetOffsetX = 60

    return arrayOf(
            ImageWidget(i + listWidgetOffsetX, j + 16, BaseGui.xSize - 68, BaseGui.ySize - 16, ResourceLocation(MODID, "textures/gui/guide/black_panel.png")),
            TextButton(i + 5, j + 16, "gearcraft.guide.group.power", ClickAction {
                baseGui.widgets = getGuide(baseGui) + arrayOf(
                        ListWidget(arrayOf(
                                GuidePageButton(2, 0, ItemStack(TabletReferences.gear), "energy", "energy", baseGui, 43),
                                GuidePageButton(2, 0, ItemStack(TabletReferences.gearbox), "power_transfer", "transfer", baseGui, 43),
                                GuidePageButton(2, 0, ItemStack(TabletReferences.gearbox), "watermills", "watermills", baseGui, 43),
                                GuidePageButton(2, 0, ItemStack(TabletReferences.gearbox), "burner_engine", "burner_engine", baseGui, 43)
                        )
                                , i + listWidgetOffsetX, j + 16, 18, BaseGui.xSize - 68, BaseGui.ySize - 16)
                )
            }, 32),
            TextButton(i + 5, j + 34, "gearcraft.guide.group.materials", ClickAction {
                baseGui.widgets = getGuide(baseGui) + arrayOf(
                        ListWidget(arrayOf(
                                GuidePageButton(2, 0, ItemStack(TabletReferences.glue), "glue", "glue", baseGui, 43)
                        ), i + listWidgetOffsetX, j + 16, 2, BaseGui.xSize - 68, BaseGui.ySize - 16)
                )
            }, 32),
            TextButton(i + 5, j + 52, "gearcraft.guide.group.tools", ClickAction {
                baseGui.widgets = getGuide(baseGui) + arrayOf(
                        ListWidget(arrayOf(
                                GuidePageButton(2, 0, ItemStack(TabletReferences.drill), "drill", "drills", baseGui, 43),
                                GuidePageButton(2, 16, ItemStack(TabletReferences.tablet), "tablet", "tablet", baseGui, 43)

                        ), i + listWidgetOffsetX, j + 16, 2, BaseGui.xSize - 68, BaseGui.ySize - 16)
                )
            }, 32))
}