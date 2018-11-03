package hu.frontrider.gearcraft.blocks.tile

import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.BlockStates.POWERED
import net.minecraft.block.state.IBlockState
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class EngineTile(var time: Int) : TileEntity() {

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setInteger("time", time)
        return compound
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        time = compound.getInteger("time")
    }

    fun update(world: World, pos: BlockPos, state: IBlockState) {
        time -=10
        if (time <= 0) {
            if (!state.getValue(POWERED))
                world.setBlockState(pos, state.withProperty(BlockStates.POWERED, true))
        }
    }

}