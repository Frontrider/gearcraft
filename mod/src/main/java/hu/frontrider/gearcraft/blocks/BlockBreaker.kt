package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.core.util.EnergyHelper
import net.minecraft.block.Block
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.Mirror
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import hu.frontrider.gearcraft.api.BlockStateHelpers.FACING
import hu.frontrider.gearcraft.core.util.BlockHelper.getFacing
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import java.util.*

open class BlockBreaker(val power: Int,
                        resistance: Float,
                        name: String,
                        tool: String,
                        hardness: Float,
                        soundType: SoundType,
                        material: Material,
                        mapColor: MapColor,
                        val miningLevel:Int) : Block(material,mapColor), TooltippedBlock {

    init{
        setRegistryName(GearCraft.MODID,name)
        setSoundType(soundType)
        setResistance(resistance)
        setHardness(hardness)
        setHarvestLevel(tool,miningLevel)
        tickRandomly = true
        unlocalizedName = "${GearCraft.MODID}.$name"
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING)
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    override fun isFullCube(blockState: IBlockState?): Boolean {
        return false
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState
                .withProperty(FACING, Objects.requireNonNull(getFacing(meta)))
    }

    override fun isOpaqueCube(blockState: IBlockState?): Boolean {
        return false
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).index
    }

    override fun withRotation(p_withRotation_1_: IBlockState, p_withRotation_2_: Rotation?): IBlockState {
        return p_withRotation_1_.withProperty(FACING, p_withRotation_2_!!.rotate(p_withRotation_1_.getValue(FACING)))
    }

    override fun withMirror(p_withMirror_1_: IBlockState, p_withMirror_2_: Mirror?): IBlockState {
        return p_withMirror_1_.withRotation(p_withMirror_2_!!.toRotation(p_withMirror_1_.getValue(FACING)))
    }

    override fun getStateForPlacement(world: World?, pos: BlockPos?, facing: EnumFacing?, p_getStateForPlacement_4_: Float, p_getStateForPlacement_5_: Float, p_getStateForPlacement_6_: Float, p_getStateForPlacement_7_: Int, entityLivingBase: EntityLivingBase?): IBlockState {
        return this.defaultState
                .withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos!!, entityLivingBase!!))
    }

    override fun observedNeighborChange(blockState: IBlockState?, world: World, blockPos: BlockPos, block: Block?, blockPos1: BlockPos) {
       world.scheduleBlockUpdate(blockPos,this,10,0)
    }

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        val value = state.getValue(FACING)
        val targetPower = EnergyHelper.getInvertedTargetPower(world, pos, value.opposite, power,javaClass)
        if (targetPower >= power) {
            val offset = pos.offset(value.opposite)
            val targetState = world.getBlockState(offset)
            val harvestLevel = targetState.block.getHarvestLevel(targetState)

            if (harvestLevel <= miningLevel)
                world.destroyBlock(offset, true)
        }
    }

    override fun setTooltip(tooltip: List<String>) {

    }
}
