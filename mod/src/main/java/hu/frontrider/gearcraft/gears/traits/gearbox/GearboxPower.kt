package hu.frontrider.gearcraft.gears.traits.gearbox

import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.traits.power.IGearPowered
import hu.frontrider.gearcraft.gears.traits.PowerConsumer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class GearboxPower(val power: Int) : IGearPowered,PowerConsumer() {
    override fun isSideSupported(world: World, pos: BlockPos, blockState: IBlockState, side: EnumFacing): Boolean {
        val state = world.getBlockState(pos)

        val inverted = state.getValue(BlockStates.INVERTED)
        val facing = state.getValue(BlockStates.FACING)
        return if (inverted) {
            facing == side
        } else {
            facing != side
        }
    }

    override fun getStrength(iBlockAccess: World, blockPos: BlockPos, state: IBlockState, side:EnumFacing): Int {
        val strength = getPower(iBlockAccess, blockPos, state,side)
        return if (strength != 0)
            4
        else
            0
    }

    override fun getPower(iBlockAccess: World, blockPos: BlockPos, state: IBlockState, side:EnumFacing): Int {

        val inverted = state.getValue(BlockStates.INVERTED)
        val facing = state.getValue(BlockStates.FACING)

        return if (inverted) {
            if(facing != side)
                return 0
            val required = getTargetPower(iBlockAccess, blockPos.offset(facing), facing)
            val total = getInvertedTargetPower(iBlockAccess, blockPos, facing)

            if (total >= required) total else 0
        } else {
            if(facing == side)
                return 0
            val total = getTargetPower(iBlockAccess, blockPos.offset(facing), facing)
            val required = getInvertedTargetPower(iBlockAccess, blockPos, facing)
            if (total >= required)
                total
            else
                0
        }
    }


}