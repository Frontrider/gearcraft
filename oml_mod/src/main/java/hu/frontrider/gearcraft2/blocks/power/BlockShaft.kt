package hu.frontrider.gearcraft2.blocks.power

import hu.frontrider.gearcraft.gears.tooltip.PowerTooltip
import hu.frontrider.gearcraft.gears.tooltip.TransmissionTooltip
import hu.frontrider.gearcraft.gears.traits.shaft.ShaftState
import hu.frontrider.gearcraft.gears.traits.shaft.ShaftUpdater
import hu.frontrider.gearcraft2.api.BlockStates
import hu.frontrider.gearcraft2.api.traits.ITooltipped
import hu.frontrider.gearcraft2.api.traits.power.ITransmission
import hu.frontrider.gearcraft2.core.util.data.getBlockState
import hu.frontrider.gearcraft2.gears.tooltip.MultiTooltip
import hu.frontrider.gearcraft2.gears.traits.shaft.ShaftPower
import net.minecraft.block.Block
import net.minecraft.block.state.BlockState
import net.minecraft.client.render.block.BlockRenderLayer
import net.minecraft.entity.EntityLiving
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Facing
import net.minecraft.world.IWorld
import net.minecraft.world.World
import java.util.*

class BlockShaft(val power:Int,builder:Block.Builder) : Block(builder),
        ITransmission by ShaftPower(power),
        ITooltipped by MultiTooltip(PowerTooltip(power),TransmissionTooltip()) {

    private val shaftUpdater: ShaftUpdater = ShaftUpdater(power)
    private val shaftState:ShaftState = ShaftState()

    init{
        this.defaultState = this.defaultState.with(BlockStates.AXIS, Facing.Axis.Y).with(BlockStates.POWER,0) as BlockState

    }

    override fun onBlockUpdate(blockState: BlockState, aFacing2: Facing, aBlockState3: BlockState, world: IWorld, blockPos: BlockPos, aBlockPos6: BlockPos): BlockState {
        shaftUpdater.update(blockState, world, blockPos)
        return world.getBlockState(blockPos)
    }

    override fun onPlaced(world: World, blockPos: BlockPos, blockState: BlockState, aEntityLiving4: EntityLiving?, aItemStack5: ItemStack) {
        shaftUpdater.update(blockState, world, blockPos)
    }

    override fun updateTick(blockState: BlockState, world: World, blockPos: BlockPos, aRandom4: Random) {
        shaftUpdater.update(blockState, world, blockPos)
    }


    override fun getRenderLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    override fun isFullBlock(aBlockState: BlockState): Boolean {
        return false
    }

}