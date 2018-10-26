package hu.frontrider.gearcraft

import hu.frontrider.gearcraft.api.recipes.events.DismantlerRecipeRegistryEvent
import hu.frontrider.gearcraft.api.recipes.events.SawRecipeRegistryEvent
import hu.frontrider.gearcraft.blocks.machine.BlockDismantler
import hu.frontrider.gearcraft.blocks.machine.BlockSaw
import hu.frontrider.gearcraft.gears.events.DrillHandler
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import hu.frontrider.gearcraft.core.util.factory.ItemFactory
import hu.frontrider.gearcraft.items.OreDictAssistant
import hu.frontrider.gearcraft.plugins.recipes.dismantler.BaseDismantlerRecipes
import hu.frontrider.gearcraft.plugins.recipes.CraftingEventhandler
import hu.frontrider.gearcraft.proxy.CommonProxy
import hu.frontrider.gearcraft.tablet.network.redstoneproxy.create.CreateProxyMessage
import hu.frontrider.gearcraft.tablet.network.redstoneproxy.use.RedstoneProxyFailed
import hu.frontrider.gearcraft.tablet.network.redstoneproxy.create.CreateServerSideMessageHandler
import hu.frontrider.gearcraft.tablet.network.redstoneproxy.use.RedstoneProxyFailHandler
import hu.frontrider.gearcraft.tablet.network.redstoneproxy.use.RedstoneProxyHandler
import hu.frontrider.gearcraft.tablet.network.redstoneproxy.use.RedstoneProxyMessage
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import org.apache.logging.log4j.Logger
import java.io.File

@Mod(modid = GearCraft.MODID,
        name = GearCraft.NAME,
        version = GearCraft.VERSION,
        dependencies = "required-after:forgelin;")
class GearCraft {

    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        BlockFactory.apply {
            modid = MODID
            creativeTabs = creativeTab
        }
        ItemFactory.apply {
            modid = MODID
            creativeTabs = creativeTab
        }
        logger = event.modLog
        configDir = event.modConfigurationDirectory

        NETWORK_WRAPPER.registerMessage(CreateServerSideMessageHandler::class.java, CreateProxyMessage::class.java, 0, Side.SERVER)
        NETWORK_WRAPPER.registerMessage(RedstoneProxyFailHandler::class.java, RedstoneProxyFailed::class.java, 1, Side.CLIENT)
        NETWORK_WRAPPER.registerMessage(RedstoneProxyHandler::class.java, RedstoneProxyMessage::class.java, 2, Side.SERVER)
        basePlugin =PluginContainer()
    }

    @EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init(event)
        OreDictAssistant().register()

        MinecraftForge.EVENT_BUS.register(BaseDismantlerRecipes())
        MinecraftForge.EVENT_BUS.register(CraftingEventhandler())
        MinecraftForge.EVENT_BUS.register(DrillHandler())

        GameRegistry.addSmelting(preservativeRaw,ItemStack(preservativeFine),.1f)
    }
    @EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        //fireing the event to set up the recipes
        MinecraftForge.EVENT_BUS.post(DismantlerRecipeRegistryEvent(BlockDismantler.dismantleRecipes))
        MinecraftForge.EVENT_BUS.post(SawRecipeRegistryEvent(BlockSaw.sawRecipes))
    }

    companion object {

        @GameRegistry.ObjectHolder("$MODID:wooden_gear")
        lateinit var icon: Item

        @GameRegistry.ObjectHolder("$MODID:raw_preservative")
        lateinit var preservativeRaw: Item
        @GameRegistry.ObjectHolder("$MODID:fine_preservative")
        lateinit var preservativeFine: Item

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

        @SidedProxy(clientSide = "hu.frontrider.gearcraft.proxy.ClientProxy", serverSide = "hu.frontrider.gearcraft.proxy.CommonProxy")
        lateinit var proxy: CommonProxy
        val NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(MODID)
    }
}
