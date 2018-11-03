package hu.frontrider.gearcraft2.gears.traits.gearbox

import hu.frontrider.gearcraft.gears.traits.PowerConsumer
import hu.frontrider.gearcraft2.api.BlockStates.FACING
import hu.frontrider.gearcraft2.api.BlockStates.INVERTED
import hu.frontrider.gearcraft2.api.traits.power.IGearPowered
import net.minecraft.block.BlockPillar
import net.minecraft.block.state.BlockState

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Facing
import net.minecraft.world.World

class GearboxPower(val power: Int) : IGearPowered,PowerConsumer() {
    override fun isSideSupported(world: World, pos: BlockPos, blockState: BlockState, side: Facing): Boolean {
        val state = world.getBlockState(pos.x,pos.y,pos.z)

        val inverted = state.get(INVERTED)
        val facing = state.get(FACING)
        return if (inverted) {
            facing == side
        } else {
            facing != side
        }
    }

    override fun getStrength(iBlockAccess: World, blockPos: BlockPos, state: BlockState, side:Facing): Int {
        val strength = getPower(iBlockAccess, blockPos, state,side)
        return if (strength != 0)
            4
        else
            0
    }

    override fun getPower(iBlockAccess: World, blockPos: BlockPos, state: BlockState, side:Facing): Int {

        val inverted = state.get(INVERTED)
        val facing = state.get(FACING)

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