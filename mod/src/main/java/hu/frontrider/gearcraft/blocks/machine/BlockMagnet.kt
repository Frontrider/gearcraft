package hu.frontrider.gearcraft.blocks.machine

import hu.frontrider.gearcraft.api.traits.power.ITransmission
import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.blocks.BlockBase
import hu.frontrider.gearcraft.gears.tooltip.PowerTooltip
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockMagnet(val power: Int,
                  resistance: Float,
                  name: String,
                  tool: String,
                  hardness: Float,
                  soundType: SoundType,
                  material: Material,
                  mapColor: MapColor,
                  miningLevel: Int) : BlockBase(resistance,  tool, miningLevel, hardness, soundType, material, mapColor), ITooltipped {
    private val powerTooltip = PowerTooltip(power)

    override fun observedNeighborChange(blockState: IBlockState?, world: World?, pos: BlockPos?, block: Block?, pos1: BlockPos?) {
        if (world!!.isBlockIndirectlyGettingPowered(pos!!) > 0 || world.isBlockPowered(pos)) {
            val down = pos.down()
            val bottom = world.getBlockState(down)
            if (bottom.block is ITransmission) {
                if ((bottom.block as ITransmission).getPower(world, down,world.getBlockState(down),EnumFacing.UP) >= power) {
                    val items = world.getEntitiesWithinAABB(EntityItem::class.java, AxisAlignedBB((pos.x - 10).toDouble(), (pos.y - 10).toDouble(), (pos.z - 10).toDouble(), (pos.x + 10).toDouble(), (pos.y + 10).toDouble(), (pos.z + 10).toDouble()))
                    items.forEach { entityItem -> entityItem.setPositionAndUpdate(pos.x.toDouble()+.5, pos.y + 2.0, pos.z.toDouble()+.5) }
                }
            }
        }
    }

    override fun setTooltip(tooltip: MutableList<String>) {
        powerTooltip.setTooltip(tooltip)
        tooltip.add("area: 20x20x20")
        tooltip.add("Give it a redstone signal to attract items")
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

    override fun canConnectRedstone(state: IBlockState, world: IBlockAccess, pos: BlockPos, side: EnumFacing?): Boolean {
        return true
    }
}
