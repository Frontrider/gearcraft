package hu.frontrider.gearcraft.blocks.producer

import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.BlockStates.POWERED
import hu.frontrider.gearcraft.api.BlockStates.SPIN
import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.api.traits.power.IGearPowered
import hu.frontrider.gearcraft.blocks.DirectionalBlockBase
import hu.frontrider.gearcraft.blocks.tile.EngineTile
import hu.frontrider.gearcraft.core.util.BlockHelper
import hu.frontrider.gearcraft.core.util.inventory.InventoryChooser
import hu.frontrider.gearcraft.core.util.inventory.extract
import hu.frontrider.gearcraft.core.util.items.isNotEmpty
import hu.frontrider.gearcraft.entities.EntityPowerManager
import hu.frontrider.gearcraft.gears.tooltip.DoNotBreakTooltip
import hu.frontrider.gearcraft.gears.tooltip.MultiTooltip
import hu.frontrider.gearcraft.gears.tooltip.PowerTooltip
import hu.frontrider.gearcraft.gears.tooltip.RedstoneControlled
import hu.frontrider.gearcraft.gears.traits.producer.FueledEngine
import net.minecraft.block.Block
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.SoundType
import net.minecraft.block.material.EnumPushReaction
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.util.*
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockFuelBurnerEngine(val power: Int,
                            resistance: Float,
                            tool: String,
                            miningLevel: Int,
                            hardness: Float,
                            soundType: SoundType,
                            material: Material,
                            mapColor: MapColor,
                            val fuels:Array<ItemStack>,
                            val tickDelay:Int,
                            val engine:FueledEngine= FueledEngine(power,EnumFacing.UP, arrayOf())
) : DirectionalBlockBase(resistance, tool, miningLevel, hardness, soundType, material, mapColor),
        IGearPowered by engine,
        ITooltipped by MultiTooltip(PowerTooltip(power),
                engine,
                DoNotBreakTooltip(),
                RedstoneControlled("gearcraft.redstone_controlled.engine")) {

    override fun randomTick(world: World, blockPos: BlockPos, blockState: IBlockState, random: Random) {

    }

    override fun hasTileEntity(state: IBlockState): Boolean {
        return state.getValue(BlockStates.POWERED)
    }

    override fun getMobilityFlag(state: IBlockState): EnumPushReaction {
        return if(state.getValue(BlockStates.POWERED)) EnumPushReaction.BLOCK else EnumPushReaction.NORMAL
    }

    override fun onBlockAdded(worldIn: World, pos: BlockPos, state: IBlockState) {
        super.onBlockAdded(worldIn, pos, state)
        worldIn.scheduleUpdate(pos,this,10)
    }

    @SideOnly(Side.CLIENT)
    override fun randomDisplayTick(stateIn: IBlockState, worldIn: World, pos: BlockPos, rand: Random) {
        if (stateIn.getValue(POWERED)) {
            val enumfacing = stateIn.getValue<EnumFacing>(BlockHorizontal.FACING) as EnumFacing
            val d0 = pos.x.toDouble() + 0.5
            val d1 = pos.y.toDouble() + rand.nextDouble() * 6.0 / 16.0
            val d2 = pos.z.toDouble() + 0.5
            val d3 = 0.52
            val d4 = rand.nextDouble() * 0.6 - 0.3

            if (rand.nextDouble() < 0.1) {
                worldIn.playSound(pos.x.toDouble() + 0.5, pos.y.toDouble(), pos.z.toDouble() + 0.5, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false)
            }

            when (enumfacing) {
                EnumFacing.WEST -> {
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52, d1, d2 + d4, 0.0, 0.0, 0.0)
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52, d1, d2 + d4, 0.0, 0.0, 0.0)
                }
                EnumFacing.EAST -> {
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52, d1, d2 + d4, 0.0, 0.0, 0.0)
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52, d1, d2 + d4, 0.0, 0.0, 0.0)
                }
                EnumFacing.NORTH -> {
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52, 0.0, 0.0, 0.0)
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52, 0.0, 0.0, 0.0)
                }
                EnumFacing.SOUTH -> {
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52, 0.0, 0.0, 0.0)
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52, 0.0, 0.0, 0.0)
                }
                else -> {
                }
            }
        }
    }


    override fun updateTick(worldIn: World, pos: BlockPos, state: IBlockState, rand: Random) {
        val tileEntity = worldIn.getTileEntity(pos)
        if(tileEntity != null){
            (tileEntity as EngineTile).update(worldIn,pos,state)
            worldIn.scheduleUpdate(pos,this,10)
        }
        val optional = EnumFacing.values().filter {
            it != EnumFacing.UP
        }.map {
            InventoryChooser.getInventory(worldIn, pos.offset(it), it.opposite)
        }.firstOrNull {
            it.isPresent
        }?:return
        val iItemHandler = optional.get()

        if(tileEntity  == null || (tileEntity as EngineTile).time<=0)
        fuels.forEach {
            val extract = iItemHandler.extract(it)
            if(extract.isNotEmpty()){
                if(tileEntity == null) {
                    worldIn.setTileEntity(pos,EngineTile(10000))
                }else{
                    (tileEntity as EngineTile).time = 10000
                }
                worldIn.setBlockState(pos, state.withProperty(BlockStates.POWERED, true))
                worldIn.scheduleUpdate(pos,this,10)
                return
            }
        }

        if(worldIn.getTileEntity(pos) == null || (tileEntity as EngineTile).time<=0){
            worldIn.setBlockState(pos, state.withProperty(BlockStates.POWERED, false))
            if(tileEntity != null)
                worldIn.removeTileEntity(pos)
        }
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        if(worldIn.isBlockPowered(pos)){
            worldIn.scheduleUpdate(pos,this,0)
        }
    }

    override fun canConnectRedstone(state: IBlockState, world: IBlockAccess, pos: BlockPos, side: EnumFacing?): Boolean {
        return true
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState
                .withProperty(BlockHorizontal.FACING, Objects.requireNonNull(BlockHelper.getFacing(meta)))
                .withProperty(BlockStates.POWERED, meta and 8 > 0)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        var i = 0
        i = i or state.getValue(BlockHorizontal.FACING).getIndex()
        if (state.getValue(BlockStates.POWERED)) {
            i = i or 8
        }
        return i
    }


    override fun getItem(worldIn: World, pos: BlockPos, state: IBlockState): ItemStack {
        return ItemStack(this, 1, getMetaFromState(worldIn.getBlockState(pos)))
    }

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState {
        return defaultState.withProperty(POWERED,false)
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, BlockStates.POWERED, BlockHorizontal.FACING)
    }

    override fun breakBlock(worldIn: World, pos: BlockPos, state: IBlockState) {
        worldIn.removeTileEntity(pos)
    }

    override fun getComparatorInputOverride(blockState: IBlockState, worldIn: World, pos: BlockPos): Int {
        val tileEntity = (worldIn.getTileEntity(pos)?:0) as EngineTile
        if(tileEntity.time<=0)
            return 0

        return tileEntity.time/1000*15
    }

    override fun hasComparatorInputOverride(state: IBlockState): Boolean {
        return state.getValue(BlockStates.POWERED)
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

    override fun withRotation(p_withRotation_1_: IBlockState, p_withRotation_2_: Rotation?): IBlockState {
        return p_withRotation_1_.withProperty(BlockStates.FACING, p_withRotation_2_!!.rotate(p_withRotation_1_.getValue(BlockHorizontal.FACING)))
    }

    override fun withMirror(p_withMirror_1_: IBlockState, p_withMirror_2_: Mirror?): IBlockState {
        return p_withMirror_1_.withRotation(p_withMirror_2_!!.toRotation(p_withMirror_1_.getValue(BlockHorizontal.FACING)))
    }

    override fun getStateForPlacement(world: World?, pos: BlockPos?, facing: EnumFacing?, p_getStateForPlacement_4_: Float, p_getStateForPlacement_5_: Float, p_getStateForPlacement_6_: Float, p_getStateForPlacement_7_: Int, entityLivingBase: EntityLivingBase?): IBlockState {
        if(entityLivingBase != null)
        return this.defaultState
                .withProperty(BlockStates.FACING, entityLivingBase.horizontalFacing.opposite)
        return  super.getStateForPlacement(world, pos, facing, p_getStateForPlacement_4_, p_getStateForPlacement_5_, p_getStateForPlacement_6_, p_getStateForPlacement_7_, entityLivingBase)
    }

    override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        worldIn.setBlockState(pos, state.withProperty(BlockHorizontal.FACING, placer.horizontalFacing.opposite), 2)
    }
}