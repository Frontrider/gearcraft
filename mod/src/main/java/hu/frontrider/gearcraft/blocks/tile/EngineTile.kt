package hu.frontrider.gearcraft.blocks.tile

import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.BlockStates.POWERED
import hu.frontrider.gearcraft.api.BlockStates.POWERSTATE
import net.minecraft.block.state.IBlockState
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Used by the engines to manage power production
 *
 * */
open class EngineTile(var time: Int) : TileEntity() {

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setInteger("time", time)
        return compound
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        time = compound.getInteger("time")
    }

    open fun update(world: World, pos: BlockPos, state: IBlockState) {
        time -= 10
        if (time >= 0) {
            if (state.getValue(POWERSTATE)!=1) {
                world.setBlockState(pos, state.withProperty(BlockStates.POWERSTATE, 1))
            }
        } else {
            if (state.getValue(POWERSTATE)!=2)
                world.setBlockState(pos, state.withProperty(BlockStates.POWERSTATE, 2))
        }
    }
}


class BurnerEngineTile(time: Int):EngineTile(time){
    override fun update(world: World, pos: BlockPos, state: IBlockState) {
        time -= 10
        if (time <= 0) {
            if (!state.getValue(POWERED))
                world.setBlockState(pos, state.withProperty(BlockStates.POWERED, true))
        } else {
            if (state.getValue(POWERED))
                world.setBlockState(pos, state.withProperty(BlockStates.POWERED, false))
        }
    }
}