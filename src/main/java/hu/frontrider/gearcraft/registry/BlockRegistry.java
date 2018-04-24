package hu.frontrider.gearcraft.registry;

import hu.frontrider.gearcraft.blocks.CreativeGearbox;
import hu.frontrider.gearcraft.blocks.GearboxBlock;
import hu.frontrider.gearcraft.blocks.ShaftBlock;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class BlockRegistry {

    public static final ShaftBlock woodenShaft = new ShaftBlock(TierRegistry.wooden);
    public static final GearboxBlock wooden_gearbox = new GearboxBlock(TierRegistry.wooden);

    public static final CreativeGearbox creativeGearbox = new CreativeGearbox();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.registerAll(woodenShaft,wooden_gearbox,creativeGearbox);
    }
}
