package hu.frontrider.gearcraft

import hu.frontrider.gearcraft.proxy.CommonProxy
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import org.apache.logging.log4j.Logger

import java.io.File

@Mod(modid = GearCraft.MODID, name = GearCraft.NAME, version = GearCraft.VERSION, dependencies = "required-after:forgelin;required-before:guideapi")
class GearCraft {

    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger = event.modLog
        configDir = event.modConfigurationDirectory
    }

    @EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy!!.init(event)
    }


    companion object {

        @GameRegistry.ObjectHolder("$MODID:wooden_gear")
        lateinit var icon: Item

        const val MODID = "gearcraft"
        const val NAME = "GearCraft"
        const val VERSION = "1.0-snapshot"
        //public static final String DEPENDENCIES = "required-after:forge@[14.23.3.2655,15.0.0.0]required-after:cofhcore@[4.5.0,4.6.0);";

        private var logger: Logger? = null
        private var configDir: File? = null
        var creativeTab: CreativeTabs = object : CreativeTabs(MODID + "_tab") {
            override fun getTabIconItem(): ItemStack? {
                return ItemStack(icon)
            }
        }

        @SidedProxy(clientSide = "hu.frontrider.gearcraft.client.ClientProxy", serverSide = "hu.frontrider.gearcraft.proxy.CommonProxy")
        private lateinit var proxy: CommonProxy
    }
}
