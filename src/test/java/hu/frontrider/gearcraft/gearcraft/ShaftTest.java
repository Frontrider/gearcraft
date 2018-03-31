package hu.frontrider.gearcraft.gearcraft;


import hu.frontrider.gearcraft.blocks.GearBox;
import hu.frontrider.gearcraft.blocks.Transmission;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Bootstrap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import org.junit.jupiter.api.*;

import java.util.Random;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

public class ShaftTest {

    WorldServer world;
    BlockPos current;
    GearBox sourceBlock;
    GearBox targetBlock;
    Transmission transmission;

    @BeforeAll
    static void bootstrap(){
        Bootstrap.register();
    }

    @BeforeEach
    void setup(){
        sourceBlock = new GearBox(Material.WOOD,MapColor.WOOD);
        targetBlock = new GearBox(Material.WOOD,MapColor.WOOD);
        transmission = new Transmission(Material.WOOD,MapColor.WOOD);
        current = new BlockPos(0,0,0);
        world = mock(WorldServer.class);
        when(world.getBlockState(current.up())).thenReturn(targetBlock.getStateFromMeta(0));
        when(world.getBlockState(current)).thenReturn(transmission.getStateFromMeta(1));
        when(world.getBlockState(current.down())).thenReturn(sourceBlock.getStateFromMeta(15));
        when(world.getSeed()).thenReturn(123L);
    }

    @Nested
    class Up{
        @Test
        @DisplayName("checking up.")
        void checkup() {
            Random random = new Random(123L);
            transmission.updateTick(world,current,transmission.getStateFromMeta(1),random);
            verify(world).setBlockState(current.up(),targetBlock.getStateFromMeta(15));
        }
    }

}
