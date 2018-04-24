package hu.frontrider.gearcraft;

import hu.frontrider.gearcraft.core.Tier;
import hu.frontrider.gearcraft.items.CreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Mod(modid = GearCraft.MODID, name = GearCraft.NAME, version = GearCraft.VERSION)
public class GearCraft {
    public static final String MODID = "gearcraft";
    public static final String NAME = "GearCraft";
    public static final String VERSION = "1.0-snapshot";

    private static Logger logger;
    private static File configDir;
    public Map<String,Tier> tierMap = new HashMap();
    public static CreativeTabs creativeTab = new CreativeTab(CreativeTabs.getNextID(), "Tutorial Blocks");

    @Mod.Instance
    public static GearCraft instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        configDir = event.getModConfigurationDirectory();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }
}
