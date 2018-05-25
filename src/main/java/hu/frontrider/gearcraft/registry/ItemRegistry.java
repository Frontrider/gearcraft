package hu.frontrider.gearcraft.registry;

import hu.frontrider.gearcraft.GearCraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

import static hu.frontrider.gearcraft.GearCraft.MODID;
import static hu.frontrider.gearcraft.GearCraft.creativeTab;
import static hu.frontrider.gearcraft.registry.BlockRegistry.BLOCKS;

@Mod.EventBusSubscriber
public class ItemRegistry {

    public static ArrayList<Item> items = new ArrayList<>();

    public static Item wooden_gear = new Item()
            .setRegistryName(new ResourceLocation(MODID, "wooden_gear"))
            .setUnlocalizedName("wooden_gear");

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(
                registerAll(
                        BLOCKS));
        registry.register(wooden_gear);
    }

    private static Item[] registerAll(Block... blocks) {
        Item[] items = new Item[blocks.length];
        int i = 0;
        for (Block block : blocks) {
            items[i] = itemToBlock(block);
            items[i].setCreativeTab(creativeTab);
            ItemRegistry.items.add(items[i]);
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
