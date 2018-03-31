package hu.frontrider.gearcraft.gearcraft;


import hu.frontrider.gearcraft.blocks.CreativeGearbox;
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
    private BlockPos spyedPos;
    private GearBox sourceBlock;
    private GearBox targetBlock;
    private CreativeGearbox creativeGearbox;
    private Transmission transmission;
    private Random random;

    @BeforeAll
    static void bootstrap() {
        Bootstrap.register();
    }

    @BeforeEach
    void setup() {
        random = new Random(123L);
        sourceBlock = new GearBox(Material.WOOD, MapColor.WOOD);
        targetBlock = new GearBox(Material.WOOD, MapColor.WOOD);
        transmission = new Transmission(Material.WOOD, MapColor.WOOD);
        current = new BlockPos(0, 0, 0);
        world = mock(WorldServer.class);
        when(world.getBlockState(current.up())).thenReturn(targetBlock.getStateFromMeta(0));
        when(world.getBlockState(current)).thenReturn(transmission.getStateFromMeta(1));
        when(world.getSeed()).thenReturn(123L);
        creativeGearbox = new CreativeGearbox(Material.BARRIER,MapColor.AIR);

        spyedPos = spy(current);
    }

    @Nested
    class Up {
        @Nested
        @DisplayName("with gearbox")
        class gearbox {
            @BeforeEach
            void setup() {
                when(world.getBlockState(current.down())).thenReturn(creativeGearbox.getDefaultState());
                transmission.updateTick(world, spyedPos, transmission.getStateFromMeta(1), random);
            }

            @Test
            @DisplayName("world.setBlockState() is called")
            void setBlockState() {
                verify(world, times(1)).setBlockState(current.up(), targetBlock.getStateFromMeta(15),2);
            }

            @Test
            @DisplayName("The block bellow is accessed")
            void blockBellowAccessed(){
                verify(spyedPos,times(1)).down();
                verify(world,times(1)).getBlockState(current.down());
            }

            @Test
            @DisplayName("The block above is accessed")
            void blockAboveAccessed(){
                verify(spyedPos,times(1)).up();
                verify(world,times(1)).getBlockState(current.up());
            }

        }

        @Nested
        @DisplayName("without gearbox")
        class nogearbox {
            @BeforeEach
            void setup() {
                when(world.getBlockState(current.down())).thenReturn(Blocks.AIR.getDefaultState());
                transmission.updateTick(world, spyedPos, transmission.getStateFromMeta(1), random);
            }

            @Test
            @DisplayName("world.setBlockState() is not called")
            void setBlockState() {
                verify(world, times(0)).setBlockState(current.up(), targetBlock.getStateFromMeta(15),2);
            }

            @Test
            @DisplayName("The block bellow is accessed")
            void blockBellowAccessed(){
                verify(spyedPos,times(1)).down();
                verify(world,times(1)).getBlockState(current.down());
            }
            @Test
            @DisplayName("The block above is not accessed")
            void blockAboveAccessed(){
                verify(spyedPos,times(1)).up();
                verify(world,times(0)).getBlockState(current.up());
            }

        }

    }

}
