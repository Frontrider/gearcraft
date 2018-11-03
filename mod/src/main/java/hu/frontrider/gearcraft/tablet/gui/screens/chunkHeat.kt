package hu.frontrider.gearcraft.tablet.gui.screens

import hu.frontrider.gearcraft.core.gui.BaseGui
import hu.frontrider.gearcraft.core.gui.widgets.TextArea
import hu.frontrider.gearcraft.core.gui.widgets.interfaces.Widget
import net.minecraft.client.Minecraft

fun getchunkHeatScreen(baseGui: BaseGui): Array<Widget>{
    val i = (baseGui.getWidth() - BaseGui.xSize) / 2
    val j = (baseGui.getHeight() - BaseGui.ySize) / 2
    val player = Minecraft.getMinecraft().player
    val world = Minecraft.getMinecraft().world
    var chunkHeatText ="Go lower to measure temteratures"
    if(player.posY <=7)
    {
        chunkHeatText = "chunk heat: "
    }
    return arrayOf(TextArea(i + 8, j + 8, BaseGui.ySize - 16, chunkHeatText))
}