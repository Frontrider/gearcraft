package hu.frontrider.gearcraft.gears.traits.shaft

import hu.frontrider.gearcraft.api.BlockStates
import net.minecraft.block.Block
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing

class ShaftState {
    fun getDefaultState(block:Block): BlockStateContainer {
        return BlockStateContainer(block, BlockStates.AXIS, BlockStates.POWER)
    }

    fun getStateFromMeta(meta: Int,defaultState: IBlockState): IBlockState {
        var meta = meta
        if (meta > 15) {
            meta = 15
        }
        var axis: EnumFacing.Axis = EnumFacing.Axis.X
        val rotationBits = meta and 0x3
        val powerBits = meta shr 2 and 0x3

        when (rotationBits) {
            1 -> axis = EnumFacing.Axis.Y
            2 -> axis = EnumFacing.Axis.Z
        }
        return defaultState.withProperty(BlockStates.AXIS, axis).withProperty(BlockStates.POWER, powerBits)
    }

    fun getMetaFromState(blockState: IBlockState): Int {
        var i = blockState.getValue(BlockStates.AXIS).ordinal
        val power = blockState.getValue(BlockStates.POWER).toInt()
        i += power shl 2
        return i
    }
}