package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.api.power.IGearPowered
import hu.frontrider.gearcraft.core.power.PowerHandler
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

class BlockGearboxNormal(
        val power: Int,
        resistance: Float,
        name: String,
        tool: String,
        miningLevel: Int,
        hardness: Float,
        soundType: SoundType,
        material: Material,
        mapColor: MapColor,
        val powerHandler: IGearPowered = PowerHandler(power, true)
) : InvertedDirectionalBlockBase(resistance, name, tool, miningLevel, hardness, soundType, material, mapColor), IGearPowered by powerHandler {

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

    override fun observedNeighborChange(blockState: IBlockState?, world: World, blockPos: BlockPos, block: Block?, blockPos1: BlockPos?) {
        world.scheduleUpdate(blockPos, this, 30)
    }

    override fun updateTick(world: World, blockPos: BlockPos, blockState: IBlockState?, random: Random?) {
        world.notifyNeighborsOfStateChange(blockPos, this, true)
    }
}