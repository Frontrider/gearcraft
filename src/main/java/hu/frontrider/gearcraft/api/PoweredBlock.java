package hu.frontrider.gearcraft.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface PoweredBlock {
    int getPower(IBlockState blockstate, BlockPos pos, World world);
    IBlockState pushPower(IBlockState blockstate, BlockPos pos, World world, int level);
}
