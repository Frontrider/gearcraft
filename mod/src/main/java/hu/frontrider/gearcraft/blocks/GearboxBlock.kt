package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.api.IPoweredBlock
import hu.frontrider.gearcraft.registry.TierRegistry
import net.minecraft.block.Block
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.Mirror
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.Objects
import java.util.Random

import hu.frontrider.gearcraft.api.BlockStateHelpers.FACING
import hu.frontrider.gearcraft.api.BlockStateHelpers.INVERTED
import hu.frontrider.gearcraft.util.BlockHelper.getFacing
import hu.frontrider.gearcraft.util.EnergyHelper.getInvertedTargetPower
import hu.frontrider.gearcraft.util.EnergyHelper.getTargetPower

class GearboxBlock @JvmOverloads constructor(tier: TierRegistry.Tier, tag: String? = null) : TieredInvertedDirectionalBlockBase(tier, "gearbox", tag), IPoweredBlock, TooltippedBlock {

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    override fun isFullCube(blockState: IBlockState?): Boolean {
        return false
    }


    override fun isOpaqueCube(blockState: IBlockState?): Boolean {
        return false
    }


    override fun getStrength(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {

        val state = iBlockAccess.getBlockState(blockPos)
        val inverted = state.getValue(INVERTED)
        val facing = state.getValue(FACING)

        if (inverted) {
            val required = getTargetPower(iBlockAccess, blockPos.offset(facing), facing, tier.power, javaClass)
            val total = getInvertedTargetPower(iBlockAccess, blockPos, facing, tier.power, javaClass)

            return if (total >= required) total else 0
        } else {

            val total = getTargetPower(iBlockAccess, blockPos.offset(facing), facing, tier.power, javaClass)
            val required = getInvertedTargetPower(iBlockAccess, blockPos, facing, true, tier.power, javaClass)
            return if (total >= required) total else 0
        }
    }

    override fun getPower(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        return if (getStrength(iBlockAccess, blockPos) > 0) 4 else 0
    }

    override fun isValidSide(access: IBlockAccess, pos: BlockPos, targetFacing: EnumFacing): Boolean {
        val state = access.getBlockState(pos)
        val inverted = state.getValue(INVERTED)
        val facing = state.getValue(FACING)
        return if (inverted) {
            facing == targetFacing
        } else {
            facing != targetFacing
        }
    }

    override fun observedNeighborChange(blockState: IBlockState?, world: World?, blockPos: BlockPos?, block: Block?, blockPos1: BlockPos?) {
        world!!.scheduleUpdate(blockPos, this, 30)
    }

    override fun updateTick(world: World?, blockPos: BlockPos?, blockState: IBlockState?, random: Random?) {
        world!!.notifyNeighborsOfStateChange(blockPos!!, this, true)
    }

    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add("Power level: " + tier.power)
    }
}
