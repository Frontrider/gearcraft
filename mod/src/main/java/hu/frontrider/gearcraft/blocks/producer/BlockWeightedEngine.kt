package hu.frontrider.gearcraft.blocks.producer

import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.BlockStates.POWERSTATE
import hu.frontrider.gearcraft.api.traits.IInit
import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.api.traits.power.IGearPowered
import hu.frontrider.gearcraft.blocks.BlockBase
import hu.frontrider.gearcraft.blocks.tile.EngineTile
import hu.frontrider.gearcraft.gears.tooltip.MultiTooltip
import hu.frontrider.gearcraft.gears.tooltip.PowerTooltip
import hu.frontrider.gearcraft.gears.traits.producer.PoweredEngine
import hu.frontrider.gearcraft.gears.traits.producer.StagedPoweredEngine
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

open class BlockWeightedEngine(val power: Int,
                               resistance: Float,
                               tool: String,
                               hardness: Float,
                               soundType: SoundType,
                               material: Material,
                               mapColor: MapColor,
                               miningLevel: Int,
                               val initFunction:(BlockWeightedEngine)->Unit={},
                               private val time: Int = 1000,
                               val engine: PoweredEngine = StagedPoweredEngine(power, EnumFacing.UP)

) : BlockBase(resistance, tool, miningLevel, hardness, soundType, material, mapColor),
        IGearPowered by engine,
        ITooltipped by MultiTooltip(PowerTooltip(power), engine),
IInit{
    override fun init() {
        initFunction(this)
    }

    lateinit var drops: Array<ItemStack>
    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, BlockStates.POWERSTATE)
    }

    override fun hasTileEntity(state: IBlockState): Boolean {
        return state.getValue(BlockStates.POWERSTATE) == 1
    }

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, p_updateTick_4_: Random?) {

        val tileEntity = world.getTileEntity(pos)
        if (state.getValue(BlockStates.POWERSTATE) != 0) {
            world.scheduleUpdate(pos, this, 10)
        } else {
            return
        }
        if (state.getValue(BlockStates.POWERSTATE) == 2) {
            if (tileEntity != null) {
                world.removeTileEntity(pos)
            }
            return
        }

        if (tileEntity != null) {
            (tileEntity as EngineTile).update(world, pos, state)
        } else {
            world.setBlockState(pos, state.withProperty(POWERSTATE, 1))

            world.setTileEntity(pos, createTileEntity(world, state))
            world.scheduleUpdate(pos, this, 10)
            return
        }
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(BlockStates.POWERSTATE, meta)
    }


    override fun getDrops(drops: NonNullList<ItemStack>, world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int) {
        drops.addAll(this.drops)
    }


    override fun getExpDrop(state: IBlockState, world: IBlockAccess, pos: BlockPos, fortune: Int): Int {
        return if (state.getValue(POWERSTATE) == 2) 2 else 0
    }

    override fun getMetaFromState(blockState: IBlockState): Int {
        return blockState.getValue(BlockStates.POWERSTATE)
    }

    override fun createTileEntity(world: World, state: IBlockState): TileEntity? {
        if (state.getValue(POWERSTATE) == 1) {
            return EngineTile(time)
        }
        return null
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        if (worldIn.isBlockPowered(pos)) {
            worldIn.setBlockState(pos, state.withProperty(POWERSTATE, 1))
            worldIn.scheduleUpdate(pos, this, 0)
        }
    }

    @SideOnly(Side.CLIENT)
    override fun randomDisplayTick(stateIn: IBlockState, worldIn: World, pos: BlockPos, rand: Random) {
        if (stateIn.getValue(BlockStates.POWERSTATE) == 1) {
            val d0 = pos.x.toDouble() + 0.5
            val d1 = pos.y.toDouble()
            val d2 = pos.z.toDouble() + 0.5
            var d4 = rand.nextDouble() * 0.6 - 0.2
            if (rand.nextBoolean()) {
                d4 *= -1
            }

            worldIn.spawnParticle(EnumParticleTypes.CRIT, d0 + d4, d1 + 1.2, d2 + d4, 0.0, 0.0, 0.0)
            worldIn.spawnParticle(EnumParticleTypes.CRIT, d0 + d4, d1 + 1.2, d2 + d4, 0.0, 0.0, 0.0)
            worldIn.spawnParticle(EnumParticleTypes.CRIT, d0 + d4, d1 + 1.2, d2 + d4, 0.0, 0.0, 0.0)


        }
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