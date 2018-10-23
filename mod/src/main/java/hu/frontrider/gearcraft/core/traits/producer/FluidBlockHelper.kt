package hu.frontrider.gearcraft.core.traits.producer

import hu.frontrider.gearcraft.core.IFluidHelper
import net.minecraft.block.Block
import net.minecraft.block.BlockLiquid
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.IBlockState

class FluidBlockHelper(private val block: Block, val condition: (Int) -> Boolean, private val property: PropertyInteger=BlockLiquid.LEVEL) : IFluidHelper {
    override fun getWaterValue(blockState: IBlockState): Int {
        if (blockState.block !== block) {
            return 0
        }

        val level = blockState.getValue(property)

        return if (condition(level))
            4
        else 0
    }
}