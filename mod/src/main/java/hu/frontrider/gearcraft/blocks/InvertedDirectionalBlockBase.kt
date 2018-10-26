package hu.frontrider.gearcraft.blocks

import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.Mirror
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

import java.util.Objects

import hu.frontrider.gearcraft.api.BlockStates.FACING
import hu.frontrider.gearcraft.api.BlockStates.INVERTED
import hu.frontrider.gearcraft.core.util.BlockHelper.getFacing
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material

abstract class InvertedDirectionalBlockBase(
        resistance: Float,
        name: String,
        tool: String,
        miningLevel: Int,
        hardness: Float,
        soundType: SoundType,
        material: Material,
        mapColor: MapColor
) : BlockBase(resistance,  tool, miningLevel, hardness, soundType, material, mapColor) {

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING, INVERTED)
    }


    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState
                .withProperty(FACING, Objects.requireNonNull(getFacing(meta)))
                .withProperty(INVERTED, meta and 8 > 0)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        var i = 0
        i = i or state.getValue(FACING).getIndex()
        if (state.getValue(INVERTED)) {
            i = i or 8
        }
        return i
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
                .withProperty(INVERTED, entityLivingBase.isSneaking)
    }

}
