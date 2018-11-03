package hu.frontrider.gearcraft2.blocks.power

import hu.frontrider.gearcraft.core.util.ChatFormat
import hu.frontrider.gearcraft.core.util.formatTranslate
import hu.frontrider.gearcraft2.api.traits.ITooltipped
import hu.frontrider.gearcraft2.api.traits.power.IGearPowered
import net.minecraft.block.Block
import net.minecraft.block.state.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Facing
import net.minecraft.world.IWorld
import net.minecraft.world.World

class CreativeGearbox(builder:Builder) : Block(builder), IGearPowered, ITooltipped {

    override fun getPower(world: IWorld?, blockPos: BlockPos?, blockState: BlockState?, side: Facing?): Int {
        //the closest we can get to infinity
        return Integer.MAX_VALUE
    }

    override fun getStrength(world: IWorld?, blockPos: BlockPos?, blockState: BlockState?, side: Facing?): Int {
        //the closest we can get to infinity
        return Integer.MAX_VALUE
    }

    override fun isSideSupported(world: IWorld?, blockPos: BlockPos?, blockState: BlockState?, side: Facing?): Boolean {
        return true
    }

    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add(formatTranslate("gearcraft.infinite_power", ChatFormat.DARK_PURPLE))
        tooltip.add(formatTranslate("gearcraft.creative_only", ChatFormat.DARK_PURPLE))
    }

    companion object {
        private val blockName = "creative_gearbox"
    }
}
