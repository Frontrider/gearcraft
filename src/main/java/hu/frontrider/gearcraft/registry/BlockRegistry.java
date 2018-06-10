package hu.frontrider.gearcraft.registry;


import hu.frontrider.gearcraft.blocks.*;
import hu.frontrider.gearcraft.blocks.piston.CustomPistonExtension;
import hu.frontrider.gearcraft.blocks.piston.MechanicalPistonBase;
import hu.frontrider.gearcraft.registry.TierRegistry.*;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

import static hu.frontrider.gearcraft.registry.TierRegistry.*;

@Mod.EventBusSubscriber
public class BlockRegistry {

    public static Block[] BLOCKS;
    public static final CreativeGearbox CREATIVE_GEARBOX = new CreativeGearbox();
    public static final MechanicalPistonBase MECHANICAL_PISTON = new MechanicalPistonBase(false);
    public static final CustomPistonExtension PISTON_EXTENSION = new CustomPistonExtension(MECHANICAL_PISTON);

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        final ArrayList<Block> blocks = new ArrayList<>();
        for (Tier tier : TIERS) {
            blocks.add(new ShaftBlock(tier));
            blocks.add(new GearboxBlock(tier));

            if(tier == WOODEN || (tier == STONE)) {
                blocks.add(new WaterMill(tier));
            }
            if (tier != WOODEN) {
                blocks.add(new CoalGenerator(tier));
            }
            blocks.add(new BlockBreaker(tier));
        }
        blocks.add(CREATIVE_GEARBOX);
        blocks.add(new Magnet());
        blocks.add(MECHANICAL_PISTON);
        blocks.add(new MechanicalPumpkin());

        BLOCKS = blocks.toArray(new Block[0]);
        registry.registerAll(BLOCKS);
        registry.registerAll(PISTON_EXTENSION);
    }
}
