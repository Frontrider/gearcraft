package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.api.BlockStateHelpers.FACING
import hu.frontrider.gearcraft.api.BlockStateHelpers.INVERTED
import hu.frontrider.gearcraft.api.IPoweredBlock
import hu.frontrider.gearcraft.core.util.EnergyHelper.getInvertedTargetPower
import hu.frontrider.gearcraft.core.util.EnergyHelper.getTargetPower
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class GearboxBlock(
        val power: Int,
        resistance: Float,
        name: String,
        tool: String,
        miningLevel: Int,
        hardness: Float,
        soundType: SoundType,
        material: Material,
        mapColor: MapColor
        ) : InvertedDirectionalBlockBase(resistance, name, tool, miningLevel, hardness, soundType, material, mapColor), IPoweredBlock, TooltippedBlock {

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
            val required = getTargetPower(iBlockAccess, blockPos.offset(facing), facing, power, javaClass)
            val total = getInvertedTargetPower(iBlockAccess, blockPos, facing, power, javaClass)

            return if (total >= required) total else 0
        } else {

            val total = getTargetPower(iBlockAccess, blockPos.offset(facing), facing, power, javaClass)
            val required = getInvertedTargetPower(iBlockAccess, blockPos, facing, true, power, javaClass)
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
        tooltip.add("Power level: $power")
    }
}
