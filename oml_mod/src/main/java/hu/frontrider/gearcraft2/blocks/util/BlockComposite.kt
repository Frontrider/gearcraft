package hu.frontrider.gearcraft.blocks.util

import net.minecraft.block.Block
import net.minecraft.block.BlockFalling
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockComposite(material: Material,val mapColor: MapColor,val hardenedVariant: Block):BlockFalling(material) {


    private fun tryTouchWater(worldIn: World, pos: BlockPos, state: IBlockState): Boolean {
        var flag = false
        val var5 = EnumFacing.values()
        val var6 = var5.size

        for (var7 in 0 until var6) {
            val enumfacing = var5[var7]
            if (enumfacing != EnumFacing.DOWN) {
                val blockpos = pos.offset(enumfacing)
                if (worldIn.getBlockState(blockpos).material === Material.WATER) {
                    flag = true
                    break
                }
            }
        }

        if (flag) {
            worldIn.setBlockState(pos, hardenedVariant.defaultState, 3)
        }

        return flag
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        if (!this.tryTouchWater(worldIn, pos, state)) {
            super.neighborChanged(state, worldIn, pos, blockIn, fromPos)
        }

    }

    override fun onBlockAdded(worldIn: World, pos: BlockPos, state: IBlockState) {
        if (!this.tryTouchWater(worldIn, pos, state)) {
            super.onBlockAdded(worldIn, pos, state)
        }

    }

    override fun getMapColor(state: IBlockState, worldIn: IBlockAccess, pos: BlockPos): MapColor {
        return mapColor
    }

}