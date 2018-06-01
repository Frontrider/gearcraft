package hu.frontrider.gearcraft.registry;


import hu.frontrider.gearcraft.blocks.*;
import hu.frontrider.gearcraft.registry.TierRegistry.Tier;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

import static hu.frontrider.gearcraft.registry.TierRegistry.STONE;
import static hu.frontrider.gearcraft.registry.TierRegistry.TIERS;

@Mod.EventBusSubscriber
public class BlockRegistry {

    public static Block[] BLOCKS;
    public static final CreativeGearbox CREATIVE_GEARBOX = new CreativeGearbox();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        final ArrayList<Block> blocks = new ArrayList<>();
        for (Tier tier : TIERS) {
            blocks.add(new ShaftBlock(tier));
            blocks.add(new GearboxBlock(tier));
            blocks.add(new WaterMill(tier));
        }

        blocks.add(CREATIVE_GEARBOX);
        //blocks.add(BLOCK_HEAT_ENGINE);
        blocks.add(new BlockBreaker(STONE));

        BLOCKS = blocks.toArray(new Block[0]);
        registry.registerAll(BLOCKS);


       // GameRegistry.registerTileEntity(BLOCK_HEAT_ENGINE.getTileEntityClass(), BLOCK_HEAT_ENGINE.getRegistryName().toString());
    }
}
