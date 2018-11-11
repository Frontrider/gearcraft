package hu.frontrider.gearcraft.gears.traits.producer

import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.api.traits.power.IGearPowered
import hu.frontrider.gearcraft.core.util.ChatFormat
import hu.frontrider.gearcraft.core.util.formatTranslate
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class PoweredEngine(val power: Int, val outputSide: EnumFacing) : IGearPowered, ITooltipped {
    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add(formatTranslate("gearcraft.tooltip.requires_pulse",ChatFormat.RED))
    }

    override fun getGearPower(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Int {
        if (getGearStrength(world, blockPos, blockState, side) > 0)
            return power
        return 0
    }

    override fun getGearStrength(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Int {
        val stage = blockState!!.getValue(BlockStates.POWERED)
        return if (stage) 4 else 0

    }

    override fun isSideSupported(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Boolean {
        return side == outputSide
    }
}

class StagedPoweredEngine(power: Int, outputSide: EnumFacing) : PoweredEngine(power, outputSide) {
    override fun getGearStrength(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Int {
        val stage = blockState!!.getValue(BlockStates.POWERSTATE)
        return if (stage==1) 4 else 0
    }
}