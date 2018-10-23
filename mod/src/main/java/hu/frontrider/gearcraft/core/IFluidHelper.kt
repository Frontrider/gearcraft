package hu.frontrider.gearcraft.core

import net.minecraft.block.state.IBlockState

interface IFluidHelper {
    fun getWaterValue(blockState: IBlockState): Int
}