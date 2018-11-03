package hu.frontrider.gearcraft.tablet.gui.screens

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.core.gui.BaseGui
import hu.frontrider.gearcraft.core.gui.widgets.ImageWidget
import hu.frontrider.gearcraft.core.gui.widgets.ListWidget
import hu.frontrider.gearcraft.core.gui.widgets.TextButton
import hu.frontrider.gearcraft.core.gui.widgets.interfaces.Widget
import hu.frontrider.gearcraft.core.gui.widgets.traits.ClickAction
import hu.frontrider.gearcraft.core.util.data.toBlockPos
import hu.frontrider.gearcraft.core.util.translateFormatting
import hu.frontrider.gearcraft.tablet.TabletData
import hu.frontrider.gearcraft.tablet.gui.ProxyAction
import hu.frontrider.gearcraft.tablet.proxy.ClientSideProxyManager
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextComponentString

fun buildRedstoneScreen(baseGui: BaseGui, itemStack: ItemStack): Array<Widget> {
    val i = (baseGui.getWidth() - BaseGui.xSize) / 2
    val j = (baseGui.getHeight() - BaseGui.ySize) / 2

    val tagCompound = itemStack.tagCompound?: NBTTagCompound()
    val name = TabletData.REDSTONE_PROXIES.name
    var proxyArray= arrayOf<Widget>()
    if(tagCompound.hasKey(name)){
        val proxies = tagCompound.getTag(name) as NBTTagList

        var i =0
     proxyArray = proxies.map {
            it as NBTTagCompound
        }.map{
            val toBlockPos = (it.getTag("pos") as NBTTagCompound).toBlockPos()
            val dimension = it.getInteger("dim")
            val id = it.getInteger("id")
            TextButton(0,i++*16,"${toBlockPos.x},${toBlockPos.y},${toBlockPos.z},$dimension",ProxyAction(id),32,16777215)
        }.toTypedArray()
    }

    return arrayOf(
            TextButton(i + 5, j + 16, "gearcraft.tablet.new_proxy", ClickAction {
                ClientSideProxyManager.makingProxy=true
                Minecraft.getMinecraft().displayGuiScreen(null)
                Minecraft.getMinecraft().player.sendStatusMessage(TextComponentString(translateFormatting("gearcraft.tablet.select_pos")), true)

            }, 32),
            ImageWidget(i + 60, j + 16, BaseGui.xSize - 68, BaseGui.ySize - 16, ResourceLocation(GearCraft.MODID, "textures/gui/guide/black_panel.png")),
                    ListWidget(proxyArray, i + 60, j + 16, 2, BaseGui.xSize - 68, BaseGui.ySize - 16)

            )
}