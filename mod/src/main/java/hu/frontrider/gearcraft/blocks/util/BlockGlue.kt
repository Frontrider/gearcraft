package hu.frontrider.gearcraft.blocks.util

import hu.frontrider.gearcraft.api.traits.IOredictionary
import net.minecraft.block.Block
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockGlue: Block(Material.CLAY, MapColor.BROWN),IOredictionary{
    companion object {
        val AABB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0)
    }

    override fun getName(): String {
        return "blokGlue"
    }

    override fun onEntityCollidedWithBlock(worldIn: World, pos: BlockPos, state: IBlockState, entityIn: Entity) {
        entityIn.motionX *= 0.4
        entityIn.motionZ *= 0.4
    }

    override fun isStickyBlock(state: IBlockState): Boolean {
        return true
    }

    override fun getCollisionBoundingBox(blockState: IBlockState, worldIn: IBlockAccess, pos: BlockPos): AxisAlignedBB? {
        return AABB
    }
}