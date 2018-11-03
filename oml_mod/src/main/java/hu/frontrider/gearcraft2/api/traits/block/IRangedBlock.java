package hu.frontrider.gearcraft2.api.traits.block;

import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IRangedBlock {
    boolean isInRange(World world, BlockPos blockPos, BlockState blockState);
    BlockState getRangedState(World world, BlockPos blockPos, BlockState blockState);
    int getRangeValue(World world, BlockPos blockPos, BlockState blockState);
}
