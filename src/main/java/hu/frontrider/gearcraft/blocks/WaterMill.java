package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.api.IPoweredBlock;
import hu.frontrider.gearcraft.registry.TierRegistry;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class WaterMill extends BlockBase implements IPoweredBlock {

    public static final PropertyInteger SPIN = PropertyInteger.create("spin", 0, 15);

    public WaterMill(TierRegistry.Tier tier, String tag) {
        super(tier, "watermill", tag);
        setTickRandomly(true);
    }

    public WaterMill(TierRegistry.Tier tier) {
        this(tier, null);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, SPIN);
    }

    @Override
    public int getPower(IBlockAccess iBlockAccess, BlockPos blockPos) {
        final Integer stage = iBlockAccess.getBlockState(blockPos).getValue(WaterMill.SPIN);
        if (stage >= 3)
            return 4;
        return 0;
    }

    @Override
    public int getStrength(IBlockAccess iBlockAccess, BlockPos blockPos) {
        return tier.power / 2;

    }

    @Override
    public boolean isValidSide(IBlockAccess access, BlockPos pos, EnumFacing facing) {
        return facing == EnumFacing.UP || facing == EnumFacing.DOWN;
    }

    @Override
    public void randomTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
        final int waterValue =
                getWaterValue(world.getBlockState(blockPos.down())) +
                        getWaterValue(world.getBlockState(blockPos.east())) +
                        getWaterValue(world.getBlockState(blockPos.west())) +
                        getWaterValue(world.getBlockState(blockPos.north())) +
                        getWaterValue(world.getBlockState(blockPos.south()));

        final Integer value = blockState.getValue(SPIN);
        if (value == 15) {
            world.destroyBlock(blockPos, false);
            return;
        }
        if (waterValue >= tier.factor) {
            //prevents it from becoming automateable with vanilla tools.
            if (random.nextBoolean() && value < 14) {
                world.setBlockState(blockPos, blockState.withProperty(SPIN, value + 2));
            } else {
                world.setBlockState(blockPos, blockState.withProperty(SPIN, value + 1));
            }

            world.notifyNeighborsOfStateChange(blockPos, this, true);

        } else {
            if (value > 0) {
                world.setBlockState(blockPos, blockState.withProperty(SPIN, value - 1));
                world.notifyNeighborsOfStateChange(blockPos, this, true);
            }
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState p_updateTick_3_, Random p_updateTick_4_) {
        world.notifyNeighborsOfStateChange(pos, this, true);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(SPIN, meta);
    }

    @Override
    public int getMetaFromState(IBlockState blockState) {
        return blockState.getValue(SPIN);
    }

    private int getWaterValue(IBlockState blockState) {
        if (blockState.getBlock() != Blocks.WATER) {
            return 0;
        }
        final Integer level = blockState.getValue(BlockLiquid.LEVEL);
        if (level > 4)
            return 4;
        if (level < 2)
            return 0;
        return level;
    }
}