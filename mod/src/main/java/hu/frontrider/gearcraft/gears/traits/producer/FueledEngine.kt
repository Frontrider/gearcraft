package hu.frontrider.gearcraft.gears.traits.producer

import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.api.traits.power.IGearPowered
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class FueledEngine(val power:Int,val outputSide:EnumFacing,val fueles:Array<Item>):IGearPowered,ITooltipped {
    override fun setTooltip(tooltip: MutableList<String>?) {

    }

    override fun getPower(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Int {
        if (getStrength(world, blockPos, blockState, side) > 0)
            return power
        return 0
    }

    override fun getStrength(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Int {
        val stage = blockState!!.getValue(BlockStates.POWERED)
        return if (stage) 4 else 0

    }

    override fun isSideSupported(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Boolean {
        return side ==outputSide
    }
}