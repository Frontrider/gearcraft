package hu.frontrider.gearcraft2.core

import net.minecraft.block.state.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Facing
import net.minecraft.world.IWorld

interface IBlockUpdater {

   fun updateBlockState(world: IWorld, left: BlockPos, right: BlockPos, thizState: BlockState, thizPos: BlockPos, leftSide: Facing, rightSide: Facing)
}