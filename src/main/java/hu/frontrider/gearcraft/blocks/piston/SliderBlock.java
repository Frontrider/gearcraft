package hu.frontrider.gearcraft.blocks.piston;

import hu.frontrider.gearcraft.blocks.BlockBase;
import hu.frontrider.gearcraft.registry.TierRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static hu.frontrider.gearcraft.api.BlockStateHelpers.AXIS;
import static hu.frontrider.gearcraft.api.BlockStateHelpers.INVERTED;

public class SliderBlock extends BlockBase {

    public SliderBlock(TierRegistry.Tier tier) {
        this(tier, null);
    }

    public SliderBlock(TierRegistry.Tier tier, String tag) {
        super(tier, "slider", tag);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AXIS, INVERTED);
    }

    private void checkForMove(World worldIn, BlockPos pos, IBlockState state) {

    }

    @Override
    public void observedNeighborChange(IBlockState observerState, World world, BlockPos observerPos, Block changedBlock, BlockPos changedBlockPos) {

    }
}
