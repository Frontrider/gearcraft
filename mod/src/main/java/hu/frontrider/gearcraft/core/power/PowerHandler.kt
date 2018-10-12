package hu.frontrider.gearcraft.core.power

import hu.frontrider.gearcraft.api.BlockStates.*
import hu.frontrider.gearcraft.api.IGearPowered
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class PowerHandler(val block: IGearPowered, val power: Int) : IGearPowered {

    override fun getPower(world: World?, blockPos: BlockPos?, blockState: IBlockState?): Int {
        return power
    }

    override fun getStrength(world: World, blockPos: BlockPos, blockState: IBlockState): Int {
        val properties = blockState.propertyKeys

        //check all the pre defined blockstate values to determine what we need to return.
        return when {
            properties.contains(SPIN) -> if (blockState.getValue(SPIN) > 0) 16 else 0
            properties.contains(POWER_BIG) -> blockState.getValue(POWER_BIG)
            properties.contains(POWER) -> blockState.getValue(POWER)
            properties.contains(POWERED) -> if (blockState.getValue(POWERED)) 16 else 0
            else -> 0
        }
    }

    override fun isSideSupported(world: World, blockPos: BlockPos, blockState: IBlockState, side: EnumFacing): Boolean {
        return if (sides.isEmpty()) {
            val properties = blockState.properties
            if (properties.containsKey(AXIS)) {
                val axis = blockState.getValue(AXIS)
                return side.axis == axis
            }
            true
        } else
            sides.contains(side)
    }

    val sides = HashSet<EnumFacing>()

    fun addSide(side: EnumFacing) {
        sides.add(side)
    }


}