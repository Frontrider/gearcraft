package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.api.IPoweredBlock
import net.minecraft.block.Block
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

import hu.frontrider.gearcraft.GearCraft.Companion.MODID

class CreativeGearbox : Block(Material.ROCK, MapColor.BLACK), IPoweredBlock, TooltippedBlock {
    init {
        setRegistryName(MODID, blockName)
        unlocalizedName = "$MODID.$blockName"
    }

    override fun getPower(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        return 4
    }

    override fun getStrength(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        //the closest we can get to infinity
        return Integer.MAX_VALUE
    }

    override fun isValidSide(access: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
        return true
    }

    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add("Infinite power")
        tooltip.add("Creative Only")
    }

    companion object {
        private val blockName = "creative_gearbox"
    }
}
