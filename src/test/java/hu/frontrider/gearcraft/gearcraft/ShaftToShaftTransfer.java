package hu.frontrider.gearcraft.gearcraft;

import hu.frontrider.gearcraft.blocks.ShaftBlock;
import hu.frontrider.gearcraft.core.Tier;
import hu.frontrider.gearcraft.registry.TierRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Bootstrap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@DisplayName("Power transfers from shaft to shaft")
public class ShaftToShaftTransfer {

    private World world;
    private ShaftBlock shaftBlock;
    private Tier mockedTier;

    private BlockPos thisPos;
    private BlockPos neighbourPos;
    private BlockPos notNeighbourPos;
    private IBlockState resultState;

    @BeforeAll
    static void init() {
        Bootstrap.register();
    }

    @BeforeEach
    void setup() {
        mockedTier = spy(TierRegistry.wooden);
        world = mock(World.class);

        thisPos = new BlockPos(0, 1, 0);
        neighbourPos = new BlockPos(0, 2, 0);
        notNeighbourPos = new BlockPos(1, 1, 0);
        shaftBlock = new ShaftBlock(TierRegistry.wooden);

        when(world.getBlockState(thisPos))
                .thenReturn(shaftBlock.getDefaultState()
                        .withProperty(ShaftBlock.AXIS, EnumFacing.Axis.X)
                        .withProperty(ShaftBlock.POWER, 0));

        when(world.getBlockState(neighbourPos))
                .thenReturn(shaftBlock.getDefaultState()
                        .withProperty(ShaftBlock.AXIS, EnumFacing.Axis.X)
                        .withProperty(ShaftBlock.POWER, 3));

        when(world.getBlockState(notNeighbourPos))
                .thenReturn(shaftBlock.getDefaultState()
                        .withProperty(ShaftBlock.AXIS, EnumFacing.Axis.X)
                        .withProperty(ShaftBlock.POWER, 3));

        resultState = shaftBlock.getDefaultState()
                .withProperty(ShaftBlock.AXIS, EnumFacing.Axis.X)
                .withProperty(ShaftBlock.POWER, 2);
    }

    @Test
    void updatesPower() {
        shaftBlock.observedNeighborChange(world.getBlockState(thisPos),world, thisPos,shaftBlock,neighbourPos);
        verify(world,times(1)).setBlockState(thisPos,resultState);
    }

}
