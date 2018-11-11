package hu.frontrider.gearcraft.blocks.power

import hu.frontrider.gearcraft.core.util.ChatFormat
import hu.frontrider.gearcraft.core.util.formatTranslate
import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import hu.frontrider.gearcraft.api.traits.power.IGearPowered
import hu.frontrider.gearcraft.api.traits.ITooltipped
import net.minecraft.block.Block
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class CreativeGearbox : Block(Material.ROCK, MapColor.BLACK), IGearPowered, ITooltipped {
    override fun getGearPower(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Int {
        //the closest we can get to infinity
        return Integer.MAX_VALUE
    }

    override fun getGearStrength(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Int {
        //the closest we can get to infinity
        return Integer.MAX_VALUE
    }

    override fun isSideSupported(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Boolean {
        return true
    }

    init {
        setRegistryName(MODID, blockName)
        unlocalizedName = "$MODID.$blockName"
    }

    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add(formatTranslate("gearcraft.infinite_power", ChatFormat.DARK_PURPLE))
        tooltip.add(formatTranslate("gearcraft.creative_only", ChatFormat.DARK_PURPLE))
    }

    companion object {
        private val blockName = "creative_gearbox"
    }
}
