package hu.frontrider.gearcraft.api.traits.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IRangedBlock {
    boolean isInRange(World world, BlockPos blockPos, IBlockState blockState);
    IBlockState getRangedState(World world, BlockPos blockPos, IBlockState blockState);
    int getRangeValue(World world, BlockPos blockPos, IBlockState blockState);
}
