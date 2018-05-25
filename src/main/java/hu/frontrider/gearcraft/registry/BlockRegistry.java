package hu.frontrider.gearcraft.registry;

import hu.frontrider.gearcraft.blocks.CreativeGearbox;
import hu.frontrider.gearcraft.blocks.GearboxBlock;
import hu.frontrider.gearcraft.blocks.ShaftBlock;
import hu.frontrider.gearcraft.registry.TierRegistry.Tier;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

import static hu.frontrider.gearcraft.registry.TierRegistry.TIERS;

@Mod.EventBusSubscriber
public class BlockRegistry {

    public static Block[] BLOCKS;

    public static final CreativeGearbox creative_gearbox = new CreativeGearbox();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        final ArrayList<Block> blocks = new ArrayList<>();
        for(Tier tier :TIERS)
        {
            blocks.add(new ShaftBlock(tier));
            blocks.add(new GearboxBlock(tier));
        }

        blocks.add(creative_gearbox);
        BLOCKS = blocks.toArray(new Block[0]);
        registry.registerAll(BLOCKS);
    }
}
