package hu.frontrider.gearcraft.craftinggear.gui

import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.gui.inventory.GuiCrafting
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class CraftingGearGui(gearContainer:ContainerCraftingGear,world: World,pos:BlockPos) : GuiContainer(gearContainer) {
    companion object {
        private val CRAFTING_TABLE_GUI_TEXTURES = ResourceLocation(MODID,"textures/gui/container/crafting_gear.png")

    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        this.mc.textureManager.bindTexture(CRAFTING_TABLE_GUI_TEXTURES)
        val i = this.guiLeft
        val j = (this.height - this.ySize) / 2
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize)
    }
}