package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.registry.TierRegistry
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.Mirror
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

import java.util.Objects

import hu.frontrider.gearcraft.api.BlockStateHelpers.FACING
import hu.frontrider.gearcraft.api.BlockStateHelpers.INVERTED
import hu.frontrider.gearcraft.api.BlockStateHelpers.POWERED
import hu.frontrider.gearcraft.util.BlockHelper.getFacing

abstract class TieredInvertedDirectionalBlockBase : BlockBase {
    constructor(tier: TierRegistry.Tier, blockName: String, tag: String?) : super(tier, blockName, tag) {}

    constructor(tier: TierRegistry.Tier, blockName: String) : super(tier, blockName) {}

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
