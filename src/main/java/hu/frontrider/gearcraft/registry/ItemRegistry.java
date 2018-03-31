package hu.frontrider.gearcraft.registry;

import hu.frontrider.gearcraft.GearCraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static hu.frontrider.gearcraft.registry.BlockRegistry.*;

@Mod.EventBusSubscriber
public class ItemRegistry {

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(itemToBlock(wooden_gearbox),
                itemToBlock(creative_gearbox),
                itemToBlock(wooden_shaft),
                itemToBlock(wooden_transmission)
        );
    }

    private static Item itemToBlock(Block block) {
        return new ItemBlock(block)
                .setRegistryName(block.getRegistryName()
                        .getResourcePath())
                .setUnlocalizedName(block.getUnlocalizedName())
                .setCreativeTab(GearCraft.creativeTab);
    }
}
