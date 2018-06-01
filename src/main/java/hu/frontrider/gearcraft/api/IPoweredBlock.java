package hu.frontrider.gearcraft.api;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * This interface have to be implemented by every block, which can output power. (only if you output! you don't need it if you only input!)
 * */
public interface IPoweredBlock {
    /**
     * Returns the signal strength of the block
     * */
    int getPower(IBlockAccess iBlockAccess, BlockPos blockPos);
    /**
     * Returns the amount of power it can transfer
     * */
    int getStrength(IBlockAccess iBlockAccess, BlockPos blockPos);
    /**
     * Checks if it can transfer power on this side.
     * */
    boolean isValidSide(IBlockAccess access, BlockPos pos, EnumFacing facing);
}
