package hu.frontrider.gearcraft;

import hu.frontrider.gearcraft.proxy.CommonProxy;
import hu.frontrider.gearcraft.registry.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = GearCraft.MODID, name = GearCraft.NAME, version = GearCraft.VERSION)
public class GearCraft {
    public static final String MODID = "gearcraft";
    public static final String NAME = "GearCraft";
    public static final String VERSION = "1.0-snapshot";
    	//public static final String DEPENDENCIES = "required-after:forge@[14.23.3.2655,15.0.0.0]required-after:cofhcore@[4.5.0,4.6.0);";


    private static Logger logger;
    private static File configDir;
    public static CreativeTabs creativeTab = new CreativeTabs(MODID + "_tab") {
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemRegistry.wooden_gear);
        }

    };

    @Mod.Instance
    public static GearCraft instance;

    @SidedProxy(clientSide = "hu.frontrider.gearcraft.proxy.ClientProxy", serverSide = "hu.frontrider.gearcraft.proxy.CommonProxy")
    private static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        configDir = event.getModConfigurationDirectory();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}
