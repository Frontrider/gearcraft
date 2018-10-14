package hu.frontrider.gearcraft.blocks.power

import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import hu.frontrider.gearcraft.api.power.IGearPowered
import hu.frontrider.gearcraft.blocks.TooltippedBlock
import net.minecraft.block.Block
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class CreativeGearbox : Block(Material.ROCK, MapColor.BLACK), IGearPowered, TooltippedBlock {
    override fun getPower(world: World?, blockPos: BlockPos?, blockState: IBlockState?): Int {
        //the closest we can get to infinity
        return Integer.MAX_VALUE
    }

    override fun getStrength(world: World?, blockPos: BlockPos?, blockState: IBlockState?): Int {
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
        tooltip.add("Infinite power")
        tooltip.add("Creative Only")
    }

    companion object {
        private val blockName = "creative_gearbox"
    }
}
