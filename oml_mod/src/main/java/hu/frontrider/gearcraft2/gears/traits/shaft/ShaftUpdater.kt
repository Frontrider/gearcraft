package hu.frontrider.gearcraft.gears.traits.shaft

import hu.frontrider.gearcraft2.core.IBlockUpdater
import hu.frontrider.gearcraft.core.util.cap
import hu.frontrider.gearcraft2.api.BlockStates
import hu.frontrider.gearcraft2.api.traits.power.IGearPowered
import hu.frontrider.gearcraft2.core.util.data.getBlockState
import net.minecraft.block.state.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Facing
import net.minecraft.world.IWorld

class ShaftUpdater(val power:Int): IBlockUpdater {
    override fun updateBlockState(world: IWorld, left: BlockPos, right: BlockPos, thizState: BlockState, thizPos: BlockPos, leftSide: Facing, rightSide: Facing) {
        val leftBlock = world.getBlockState(left)
        val rightBlock = world.getBlockState(right)

        val poweredLeft = leftBlock.block
        val poweredRight = rightBlock.block

        var rightPower = -1
        var leftPower = -1
        var leftStrength = 0
        var rightStrength = 0

        if (poweredLeft is IGearPowered) {
            leftPower = (poweredLeft as IGearPowered).getPower(world, left, world.getBlockState(left), leftSide).cap(power)

            leftStrength = (poweredLeft as IGearPowered).getStrength(world, left, world.getBlockState(left), leftSide).cap(4)
        }
        if (poweredRight is IGearPowered) {
            rightPower = (poweredRight as IGearPowered).getPower(world, right, world.getBlockState(right), rightSide).cap(power)
            rightStrength = (poweredRight as IGearPowered).getStrength(world, right, world.getBlockState(right), rightSide).cap(4)
        }
        val thizPower = thizState.get(BlockStates.POWER)

        if (leftPower <= 0 && rightPower <= 0) {
            world.setBlockState(thizPos, thizState.with(BlockStates.POWER, 0),0)
            return
        }

        var newPower = 0;
        if (leftPower >= rightPower && leftPower >= power && leftStrength > 0) {
            if (thizPower == leftStrength - 1)
                return
            newPower = leftStrength - 1
        }
        if (leftPower <= rightPower && rightPower >= power && rightStrength > 0) {
            if (thizPower == rightStrength - 1)
                return
            newPower = rightStrength - 1

        }

        if (newPower != power) {
            world.setBlockState(thizPos, thizState.with(BlockStates.POWER, newPower),0)

            world.blockTickScheduler.scheduleTick(left, poweredLeft, 10)
            world.blockTickScheduler.scheduleTick(right, poweredRight, 10)
        }
    }

    fun update(blockState: BlockState, world: IWorld, blockPos: BlockPos?) {
        val axis = blockState.get(BlockStates.AXIS)

        when (axis) {
            Facing.Axis.Y -> {
                updateBlockState(world, blockPos!!.up(), blockPos.down(), blockState, blockPos, Facing.DOWN, Facing.UP)
            }
            Facing.Axis.Z -> {
                updateBlockState(world, blockPos!!.north(), blockPos.south(), blockState, blockPos, Facing.SOUTH, Facing.NORTH)
            }
            Facing.Axis.X -> {
                updateBlockState(world, blockPos!!.east(), blockPos.west(), blockState, blockPos, Facing.WEST, Facing.EAST)
            }
        }
    }
}