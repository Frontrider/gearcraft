package hu.frontrider.gearcraft.blocks.producer

import hu.frontrider.gearcraft.core.util.ChatFormat
import hu.frontrider.gearcraft.core.util.formatTranslate
import hu.frontrider.gearcraft.GearCraft
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

import java.util.Random

import hu.frontrider.gearcraft.api.BlockStates.SPIN
import hu.frontrider.gearcraft.api.traits.power.IGearPowered
import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.blocks.BlockBase
import hu.frontrider.gearcraft.gears.traits.producer.SpinUpPowerSource
import hu.frontrider.gearcraft2.gears.tooltip.MultiTooltip
import hu.frontrider.gearcraft.gears.tooltip.PowerTooltip
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.util.BlockRenderLayer
import net.minecraftforge.common.BiomeDictionary
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class StrongWatermill(val power: Int,
                      resistance: Float,
                      name: String,
                      tool: String,
                      hardness: Float,
                      soundType: SoundType,
                      material: Material,
                      mapColor: MapColor,
                      miningLevel: Int,
                      private val spinUpPowerManager: SpinUpPowerSource = SpinUpPowerSource(power, EnumFacing.DOWN)
) : BlockBase(resistance, tool, miningLevel, hardness, soundType, material, mapColor),
        IGearPowered by spinUpPowerManager,
        ITooltipped {

    val tooltips = MultiTooltip(PowerTooltip(power), spinUpPowerManager)


    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, SPIN)
    }

    override fun randomTick(world: World, blockPos: BlockPos, blockState: IBlockState, random: Random) {

        val biome = world.getBiomeForCoordsBody(blockPos)
        val flag = BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN)

        spinUpPowerManager.doSpinUp(world, blockPos, blockState, random, flag)
    }

    override fun updateTick(world: World?, pos: BlockPos?, p_updateTick_3_: IBlockState?, p_updateTick_4_: Random?) {
        world!!.notifyNeighborsOfStateChange(pos!!, this, true)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(SPIN, meta)
    }

    //may or may not drop itself.
    override fun quantityDropped(random: Random): Int {
        return if (random.nextInt(10) < 3) 1 else 0
    }

    override fun getMetaFromState(blockState: IBlockState): Int {
        return blockState.getValue(SPIN)
    }

    override fun setTooltip(tooltip: MutableList<String>) {
        tooltips.setTooltip(tooltip)
        tooltip.add(formatTranslate("gearcraft.seaplace", ChatFormat.AQUA))
    }


    //block stuff
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
}