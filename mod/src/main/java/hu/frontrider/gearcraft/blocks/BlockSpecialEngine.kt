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

import hu.frontrider.gearcraft.api.BlockStateHelpers.POWERED
import hu.frontrider.gearcraft.api.IEnginePowerSource
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.util.BlockRenderLayer
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockSpecialEngine(val power: Int,
                         resistance: Float,
                         name: String,
                         tool: String,
                         hardness: Float,
                         soundType: SoundType,
                         material: Material,
                         mapColor: MapColor,
                         val miningLevel: Int,
                         val powerSource: IEnginePowerSource) : Block(material, mapColor), IPoweredBlock {


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
        return BlockStateContainer(this, POWERED)
    }

    override fun getPower(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        val stage = iBlockAccess.getBlockState(blockPos).getValue(POWERED)
        return if (stage) 16 else 0
    }

    override fun getStrength(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        return power

    }

    override fun isValidSide(access: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
        return facing == EnumFacing.UP || facing == EnumFacing.DOWN
    }

    override fun randomTick(world: World, blockPos: BlockPos, blockState: IBlockState, random: Random) {
        world.setBlockState(blockPos,powerSource.drainPower(world,blockPos,blockState))
    }

    override fun updateTick(world: World?, pos: BlockPos?, p_updateTick_3_: IBlockState?, p_updateTick_4_: Random?) {
        world!!.notifyNeighborsOfStateChange(pos!!, this, true)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(POWERED, meta>0)
    }
    
    override fun getMetaFromState(blockState: IBlockState): Int {
        return if(blockState.getValue(POWERED))1 else 0
    }
    

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }
}