package hu.frontrider.gearcraft.blocks.machine

import hu.frontrider.gearcraft.api.BlockStates.FACING
import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.gears.traits.PowerConsumer
import hu.frontrider.gearcraft2.gears.tooltip.MultiTooltip
import hu.frontrider.gearcraft.gears.tooltip.PowerTooltip
import hu.frontrider.gearcraft2.gears.tooltip.RedstoneControlled
import hu.frontrider.gearcraft.gears.traits.SideManager
import hu.frontrider.gearcraft.core.util.InventoryChooser
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
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
                  val movedItems: Int,
                  private val tooltip: ITooltipped = MultiTooltip(PowerTooltip(power),
                          RedstoneControlled("gearcraft.router.tooltip.redstone"))
) :
        DirectionalBlockBase(resistance, name, tool, miningLevel, hardness, soundType, material, mapColor),
        ITooltipped {
    protected val powerConsumer = PowerConsumer()
    private val sideManager = SideManager()

    override fun setTooltip(tooltip: MutableList<String>) {
        this.tooltip.setTooltip(tooltip)
    }

    override fun observedNeighborChange(blockState: IBlockState, world: World, pos: BlockPos, block: Block, pos1: BlockPos) {
        if (world.isBlockIndirectlyGettingPowered(pos) > 0 || world.isBlockPowered(pos)) {

            val facing = blockState.getValue(FACING)
            val sides = sideManager.getSidesForTop(facing)
            val powerForSides = powerConsumer.getPowerForSides(world, pos, sides.toTypedArray())

            if (powerForSides >= power) {
                val source = InventoryChooser.getInventory(world, pos.offset(facing), facing.opposite)
                if (source.isPresent) {
                    val target = InventoryChooser.getInventory(world, pos.offset(facing.opposite), facing)
                    if (target.isPresent) {
                        val sourceInventory = source.get()
                        val targetInventory = target.get()
                        val testExtract = sourceInventory.extractFirst(false)
                        val oldCount = testExtract.count
                        if(testExtract.isNotEmpty()){
                            if(targetInventory.addItemStackToInventory(testExtract))
                            {
                                sourceInventory.extract(testExtract)
                            }else{
                                if(testExtract.count != oldCount) {
                                    sourceInventory.extract(testExtract)
                                }
                            }
                        }
                    }
                }
            }
        }
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