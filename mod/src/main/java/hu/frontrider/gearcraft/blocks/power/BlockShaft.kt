package hu.frontrider.gearcraft.blocks.power

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.BlockStates.AXIS
import hu.frontrider.gearcraft.api.BlockStates.POWER
import hu.frontrider.gearcraft.api.power.IGearPowered
import hu.frontrider.gearcraft.api.power.ITransmission
import hu.frontrider.gearcraft.core.util.cap
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
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*
import kotlin.math.absoluteValue

class BlockShaft(val power: Int,
                 resistance: Float,
                 name: String,
                 tool: String,
                 miningLevel: Int,
                 hardness: Float,
                 soundType: SoundType,
                 material: Material,
                 mapColor: MapColor ) : Block(material, mapColor), ITransmission {

    init {
        setRegistryName(GearCraft.MODID, name)
        setSoundType(soundType)
        setResistance(resistance)
        setHardness(hardness)
        setHarvestLevel(tool, miningLevel)
        tickRandomly = true
        unlocalizedName = "${GearCraft.MODID}.$name"
    }

    override fun observedNeighborChange(blockState: IBlockState?, world: World?, blockPos: BlockPos?, block: Block?, neighbourPos: BlockPos?) {
        update(blockState!!, world, blockPos)
    }

    override fun onBlockPlacedBy(world: World?, blockPos: BlockPos?, blockState: IBlockState?, p_onBlockPlacedBy_4_: EntityLivingBase?, p_onBlockPlacedBy_5_: ItemStack?) {
        update(blockState!!, world, blockPos)
    }

    override fun updateTick(world: World?, blockPos: BlockPos?, blockState: IBlockState?, p_updateTick_4_: Random?) {
        update(blockState!!, world, blockPos)
    }

    private fun update(blockState: IBlockState, world: World?, blockPos: BlockPos?) {
        val axis = blockState.getValue(BlockStates.AXIS)!!

        when (axis) {
            EnumFacing.Axis.Y -> {
                updateBlockState(world!!, blockPos!!.up(), blockPos.down(), blockState, blockPos, EnumFacing.DOWN, EnumFacing.UP)
            }
            EnumFacing.Axis.Z -> {
                updateBlockState(world!!, blockPos!!.north(), blockPos.south(), blockState, blockPos, EnumFacing.SOUTH, EnumFacing.NORTH)
            }
            EnumFacing.Axis.X -> {
                updateBlockState(world!!, blockPos!!.east(), blockPos.west(), blockState, blockPos, EnumFacing.WEST, EnumFacing.EAST)
            }
        }
    }

    private fun updateBlockState(world: World, left: BlockPos, right: BlockPos, thizState: IBlockState, thizPos: BlockPos, leftSide: EnumFacing, rightSide: EnumFacing) {
        val leftBlock = world.getBlockState(left)
        val rightBlock = world.getBlockState(right)

        val poweredLeft = leftBlock.block
        val poweredRight = rightBlock.block

        var rightPower = 0
        var leftPower = 0
        var leftStrength = 0
        var rightStrength = 0

        if (poweredLeft is IGearPowered) {
            if ((poweredLeft as IGearPowered).isSideSupported(world, left,world.getBlockState(left), leftSide))
                leftPower = (poweredLeft as IGearPowered).getPower(world, left,world.getBlockState(left)).cap(power)
            leftStrength = (poweredLeft as IGearPowered).getStrength(world, left,world.getBlockState(left)).cap(4)
        }
        if (poweredRight is IGearPowered) {
            if ((poweredRight as IGearPowered).isSideSupported(world, left,world.getBlockState(right), rightSide))
                rightPower = (poweredRight as IGearPowered).getPower(world, right,world.getBlockState(right)).cap(power)
            rightStrength = (poweredRight as IGearPowered).getStrength(world, right,world.getBlockState(right)).cap(4)
        }

        val thizPower = thizState.getValue(POWER)


        if(leftPower>=rightPower&&leftPower>=power && leftStrength>0){
            if(thizPower == leftStrength-1)
                return

            world.setBlockState(thizPos,thizState.withProperty(POWER,leftStrength-1))

            world.scheduleUpdate(left, poweredLeft, 10)
            world.scheduleUpdate(right, poweredRight, 10)
        }

        if(leftPower<=rightPower && rightPower>=power && rightStrength>0){
            if(thizPower == rightStrength-1)
                return

            world.setBlockState(thizPos,thizState.withProperty(POWER,rightStrength-1))

            world.scheduleUpdate(left, poweredLeft, 10)
            world.scheduleUpdate(right, poweredRight, 10)
        }

    }
    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, BlockStates.AXIS, BlockStates.POWER)
    }


    override fun getPower(world: World?, blockPos: BlockPos?, blockState: IBlockState?): Int {
        return power
    }

    override fun getStrength(world: World?, blockPos: BlockPos?, blockState: IBlockState): Int {
       return blockState.getValue(POWER)
    }

    override fun isSideSupported(world: World?, blockPos: BlockPos?, blockState: IBlockState, side: EnumFacing): Boolean {
        return side.axis == blockState.getValue(AXIS)
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
        var meta = meta
        if (meta > 15) {
            meta = 15
        }
        var axis: EnumFacing.Axis = EnumFacing.Axis.X
        val rotationBits = meta and 0x3
        val powerBits = meta shr 2 and 0x3

        when (rotationBits) {
            1 -> axis = EnumFacing.Axis.Y
            2 -> axis = EnumFacing.Axis.Z
        }
        return this.defaultState.withProperty(BlockStates.AXIS, axis).withProperty(BlockStates.POWER, powerBits)
    }

    override fun getMetaFromState(blockState: IBlockState): Int {
        var i = blockState.getValue(BlockStates.AXIS).ordinal
        val power = blockState.getValue(BlockStates.POWER).toInt()
        i += power shl 2
        return i
    }


    override fun getSilkTouchDrop(p_getSilkTouchDrop_1_: IBlockState): ItemStack {
        return ItemStack(Item.getItemFromBlock(this))
    }

    override fun getStateForPlacement(p_getStateForPlacement_1_: World?, p_getStateForPlacement_2_: BlockPos?, p_getStateForPlacement_3_: EnumFacing?, p_getStateForPlacement_4_: Float, p_getStateForPlacement_5_: Float, p_getStateForPlacement_6_: Float, p_getStateForPlacement_7_: Int, p_getStateForPlacement_8_: EntityLivingBase?): IBlockState {
        return super.getStateForPlacement(p_getStateForPlacement_1_, p_getStateForPlacement_2_, p_getStateForPlacement_3_, p_getStateForPlacement_4_, p_getStateForPlacement_5_, p_getStateForPlacement_6_, p_getStateForPlacement_7_, p_getStateForPlacement_8_).withProperty(BlockStates.AXIS, p_getStateForPlacement_3_!!.axis)
    }
}