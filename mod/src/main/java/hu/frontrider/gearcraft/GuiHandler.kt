package hu.frontrider.gearcraft

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler


class GuiHandler : IGuiHandler {
    override fun getClientGuiElement(ID: Int, player: EntityPlayer, worldIn: World, x: Int, y: Int, z: Int): Any? {
        return when (ID) {

            else -> null
        }
    }

    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        return when (ID) {
            else -> null
        }
    }

    companion object {
    }
}