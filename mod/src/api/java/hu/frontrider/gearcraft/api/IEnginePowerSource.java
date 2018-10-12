package hu.frontrider.gearcraft.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IEnginePowerSource {

    IBlockState drainPower(World world, BlockPos pos, IBlockState blockState);

}
