package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.api.IPoweredBlock
import hu.frontrider.gearcraft.registry.TierRegistry
import net.minecraft.block.BlockLiquid
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

import java.util.Random

import hu.frontrider.gearcraft.api.BlockStateHelpers.SPIN

class WaterMill @JvmOverloads constructor(tier: TierRegistry.Tier, tag: String? = null) : BlockBase(tier, "watermill", tag), IPoweredBlock {


    init {
        tickRandomly = true
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, SPIN)
    }

    override fun getPower(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        val stage = iBlockAccess.getBlockState(blockPos).getValue(SPIN)
        return if (stage >= 3) 4 else 0
    }

    override fun getStrength(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        return tier.power / 2

    }

    override fun isValidSide(access: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
        return facing == EnumFacing.UP || facing == EnumFacing.DOWN
    }

    override fun randomTick(world: World, blockPos: BlockPos, blockState: IBlockState, random: Random) {
        val waterValue = getWaterValue(world.getBlockState(blockPos.down())) +
                getWaterValue(world.getBlockState(blockPos.east())) +
                getWaterValue(world.getBlockState(blockPos.west())) +
                getWaterValue(world.getBlockState(blockPos.north())) +
                getWaterValue(world.getBlockState(blockPos.south()))

        val value = blockState.getValue(SPIN)
        if (value == 15) {
            world.destroyBlock(blockPos, false)
            return
        }
        if (waterValue >= tier.factor) {
            //prevents it from becoming automateable with vanilla tools.
            if (random.nextBoolean() && value < 14) {
                world.setBlockState(blockPos, blockState.withProperty(SPIN, value + 2))
            } else {
                world.setBlockState(blockPos, blockState.withProperty(SPIN, value + 1))
            }

            world.notifyNeighborsOfStateChange(blockPos, this, true)

        } else {
            if (value > 0) {
                world.setBlockState(blockPos, blockState.withProperty(SPIN, value - 1))
                world.notifyNeighborsOfStateChange(blockPos, this, true)
            }
        }
    }

    override fun updateTick(world: World?, pos: BlockPos?, p_updateTick_3_: IBlockState?, p_updateTick_4_: Random?) {
        world!!.notifyNeighborsOfStateChange(pos!!, this, true)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(SPIN, meta)
    }

    override fun quantityDropped(random: Random): Int {
        return if (random.nextInt(10) < 3) 1 else 0
    }

    override fun getMetaFromState(blockState: IBlockState): Int {
        return blockState.getValue(SPIN)
    }

    private fun getWaterValue(blockState: IBlockState): Int {
        if (blockState.block !== Blocks.WATER) {
            return 0
        }
        val level = blockState.getValue(BlockLiquid.LEVEL)
        if (level > 4)
            return 4
        return if (level < 2) 0 else level
    }
}