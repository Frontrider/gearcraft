package hu.frontrider.gearcraft.blocks.power

import hu.frontrider.gearcraft.api.traits.power.IGearPowered
import hu.frontrider.gearcraft.blocks.InvertedDirectionalBlockBase
import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.gears.tooltip.PowerTooltip
import hu.frontrider.gearcraft.gears.traits.gearbox.GearboxPower
import hu.frontrider.gearcraft.gears.tooltip.InvertedToolTip
import hu.frontrider.gearcraft.gears.tooltip.MultiTooltip
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockGearbox(val power: Int,
                   resistance: Float,
                   name: String,
                   tool: String,
                   miningLevel: Int,
                   hardness: Float,
                   soundType: SoundType,
                   material: Material,
                   mapColor: MapColor
) : InvertedDirectionalBlockBase(resistance, name, tool, miningLevel, hardness, soundType, material, mapColor),
        IGearPowered by GearboxPower(power),
        ITooltipped by MultiTooltip(PowerTooltip(power),InvertedToolTip("gearcraft.gearbox.invertmessage")) {


    override fun observedNeighborChange(blockState: IBlockState?, world: World?, blockPos: BlockPos, block: Block?, blockPos1: BlockPos?) {
        world!!.scheduleUpdate(blockPos, this, 30)
    }

    override fun updateTick(world: World?, blockPos: BlockPos?, blockState: IBlockState?, random: Random?) {
        world!!.notifyNeighborsOfStateChange(blockPos!!, this, true)
    }

    //block stuff
    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    override fun isFullCube(blockState: IBlockState?): Boolean {
        return false
    }

    override fun isOpaqueCube(blockState: IBlockState?): Boolean {
        return false
    }
}