package hu.frontrider.gearcraft.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * This interface have to be implemented by every block, which can output power.
 * */
public interface PoweredBlock {
    /**
     * Returns the signal strength of the block
     * */
    int getPower(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState leftBlock);
    /**
     * Returns the amount of power it can transfer
     * */
    int getStrength(IBlockAccess iBlockAccess, BlockPos blockPos);
    /**
     * Checks if it can transfer power on this side.
     * */
    boolean isValidSide(IBlockAccess access, BlockPos pos, EnumFacing facing);
}
