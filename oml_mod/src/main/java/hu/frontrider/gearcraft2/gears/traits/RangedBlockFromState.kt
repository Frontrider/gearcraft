package hu.frontrider.gearcraft.gears.traits

import hu.frontrider.gearcraft.api.BlockStates.RANGE
import hu.frontrider.gearcraft.api.traits.block.IRangedBlock
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class RangedBlockFromState: IRangedBlock {
    override fun getRangeValue(world: World?, blockPos: BlockPos?, blockState: IBlockState): Int {
        return blockState.getValue(RANGE)
    }

    override fun getRangedState(world: World, blockPos: BlockPos, blockState: IBlockState): IBlockState {
        var maxrange = 0
        val values = intArrayOf(
                getRangeValue(world, blockPos.down(), blockState),
                getRangeValue(world, blockPos.north(), blockState),
                getRangeValue(world, blockPos.east(), blockState),
                getRangeValue(world, blockPos.west(), blockState),
                getRangeValue(world, blockPos.south(), blockState)
        )

        maxrange = values.reduce { acc, i ->
            if(acc <i)
                i
            else
                acc
        }

        return blockState.withProperty(RANGE,maxrange)
    }


    override fun isInRange(world: World, blockPos: BlockPos, blockState: IBlockState): Boolean {
        return getRangeValue(world, blockPos, getRangedState(world, blockPos, blockState))<15
    }

}