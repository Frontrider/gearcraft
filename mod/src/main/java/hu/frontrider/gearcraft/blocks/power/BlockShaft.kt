package hu.frontrider.gearcraft.blocks.power

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.traits.power.ITransmission
import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.core.tooltip.PowerTooltip
import hu.frontrider.gearcraft.core.traits.shaft.ShaftPower
import hu.frontrider.gearcraft.core.traits.shaft.ShaftState
import hu.frontrider.gearcraft.core.traits.shaft.ShaftUpdater
import hu.frontrider.gearcraft.core.tooltip.MultiTooltip
import hu.frontrider.gearcraft.core.tooltip.TransmissionTooltip
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.Rotation
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockShaft(val power: Int,
                 resistance: Float,
                 name: String,
                 tool: String,
                 miningLevel: Int,
                 hardness: Float,
                 soundType: SoundType,
                 material: Material,
                 mapColor: MapColor,
                 private val shaftPower: ITransmission = ShaftPower(power)
) : Block(material, mapColor),
        ITransmission by shaftPower,
        ITooltipped by MultiTooltip(PowerTooltip(power),TransmissionTooltip()) {

    private val shaftUpdater: ShaftUpdater = ShaftUpdater(power)
    private val shaftState:ShaftState = ShaftState()

    init {
        setRegistryName(GearCraft.MODID, name)
        setSoundType(soundType)
        setResistance(resistance)
        setHardness(hardness)
        setHarvestLevel(tool, miningLevel)
        //tickRandomly = true
        unlocalizedName = "${GearCraft.MODID}.$name"
    }

    override fun observedNeighborChange(blockState: IBlockState?, world: World?, blockPos: BlockPos?, block: Block?, neighbourPos: BlockPos?) {
        shaftUpdater.update(blockState!!, world, blockPos)
    }

    override fun onBlockPlacedBy(world: World?, blockPos: BlockPos?, blockState: IBlockState?, p_onBlockPlacedBy_4_: EntityLivingBase?, p_onBlockPlacedBy_5_: ItemStack?) {
        shaftUpdater.update(blockState!!, world, blockPos)
    }

    override fun updateTick(world: World, blockPos: BlockPos, blockState: IBlockState, p_updateTick_4_: Random?) {
        shaftUpdater.update(blockState, world, blockPos)
    }

    override fun createBlockState(): BlockStateContainer {
        //minecraft bypasses the constructor, so it is null when the game boots up.
        if(shaftState == null)
        {
            return ShaftState().getDefaultState(this)
        }
        return shaftState.getDefaultState(this)
    }

    //block related stuff
    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    override fun isFullCube(blockState: IBlockState?): Boolean {
        return false
    }

    override fun isOpaqueCube(p_isOpaqueCube_1_: IBlockState?): Boolean {
        return false
    }

    override fun withRotation(state: IBlockState, rotation: Rotation?): IBlockState {
        return when (rotation) {
            Rotation.COUNTERCLOCKWISE_90, Rotation.CLOCKWISE_90 -> when (blockState.getProperty("axis")) {
                EnumFacing.Axis.X -> state.withProperty(BlockStates.AXIS, EnumFacing.Axis.Z)
                EnumFacing.Axis.Z -> state.withProperty(BlockStates.AXIS, EnumFacing.Axis.X)
                else -> state
            }
            else -> state
        }
    }

   override fun getStateFromMeta(meta: Int): IBlockState {
        return shaftState.getStateFromMeta(meta,this.defaultState)
    }

    override fun getMetaFromState(blockState: IBlockState): Int {
        return shaftState.getMetaFromState(blockState)
    }


    override fun getSilkTouchDrop(p_getSilkTouchDrop_1_: IBlockState): ItemStack {
        return ItemStack(Item.getItemFromBlock(this))
    }

    override fun getStateForPlacement(p_getStateForPlacement_1_: World?, p_getStateForPlacement_2_: BlockPos?, p_getStateForPlacement_3_: EnumFacing?, p_getStateForPlacement_4_: Float, p_getStateForPlacement_5_: Float, p_getStateForPlacement_6_: Float, p_getStateForPlacement_7_: Int, p_getStateForPlacement_8_: EntityLivingBase?): IBlockState {
        return super.getStateForPlacement(p_getStateForPlacement_1_, p_getStateForPlacement_2_, p_getStateForPlacement_3_, p_getStateForPlacement_4_, p_getStateForPlacement_5_, p_getStateForPlacement_6_, p_getStateForPlacement_7_, p_getStateForPlacement_8_).withProperty(BlockStates.AXIS, p_getStateForPlacement_3_!!.axis)
    }

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        val axis = state.getValue(BlockStates.AXIS)
        return when (axis) {
            EnumFacing.Axis.X -> alignedX
            EnumFacing.Axis.Y -> alignedY
            else -> alignedZ
        }
    }

    companion object {

        val alignedY = AxisAlignedBB(0.3125, 0.0, 0.3125, 0.6875, 1.0, 0.6875)
        val alignedX = AxisAlignedBB(0.0, 0.3125, 0.3125, 1.0, 0.6875, 0.6875)
        val alignedZ = AxisAlignedBB(0.3125, 0.3125, 0.0, 0.6875, 0.6875, 1.0)

    }
}