package hu.frontrider.gearcraft

import hu.frontrider.gearcraft.craftinggear.gui.ContainerCraftingGear
import hu.frontrider.gearcraft.craftinggear.gui.CraftingGearGui
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler


class GuiHandler : IGuiHandler {
    override fun getClientGuiElement(ID: Int, player: EntityPlayer, worldIn: World, x: Int, y: Int, z: Int): Any? {
        return when (ID) {
            craftingGear ->
                player.apply {
                    return CraftingGearGui(getServerGuiElement(ID,player, world, x, y, z) as ContainerCraftingGear,world,position)
                }
            else -> null
        }
    }

    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        return when (ID) {
            craftingGear -> ContainerCraftingGear(player.inventory,player.world,player.position)
            else -> null
        }
    }

    companion object {
        const val craftingGear = 0;
    }
}