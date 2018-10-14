package hu.frontrider.gearcraft.api.power;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The data that marks a block to support mechanical power.
 *
 * MUST BE ON THE BLOCK, NOT THE TILE ENTITY!
 *
 * In the default implementation it's delegated to a handler.
 * */
public interface IGearPowered {

    /**
     *
     * @param world      the current world
     * @param blockPos   the position of the block we handle
     * @param blockState the state of the block we handle
     * @return the power level you emit, 0 if you can't emit anything.
     */
    int getPower(World world, BlockPos blockPos, IBlockState blockState);

    /**
     *
     * @param world      the current world
     * @param blockPos   the position of the block we handle
     * @param blockState the state of the block we handle
     * @return the strenght of the "signal" you're carrying.
     */
    int getStrength(World world, BlockPos blockPos, IBlockState blockState);


    /**
     * @param side the side that we want to access
     * @return true if you support extraction from this side
     * */
    boolean isSideSupported(World world, BlockPos blockPos, IBlockState blockState,EnumFacing side);

}
