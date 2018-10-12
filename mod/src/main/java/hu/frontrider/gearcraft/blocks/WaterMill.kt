package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.IPoweredBlock
import net.minecraft.block.BlockLiquid
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

import java.util.Random

import hu.frontrider.gearcraft.api.BlockStateHelpers.SPIN
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.util.BlockRenderLayer
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class WaterMill(val power: Int,
                resistance: Float,
                name: String,
                tool: String,
                hardness: Float,
                soundType: SoundType,
                material: Material,
                mapColor: MapColor,
                val miningLevel: Int) : Block(material, mapColor), IPoweredBlock {


    init{
        setRegistryName(GearCraft.MODID,name)
        setSoundType(soundType)
        setResistance(resistance)
        setHardness(hardness)
        setHarvestLevel(tool,miningLevel)
        tickRandomly = true
        unlocalizedName = "${GearCraft.MODID}.$name"
    }
    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, SPIN)
    }

    override fun getPower(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        val stage = iBlockAccess.getBlockState(blockPos).getValue(SPIN)
        return if (stage >= 3) 4 else 0
    }

    override fun getStrength(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        return power

    }

    override fun isValidSide(access: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
        return facing == EnumFacing.UP || facing == EnumFacing.DOWN
    }

    override fun randomTick(world: World, blockPos: BlockPos, blockState: IBlockState, random: Random) {
        val waterValue = getWaterValue(world.getBlockState(blockPos.down())) +
                getWaterValue(world.getBlockState(blockPos.east())) +
                getWaterValue(world.getBlockState(blockPos.west())) +
                getWaterValue(world.getBlockState(blockPos.north())) +
                getWaterValue(world.getBlockState(blockPos.south()))

        val value = blockState.getValue(SPIN)
        if (value == 15) {
            world.destroyBlock(blockPos, false)
            return
        }
        if (waterValue >= 4) {
            //prevents it from becoming automateable with vanilla tools.
            if (random.nextBoolean() && value < 14) {
                world.setBlockState(blockPos, blockState.withProperty(SPIN, value + 2))
            } else {
                world.setBlockState(blockPos, blockState.withProperty(SPIN, value + 1))
            }

            world.notifyNeighborsOfStateChange(blockPos, this, true)

        } else {
            if (value > 0) {
                world.setBlockState(blockPos, blockState.withProperty(SPIN, value - 1))
                world.notifyNeighborsOfStateChange(blockPos, this, true)
            }
        }
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

    private fun getWaterValue(blockState: IBlockState): Int {
        if (blockState.block !== Blocks.WATER) {
            return 0
        }
        val level = blockState.getValue(BlockLiquid.LEVEL)
        if (level > 4)
            return 4
        return if (level < 2) 0 else level
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }
}