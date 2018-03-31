package hu.frontrider.gearcraft.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface PoweredBlock {
    int getLevel(IBlockState blockstate,BlockPos pos,World world);
    IBlockState setLevel(IBlockState blockstate,BlockPos pos,World world,int level);
}
