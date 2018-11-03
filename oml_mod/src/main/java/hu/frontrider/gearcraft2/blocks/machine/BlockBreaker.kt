package hu.frontrider.gearcraft2.blocks.machine

import net.minecraft.block.Block
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos

import hu.frontrider.gearcraft.gears.traits.PowerConsumer
import hu.frontrider.gearcraft2.gears.tooltip.MultiTooltip
import hu.frontrider.gearcraft.gears.tooltip.PowerTooltip
import hu.frontrider.gearcraft.core.util.BlockHelper.getFacing
import hu.frontrider.gearcraft2.api.BlockStates
import hu.frontrider.gearcraft2.api.traits.ITooltipped

import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockState
import net.minecraft.client.render.block.BlockRenderLayer
import net.minecraft.util.math.Facing
import java.util.*

open class BlockBreaker(builder: Block.Builder, val power: Int) : Block(builder), ITooltipped by MultiTooltip(PowerTooltip(power)) {

    protected val powerConsumer = PowerConsumer()

    init {
        this.defaultState = this.defaultState.with(BlockStates.FACING, Facing.UP) as BlockState
    }



    override fun getRenderLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    override fun isFullBlock(aBlockState: BlockState): Boolean {
        return false
    }

    override fun getStateForPlacement(world: World?, pos: BlockPos?, facing: EnumFacing?, p_getStateForPlacement_4_: Float, p_getStateForPlacement_5_: Float, p_getStateForPlacement_6_: Float, p_getStateForPlacement_7_: Int, entityLivingBase: EntityLivingBase?): IBlockState {
        return this.defaultState
                .withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos!!, entityLivingBase!!))
    }

    override fun observedNeighborChange(blockState: IBlockState, world: World, blockPos: BlockPos, block: Block?, blockPos1: BlockPos) {
        val power1 = powerConsumer.getInvertedTargetPower(world, blockPos, blockState.getValue(FACING).opposite)
        if (power1 >= power)
            world.scheduleBlockUpdate(blockPos, this, 10, 0)
    }

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        val value = state.getValue(FACING)
        val targetPower = powerConsumer.getInvertedTargetPower(world, pos, value.opposite)
        if (targetPower >= power) {
            val offset = pos.offset(value.opposite)
            val targetState = world.getBlockState(offset)
            val harvestLevel = targetState.block.getHarvestLevel(targetState)

            if (harvestLevel <= miningLevel)
                world.destroyBlock(offset, true)
        }
    }
}
