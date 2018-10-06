package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.api.BlockStateHelpers.SPIN
import hu.frontrider.gearcraft.api.IPoweredBlock
import hu.frontrider.gearcraft.registry.TierRegistry
import net.minecraft.block.Block
import net.minecraft.block.material.EnumPushReaction
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.items.IItemHandler
import java.util.Random

import hu.frontrider.gearcraft.util.BlockHelper.isPowered
import net.minecraft.util.EnumFacing.DOWN
import net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY

class CoalGenerator @JvmOverloads constructor(tier: TierRegistry.Tier, tag: String? = null) : BlockBase(tier, "coal_generator", tag), IPoweredBlock, TooltippedBlock {

    init {
        tickRandomly = true
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, SPIN)
    }

    override fun randomTick(world: World, pos: BlockPos, blockState: IBlockState, random: Random) {
        val power = blockState.getValue(SPIN)
        val up = pos.up()
        val tileEntity = world.getTileEntity(up)
        var itemHandler: IItemHandler? = null
        if (tileEntity != null) {
            if (tileEntity.hasCapability(ITEM_HANDLER_CAPABILITY, DOWN)) {
                itemHandler = tileEntity.getCapability(ITEM_HANDLER_CAPABILITY, DOWN)
            }
        }

        if (itemHandler == null) {
            val entities = world.getEntitiesWithinAABB(Entity::class.java, AxisAlignedBB(up.x.toDouble(), up.y.toDouble(), up.z.toDouble(), (up.x + 1).toDouble(), (up.y + 1).toDouble(), (up.z + 1).toDouble()))
            for (entity in entities) {
                if (entity.hasCapability(ITEM_HANDLER_CAPABILITY, DOWN)) {
                    itemHandler = entity.getCapability(ITEM_HANDLER_CAPABILITY, DOWN)
                }
            }
        }
        if (itemHandler == null) {
            if (power > 0)
                world.setBlockState(pos, blockState.withProperty(SPIN,power - 1))
            return
        }

        if (canItemsFuel(itemHandler, true) && random.nextBoolean()) {
            canItemsFuel(itemHandler, false)
            if (power < 15)
                world.setBlockState(pos, blockState.withProperty(SPIN, power + 1))

        } else {
            world.setBlockState(pos, blockState.withProperty(SPIN, if (power - 1 > 0) power - 1 else 0))
        }

    }

    override fun observedNeighborChange(iBlockState: IBlockState?, world: World?, pos: BlockPos?, block: Block?, blockPos: BlockPos?) {
        if (isPowered(world!!, pos)) {
            if (world.getBlockState(pos!!).getValue(SPIN) ==0)
                randomTick(world, pos, iBlockState!!, world.rand)
        }
    }

    override fun getMetaFromState(state: IBlockState): Int {

        return state.getValue(SPIN)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState.withProperty(SPIN, meta)
    }

    override fun getMobilityFlag(p_getMobilityFlag_1_: IBlockState?): EnumPushReaction {
        return EnumPushReaction.BLOCK
    }

    private fun canItemsFuel(itemHandler: IItemHandler, simulate: Boolean): Boolean {
        val slots = itemHandler.slots
        for (i in 0 until slots) {
            val itemStack = itemHandler.getStackInSlot(i)
            val itemEqual = itemStack.isItemEqual(ItemStack(Items.COAL))
            if (itemEqual) {
                if (!simulate) {
                    itemStack.shrink(1)
                }
                return true
            }
        }
        return false
    }

    override fun getPower(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        val blockState = iBlockAccess.getBlockState(blockPos)
        return if (blockState.getValue(SPIN) > 8) 4 else 0
    }

    override fun getStrength(iBlockAccess: IBlockAccess, blockPos: BlockPos): Int {
        return tier.power
    }

    override fun isValidSide(access: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
        return facing == DOWN
    }

    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add("Put fuel into a chest above it.(minecraft works)")
        tooltip.add("Power level: " + tier.power)
    }

}
