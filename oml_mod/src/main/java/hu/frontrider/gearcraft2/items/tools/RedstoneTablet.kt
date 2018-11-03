package hu.frontrider.gearcraft.items.tools

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.tablet.gui.TabletGui
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import net.minecraft.client.Minecraft
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.registry.GameRegistry


class RedstoneTablet : Item() {

    init {
        maxStackSize =1
    }
    companion object {

        @GameRegistry.ObjectHolder("${GearCraft.MODID}:temporary_redstone")
        lateinit var tempRedstone: Block
    }

    override fun onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult<ItemStack> {
        if (worldIn.isRemote) {
            TabletGui.itemStack = playerIn.getHeldItem(handIn)
            Minecraft.getMinecraft().displayGuiScreen(TabletGui)
        }
        return ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn))
    }

    override fun onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {

        val offset = pos.offset(facing)
        GearCraft.proxy.handleRedstoneProxy(offset,player.dimension)

        if (worldIn.isAirBlock(offset)) {

            worldIn.setBlockState(offset, tempRedstone.defaultState)
            tempRedstone.onBlockPlacedBy(worldIn, offset,worldIn.getBlockState(offset),player, ItemStack.EMPTY)
        }

        return EnumActionResult.SUCCESS
    }
}