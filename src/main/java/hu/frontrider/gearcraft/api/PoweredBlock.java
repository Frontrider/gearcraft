package hu.frontrider.gearcraft.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface PoweredBlock {

    int getPower(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState leftBlock);
    int getStrength(IBlockAccess iBlockAccess, BlockPos blockPos);
    boolean isValidSide(IBlockAccess access, BlockPos pos, EnumFacing facing);
}
