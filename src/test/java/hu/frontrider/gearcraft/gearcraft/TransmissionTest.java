package hu.frontrider.gearcraft.gearcraft;


import hu.frontrider.gearcraft.blocks.GearBox;
import hu.frontrider.gearcraft.blocks.Transmission;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Bootstrap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import org.junit.jupiter.api.*;

import java.util.Random;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mock;

class TransmissionTest {

    private WorldServer world;
    private BlockPos current;
    private GearBox sourceBlock;
    private GearBox targetBlock;
    private Transmission transmission;

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
        when(world.getSeed()).thenReturn(123L);
    }

    @Nested
    class Up{
        @Test
        @DisplayName("with gearbox bellow")
        void withGearbox() {
            Random random = new Random(123L);
            when(world.getBlockState(current.down())).thenReturn(sourceBlock.getStateFromMeta(15));
            transmission.updateTick(world,current,transmission.getStateFromMeta(1),random);
            verify(world,times(1)).setBlockState(current.up(),targetBlock.getStateFromMeta(15));
        }
        @Test
        @DisplayName("without gearbox bellow")
        void NoGearbox() {
            Random random = new Random(123L);
            when(world.getBlockState(current.down())).thenReturn(Blocks.AIR.getDefaultState());
            transmission.updateTick(world,current,transmission.getStateFromMeta(1),random);
            verify(world,times(0)).setBlockState(current.up(),targetBlock.getStateFromMeta(15));
        }

    }

}
