package hu.frontrider.gearcraft.blocks.util

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.BlockStates.RANGE
import hu.frontrider.gearcraft.api.traits.block.IRangedBlock
import hu.frontrider.gearcraft.blocks.BlockBase
import hu.frontrider.gearcraft.core.traits.RangedBlockFromState
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.item.EntityFallingBlock
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockScaffold(resistance: Float,
                    name: String,
                    tool: String,
                    miningLevel: Int,
                    hardness: Float,
                    soundType: SoundType,
                    material: Material,
                    mapColor: MapColor) : BlockBase(resistance, name, tool, miningLevel, hardness, soundType, material, mapColor)
{

    override fun onBlockAdded(worldIn: World, pos: BlockPos, state: IBlockState) {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn))
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn))
    }

    override fun updateTick(worldIn: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if (!worldIn.isRemote) {
            this.checkFallable(state,worldIn, pos)
        }

    }
    private fun checkFallable(state: IBlockState, world: World, pos: BlockPos) {

    }

    override fun isStickyBlock(state: IBlockState): Boolean {
        return true
    }


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

    override fun canCollideCheck(state: IBlockState, hitIfLiquid: Boolean): Boolean {
        return true
    }

}