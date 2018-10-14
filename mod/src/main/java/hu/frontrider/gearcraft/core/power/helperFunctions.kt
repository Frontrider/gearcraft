package hu.frontrider.gearcraft.core.power

import hu.frontrider.gearcraft.api.power.IGearPowered
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

internal fun PowerHandler.getTargetPower(world: World, pos: BlockPos, side: EnumFacing, target: Int, ignoreStrength: Boolean = false): Int {
    val targetState = world.getBlockState(pos)
    val targetBlock = targetState.block


    if (targetBlock is IGearPowered) {
        if ((targetBlock as IGearPowered).getPower(world, pos,targetState) > 0 && (targetBlock as IGearPowered).isSideSupported(world, pos,targetState, side) || ignoreStrength) {
            val targetPower = (targetBlock as IGearPowered).getStrength(world, pos,targetState)
            return if (targetPower >= target) {
                target
            } else
                targetPower
        }
    }
    return 0
}

internal fun PowerHandler.getInvertedTargetPower(world: World, blockPos: BlockPos, facing: EnumFacing, target: Int): Int {
    return getInvertedTargetPower(world, blockPos, facing, false, target)
}

internal fun PowerHandler.getInvertedTargetPower(world: World, blockPos: BlockPos, facing: EnumFacing, ignoreStrength: Boolean, target: Int): Int {
    var total = 0
    if (facing != EnumFacing.NORTH) {
        total += getTargetPower(world, blockPos.south(), EnumFacing.NORTH, target, ignoreStrength)
    }
    if (facing != EnumFacing.SOUTH) {
        total += getTargetPower(world, blockPos.north(), EnumFacing.SOUTH, target, ignoreStrength)
    }
    if (facing != EnumFacing.EAST) {
        total += getTargetPower(world, blockPos.west(), EnumFacing.EAST, target, ignoreStrength)
    }
    if (facing != EnumFacing.WEST) {
        total += getTargetPower(world, blockPos.east(), EnumFacing.WEST, target, ignoreStrength)
    }
    if (facing != EnumFacing.UP) {
        total += getTargetPower(world, blockPos.down(), EnumFacing.UP, target, ignoreStrength)
    }
    if (facing != EnumFacing.DOWN) {
        total += getTargetPower(world, blockPos.up(), EnumFacing.DOWN, target, ignoreStrength)
    }
    return total
}
