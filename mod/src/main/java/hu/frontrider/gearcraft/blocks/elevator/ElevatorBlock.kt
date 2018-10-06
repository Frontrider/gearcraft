package hu.frontrider.gearcraft.blocks.elevator

import hu.frontrider.gearcraft.api.IPoweredBlock
import hu.frontrider.gearcraft.blocks.BlockBase
import hu.frontrider.gearcraft.registry.TierRegistry
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

import java.util.Random

import hu.frontrider.gearcraft.api.BlockStateHelpers.FACING

class ElevatorBlock(tier: TierRegistry.Tier, tag: String) : BlockBase(tier, "elevator", tag), IPoweredBlock {

    override fun getPower(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        return 0
    }

    override fun getStrength(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        return 0
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING)
    }

    override fun isValidSide(access: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
        return false
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return super.getStateFromMeta(meta)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return super.getMetaFromState(state)
    }

}
