package hu.frontrider.gearcraft.core.util

import hu.frontrider.gearcraft.api.IPoweredBlock
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

object EnergyHelper {

    @JvmOverloads
    fun getTargetPower(iBlockAccess: IBlockAccess, pos: BlockPos, side: EnumFacing, target: Int, toIgnore: Class<*>?, ignoreStrength: Boolean = false): Int {
        val targetState = iBlockAccess.getBlockState(pos)
        val targetBlock = targetState.block

        if (toIgnore != null) {
            if (toIgnore.isInstance(targetBlock)) {
                return 0
            }
        }

        if (targetBlock is IPoweredBlock) {
            if ((targetBlock as IPoweredBlock).getPower(iBlockAccess, pos) > 0 && (targetBlock as IPoweredBlock).isValidSide(iBlockAccess, pos, side) || ignoreStrength) {
                val targetPower = (targetBlock as IPoweredBlock).getStrength(iBlockAccess, pos)
                return if (targetPower >= target) {
                    target
                } else
                    targetPower
            }
        }
        return 0
    }

    fun getInvertedTargetPower(iBlockAccess: IBlockAccess, blockPos: BlockPos, facing: EnumFacing, target: Int, toIgnore: Class<*>): Int {
        return getInvertedTargetPower(iBlockAccess, blockPos, facing, false, target, toIgnore)
    }

    fun getInvertedTargetPower(iBlockAccess: IBlockAccess, blockPos: BlockPos, facing: EnumFacing, ignoreStrength: Boolean, target: Int, toIgnore: Class<*>): Int {
        var total = 0
        if (facing != EnumFacing.NORTH) {
            total += getTargetPower(iBlockAccess, blockPos.south(), EnumFacing.NORTH, target, toIgnore, ignoreStrength)
        }
        if (facing != EnumFacing.SOUTH) {
            total += getTargetPower(iBlockAccess, blockPos.north(), EnumFacing.SOUTH, target, toIgnore, ignoreStrength)
        }
        if (facing != EnumFacing.EAST) {
            total += getTargetPower(iBlockAccess, blockPos.west(), EnumFacing.EAST, target, toIgnore, ignoreStrength)
        }
        if (facing != EnumFacing.WEST) {
            total += getTargetPower(iBlockAccess, blockPos.east(), EnumFacing.WEST, target, toIgnore, ignoreStrength)
        }
        if (facing != EnumFacing.UP) {
            total += getTargetPower(iBlockAccess, blockPos.down(), EnumFacing.UP, target, toIgnore, ignoreStrength)
        }
        if (facing != EnumFacing.DOWN) {
            total += getTargetPower(iBlockAccess, blockPos.up(), EnumFacing.DOWN, target, toIgnore, ignoreStrength)
        }
        return total
    }

}
