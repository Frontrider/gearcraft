package hu.frontrider.gearcraft.registry;

import hu.frontrider.gearcraft.blocks.CreativeGearbox;
import hu.frontrider.gearcraft.blocks.GearBox;
import hu.frontrider.gearcraft.blocks.Shaft;
import hu.frontrider.gearcraft.blocks.Transmission;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BlockRegistry {

    public static final Block wooden_gearbox = new GearBox(Material.WOOD, MapColor.BROWN)
            .setRegistryName("wooden_gearbox")
            .setUnlocalizedName("wooden_gearbox");

    public static final Block wooden_transmission = new Transmission(Material.WOOD, MapColor.BROWN)
            .setRegistryName("wooden_transmission")
            .setUnlocalizedName("wooden_transmission");
    public static final Block wooden_shaft = new Shaft(Material.WOOD, MapColor.BROWN)
            .setRegistryName("wooden_shaft")
            .setUnlocalizedName("wooden_shaft");

    public static final Block creative_gearbox = new CreativeGearbox(Material.BARRIER, MapColor.PURPLE)
            .setRegistryName("creative_gearbox")
            .setUnlocalizedName("creative_gearbox");

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(wooden_gearbox, creative_gearbox,wooden_transmission,wooden_shaft);
    }
}
