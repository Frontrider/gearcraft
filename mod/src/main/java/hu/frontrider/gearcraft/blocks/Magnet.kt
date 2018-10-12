package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.IPoweredBlock
import net.minecraft.block.Block
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Magnet : Block(Material.IRON, MapColor.GOLD), TooltippedBlock {
    init {
        setRegistryName(GearCraft.MODID, "stone_magnet")
        unlocalizedName = "stone_magnet"
    }

    override fun createBlockState(): BlockStateContainer {
        return super.createBlockState()
    }

    override fun observedNeighborChange(blockState: IBlockState?, world: World?, pos: BlockPos?, block: Block?, pos1: BlockPos?) {
        if (world!!.isBlockIndirectlyGettingPowered(pos!!) > 0 || world.isBlockPowered(pos)) {
            val down = pos.down()
            val bottom = world.getBlockState(down)
            if (bottom.block is IPoweredBlock) {
                if ((bottom.block as IPoweredBlock).getPower(world, down) > 0) {
                    val items = world.getEntitiesWithinAABB(EntityItem::class.java, AxisAlignedBB((pos.x - 10).toDouble(), (pos.y - 10).toDouble(), (pos.z - 10).toDouble(), (pos.x + 10).toDouble(), (pos.y + 10).toDouble(), (pos.z + 10).toDouble()))
                    items.forEach { entityItem -> entityItem.setPositionAndUpdate(pos.x.toDouble(), pos.y + 1.5, pos.z.toDouble()) }
                }
            }
        }
    }

    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add("area: 20x20x20")
        tooltip.add("Give it a redstone signal to attract items")
    }
}
