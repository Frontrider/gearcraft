package hu.frontrider.gearcraft.blocks.machine

import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.blocks.InvertedDirectionalBlockBase
import hu.frontrider.gearcraft.core.traits.PowerConsumer
import hu.frontrider.gearcraft.core.tooltip.MultiTooltip
import hu.frontrider.gearcraft.core.tooltip.PowerTooltip
import hu.frontrider.gearcraft.core.tooltip.RedstoneControlled
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockRouter(val power: Int,
                  resistance: Float,
                  name: String,
                  tool: String,
                  hardness: Float,
                  soundType: SoundType,
                  material: Material,
                  mapColor: MapColor,
                  val miningLevel: Int,
                  private val tooltip: ITooltipped = MultiTooltip(PowerTooltip(power),
                          RedstoneControlled("gearcraft.router.tooltip.redstone"))
) :
        InvertedDirectionalBlockBase(resistance, name, tool, miningLevel, hardness, soundType, material, mapColor),
        ITooltipped {
    protected val powerConsumer = PowerConsumer()

    override fun setTooltip(tooltip: MutableList<String>) {
        this.tooltip.setTooltip(tooltip)

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