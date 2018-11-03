package hu.frontrider.gearcraft2.gears.traits.shaft

import hu.frontrider.gearcraft2.api.BlockStates
import hu.frontrider.gearcraft2.api.traits.power.ITransmission
import net.minecraft.block.state.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Facing
import net.minecraft.world.World

class ShaftPower(val power:Int): ITransmission {

    override fun getPower(world: World?, blockPos: BlockPos, blockState: BlockState, side: Facing): Int {
        if(!isSideSupported(world,blockPos,blockState,side) && getStrength(world, blockPos, blockState, side)>0)
            return 0
        return power
    }

    override fun getStrength(world: World?, blockPos: BlockPos?, blockState: BlockState, side:Facing): Int {
        return blockState.get(BlockStates.POWER)
    }

    override fun isSideSupported(world: World?, blockPos: BlockPos?, blockState: BlockState, side: Facing): Boolean {
        return side.axis == blockState.get(BlockStates.AXIS)
    }



}