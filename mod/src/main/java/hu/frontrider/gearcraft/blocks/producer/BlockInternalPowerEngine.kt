package hu.frontrider.gearcraft.blocks.producer

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.BlockStates.SPIN
import hu.frontrider.gearcraft.api.traits.block.IMetaBlock
import hu.frontrider.gearcraft.api.traits.power.IGearPowered
import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.blocks.BlockBase
import hu.frontrider.gearcraft.gears.traits.producer.SpinUpPowerSource
import hu.frontrider.gearcraft.gears.tooltip.DoNotBreakTooltip
import hu.frontrider.gearcraft.gears.tooltip.MultiTooltip
import hu.frontrider.gearcraft.gears.tooltip.PowerTooltip
import hu.frontrider.gearcraft.gears.tooltip.RedstoneControlled
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockInternalPowerEngine(val power: Int,
                               resistance: Float,
                               name: String,
                               tool: String,
                               miningLevel: Int,
                               hardness: Float,
                               soundType: SoundType,
                               material: Material,
                               mapColor: MapColor,
                               private val spinUpPowerManager: SpinUpPowerSource = SpinUpPowerSource(power, EnumFacing.DOWN, 1)
) : BlockBase(resistance, tool, miningLevel, hardness, soundType, material, mapColor),
        IGearPowered by spinUpPowerManager,
        IMetaBlock by spinUpPowerManager,
        ITooltipped by MultiTooltip(PowerTooltip(power),
                spinUpPowerManager,
                DoNotBreakTooltip(),
                RedstoneControlled("gearcraft.redstone_controlled.engine")) {

    override fun randomTick(world: World, blockPos: BlockPos, blockState: IBlockState, random: Random) {
        val flag = !world.isBlockPowered(blockPos)
        if (flag)
            spinUpPowerManager.doSpinUp(world, blockPos, blockState, random, flag)
    }

    override fun getPower(world: World, blockPos: BlockPos, blockState: IBlockState?, side: EnumFacing?): Int {
        if (world.isBlockPowered(blockPos)) {
            if (world.rand.nextInt(10) > 6)
                spinUpPowerManager.increment(world, blockPos, world.getBlockState(blockPos))
            return 0
        }

        return spinUpPowerManager.getPower(world, blockPos, blockState, side)
    }

    override fun canConnectRedstone(state: IBlockState, world: IBlockAccess, pos: BlockPos, side: EnumFacing?): Boolean {
        return true
    }


    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(BlockStates.SPIN, meta)
    }

    override fun getMetaFromState(blockState: IBlockState): Int {
        return blockState.getValue(BlockStates.SPIN)
    }


    override fun getItem(worldIn: World, pos: BlockPos, state: IBlockState): ItemStack {
        return ItemStack(this, 1, getMetaFromState(worldIn.getBlockState(pos)))
    }

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState {
        return defaultState.withProperty(SPIN,meta)
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, BlockStates.SPIN)
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