package hu.frontrider.gearcraft.thermal

import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import hu.frontrider.gearcraft.core.util.factory.ItemFactory
import hu.frontrider.gearcraft.thermal.ThermalGearCraft.Companion.MODID
import hu.frontrider.gearcraft.thermal.ThermalGearCraft.Companion.NAME
import hu.frontrider.gearcraft.thermal.ThermalGearCraft.Companion.VERSION
import hu.frontrider.gearcraft.thermal.proxy.CommonProxy
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import org.apache.logging.log4j.Logger
import java.io.File

@Mod(modid = MODID,
        name = NAME,
        version = VERSION,
        dependencies = "required-after:gearcraft;required-after:thermalfoundation;")
class ThermalGearCraft {

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        BlockFactory.apply {
            this.modid = MODID
            creativeTabs = creativeTab
        }
        ItemFactory.apply {
            this.modid = MODID
            creativeTabs = creativeTab
        }
        basePlugin= PluginContainer()
    }


    companion object {

        @GameRegistry.ObjectHolder("$MODID:copper_gearbox")
        lateinit var icon: Block

        const val MODID = "gearcraft_thermal"
        const val NAME = "GearCraft: Thermally Expanded"
        const val VERSION = "1.0-snapshot"
        //public static final String DEPENDENCIES = "required-after:forge@[14.23.3.2655,15.0.0.0]required-after:cofhcore@[4.5.0,4.6.0);";

        private var logger: Logger? = null
        private var configDir: File? = null
        var creativeTab: CreativeTabs = object : CreativeTabs(MODID + "_tab") {
            override fun getTabIconItem(): ItemStack? {
                return ItemStack(icon)
            }
        }

        @SidedProxy(
                clientSide = "hu.frontrider.gearcraft.thermal.proxy.ClientProxy",
                serverSide = "hu.frontrider.gearcraft.thermal.proxy.CommonProxy"
        )
        lateinit var proxy: CommonProxy
    }
}