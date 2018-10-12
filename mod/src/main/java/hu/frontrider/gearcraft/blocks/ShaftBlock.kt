package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.IPoweredBlock
import net.minecraft.block.Block
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.Rotation
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.Random

import hu.frontrider.gearcraft.api.BlockStates.AXIS
import hu.frontrider.gearcraft.api.BlockStates.POWER
import hu.frontrider.gearcraft.core.util.cap
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material


class ShaftBlock (val power: Int,
                  resistance: Float,
                  name: String,
                  tool: String,
                  miningLevel: Int,
                  hardness: Float,
                  soundType: SoundType,
                  material: Material,
                  mapColor: MapColor) : Block(material,mapColor), IPoweredBlock, TooltippedBlock {

    init{
        setRegistryName(GearCraft.MODID,name)
        setSoundType(soundType)
        setResistance(resistance)
        setHardness(hardness)
        setHarvestLevel(tool,miningLevel)
        tickRandomly = true
        unlocalizedName = "${GearCraft.MODID}.$name"
    }
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

    override fun getBoundingBox(blockState: IBlockState, p_getBoundingBox_2_: IBlockAccess?, p_getBoundingBox_3_: BlockPos?): AxisAlignedBB {
        return when(blockState.getValue(AXIS)){
            EnumFacing.Axis.X-> alignedX
            EnumFacing.Axis.Z-> alignedZ
            else-> alignedY
        }
    }

    override fun withRotation(state: IBlockState, rotation: Rotation?): IBlockState {
        return when (rotation) {
            Rotation.COUNTERCLOCKWISE_90, Rotation.CLOCKWISE_90 -> when(blockState.getProperty("axis")){
                EnumFacing.Axis.X-> state.withProperty(AXIS,EnumFacing.Axis.Z)
                EnumFacing.Axis.Z-> state.withProperty(AXIS,EnumFacing.Axis.X)
                else-> state
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
        return this.defaultState.withProperty(AXIS, axis).withProperty(POWER, powerBits)
    }

    override fun getMetaFromState(blockState: IBlockState): Int {
        var i = blockState.getValue(AXIS).ordinal
        val power = blockState.getValue(POWER).toInt()
        i += power shl 2
        return i
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, AXIS, POWER)
    }

    override fun getSilkTouchDrop(p_getSilkTouchDrop_1_: IBlockState): ItemStack {
        return ItemStack(Item.getItemFromBlock(this))
    }

    override fun getStateForPlacement(p_getStateForPlacement_1_: World?, p_getStateForPlacement_2_: BlockPos?, p_getStateForPlacement_3_: EnumFacing?, p_getStateForPlacement_4_: Float, p_getStateForPlacement_5_: Float, p_getStateForPlacement_6_: Float, p_getStateForPlacement_7_: Int, p_getStateForPlacement_8_: EntityLivingBase?): IBlockState {
        return super.getStateForPlacement(p_getStateForPlacement_1_, p_getStateForPlacement_2_, p_getStateForPlacement_3_, p_getStateForPlacement_4_, p_getStateForPlacement_5_, p_getStateForPlacement_6_, p_getStateForPlacement_7_, p_getStateForPlacement_8_).withProperty(AXIS, p_getStateForPlacement_3_!!.axis)
    }


    override fun getPower(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        return iBlockAccess.getBlockState(blockPos).getValue(POWER).toInt()
    }

    override fun getStrength(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        return power
    }

    override fun isValidSide(access: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {

        val blockState = access.getBlockState(pos)

        val axis = blockState.getValue(AXIS)
        when (axis) {
            EnumFacing.Axis.Y -> if ((facing == EnumFacing.UP) or (facing == EnumFacing.DOWN))
                return true
            EnumFacing.Axis.Z -> if ((facing == EnumFacing.NORTH) or (facing == EnumFacing.SOUTH))
                return true
            EnumFacing.Axis.X -> if ((facing == EnumFacing.WEST) or (facing == EnumFacing.EAST))
                return true
        }

        return false
    }

    private fun update(blockState: IBlockState, world: World?, blockPos: BlockPos?) {
        val axis = blockState.getValue(AXIS)
        val leftSide: EnumFacing
        val rightSide: EnumFacing
        when (axis) {
            EnumFacing.Axis.Y -> {
                leftSide = EnumFacing.DOWN
                rightSide = EnumFacing.UP
                updateBlockState(world!!, blockPos!!.up(), blockPos.down(), blockState, blockPos, leftSide, rightSide)
            }
            EnumFacing.Axis.Z -> {
                leftSide = EnumFacing.SOUTH
                rightSide = EnumFacing.NORTH
                updateBlockState(world!!, blockPos!!.north(), blockPos.south(), blockState, blockPos, leftSide, rightSide)
            }
            EnumFacing.Axis.X -> {
                leftSide = EnumFacing.WEST
                rightSide = EnumFacing.EAST
                updateBlockState(world!!, blockPos!!.east(), blockPos.west(), blockState, blockPos, leftSide, rightSide)
            }
        }
    }

    private fun updateBlockState(world: World, left: BlockPos, right: BlockPos, thiz: IBlockState, thizPos: BlockPos, leftSide: EnumFacing, rightSide: EnumFacing) {
        val leftBlock = world.getBlockState(left)
        val rightBlock = world.getBlockState(right)
        val thizState = world.getBlockState(thizPos)

        val poweredLeft = leftBlock.block
        val poweredRight = rightBlock.block

        var rightPower = 0
        var leftPower = 0
        var leftStrength = 0
        var rightStrength = 0

        if (poweredLeft is IPoweredBlock) {
            if ((poweredLeft as IPoweredBlock).isValidSide(world, left, leftSide))
                leftPower = (poweredLeft as IPoweredBlock).getPower(world, left).cap(4)
            leftStrength = (poweredLeft as IPoweredBlock).getStrength(world, left).cap(4)
        }
        if (poweredRight is IPoweredBlock) {
            if ((poweredRight as IPoweredBlock).isValidSide(world, right, rightSide))
                rightPower = (poweredRight as IPoweredBlock).getPower(world, right).cap(4)
            rightStrength = (poweredRight as IPoweredBlock).getStrength(world, right).cap(4)
        }

        if (leftPower == rightPower) {
            world.setBlockState(thizPos, thizState.withProperty(POWER, 0))
        } else {
            if (leftPower > rightPower
                    && leftStrength >= power
                    && rightStrength <= power) {
                world.setBlockState(thizPos, thizState.withProperty(POWER, leftPower - 1))
            } else if (leftPower < rightPower
                    && rightStrength >= power
                    && leftStrength <= power) {
                world.setBlockState(thizPos, thizState.withProperty(POWER, rightPower - 1))
            }
        }

        world.scheduleUpdate(left, poweredLeft, 10)
        world.scheduleUpdate(right, poweredRight, 10)
    }


    private fun getPowerLevel(blockState: IBlockState, world: IBlockAccess?, pos: BlockPos?): Int {
        return (blockState.block as IPoweredBlock).getStrength(world!!, pos!!)
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

    @SideOnly(Side.CLIENT)
    override fun randomDisplayTick(blockState: IBlockState?, world: World?, blockPos: BlockPos?, random: Random?) {
        if (this.getPowerLevel(blockState!!, world, blockPos) > 0) {
            world!!.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
                    (blockPos!!.x + random!!.nextFloat() - 0.5f) * 0.2,
                    (blockPos.y + random.nextFloat() - 0.5f) * 0.2,
                    (blockPos.z + random.nextFloat() - 0.5f) * 0.2,
                    0.0, 0.0, 0.0)
        }
    }

    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add("Power level: " + power)
    }

    companion object {


        val alignedY = AxisAlignedBB(0.3125, 0.0, 0.3125, 0.6875, 1.0, 0.6875)
        val alignedX = AxisAlignedBB(0.0, 0.3125, 0.3125, 1.0, 0.6875, 0.6875)
        val alignedZ = AxisAlignedBB(0.3125, 0.3125, 0.0, 0.6875, 0.6875, 1.0)
    }
}
