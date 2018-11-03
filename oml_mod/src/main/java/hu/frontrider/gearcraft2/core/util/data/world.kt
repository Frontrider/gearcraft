package hu.frontrider.gearcraft2.core.util.data

import net.minecraft.block.state.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld

fun IWorld.getBlockState(blockPos:BlockPos): BlockState {
    return getBlockState(blockPos.x,blockPos.y,blockPos.z)
}