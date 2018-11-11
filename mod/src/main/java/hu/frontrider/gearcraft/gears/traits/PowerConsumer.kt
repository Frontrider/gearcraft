package hu.frontrider.gearcraft.gears.traits

import hu.frontrider.gearcraft.api.traits.power.ITransmission
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class PowerConsumer {

    fun getTargetPower(world: World, pos: BlockPos, side: EnumFacing): Int {
        val targetState = world.getBlockState(pos)
        val targetBlock = targetState.block as? ITransmission ?: return 0

        if (targetBlock.getGearStrength(world, pos, world.getBlockState(pos), side) > 0 && targetBlock.isSideSupported(world, pos, world.getBlockState(pos), side)) {
            return targetBlock.getGearPower(world, pos, world.getBlockState(pos), side)
        }
        return 0
    }

    fun getInvertedTargetPower(world: World, blockPos: BlockPos, facing: EnumFacing): Int {
        var total = 0

        for (it in EnumFacing.values()) {
            if(it != facing)
                total+=getTargetPower(world, blockPos.offset(it),it.opposite)
        }
        return total
    }

    fun getPowerForSides(world: World, blockPos: BlockPos, facing: Array<EnumFacing>): Int {
        var total = 0

        for (it in EnumFacing.values()) {
            if(facing.contains(it))
                total+=getTargetPower(world, blockPos.offset(it),it.opposite)
        }
        return total
    }
}