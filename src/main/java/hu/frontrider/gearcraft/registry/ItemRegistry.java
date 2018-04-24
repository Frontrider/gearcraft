package hu.frontrider.gearcraft.registry;

import hu.frontrider.gearcraft.GearCraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static hu.frontrider.gearcraft.registry.BlockRegistry.creativeGearbox;
import static hu.frontrider.gearcraft.registry.BlockRegistry.wooden_gearbox;

@Mod.EventBusSubscriber
public class ItemRegistry {

    public static final Item woodenShaft = itemToBlock(BlockRegistry.woodenShaft);

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
                registry.register(woodenShaft);
                registry.registerAll(
                registerAll(
                        wooden_gearbox,creativeGearbox));
    }

    private static Item[] registerAll(Block... blocks) {
        Item[] items = new Item[blocks.length];
        int i = 0;
        for (Block block : blocks) {
            items[i] = itemToBlock(block);
            i++;
        }
        return items;
    }

    private static Item itemToBlock(Block block) {
        return new ItemBlock(block)
                .setRegistryName(block.getRegistryName()
                        .getResourcePath())
                .setUnlocalizedName(block.getUnlocalizedName())
                .setCreativeTab(GearCraft.creativeTab);
    }
}
