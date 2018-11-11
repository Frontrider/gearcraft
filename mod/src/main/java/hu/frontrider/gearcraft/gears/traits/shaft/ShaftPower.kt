package hu.frontrider.gearcraft.gears.traits.shaft

import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.traits.power.ITransmission
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ShaftPower(val power:Int): ITransmission {

    override fun getGearPower(world: World?, blockPos: BlockPos, blockState: IBlockState, side:EnumFacing): Int {
        if(!isSideSupported(world,blockPos,blockState,side) && getGearStrength(world, blockPos, blockState, side)>0)
            return 0
        return power
    }

    override fun getGearStrength(world: World?, blockPos: BlockPos?, blockState: IBlockState, side:EnumFacing): Int {
        return blockState.getValue(BlockStates.POWER)
    }

    override fun isSideSupported(world: World?, blockPos: BlockPos?, blockState: IBlockState, side: EnumFacing): Boolean {
        return side.axis == blockState.getValue(BlockStates.AXIS)
    }



}