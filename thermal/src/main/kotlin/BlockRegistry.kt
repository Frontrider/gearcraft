package hu.frontrider.gearcraft.thermal


import hu.frontrider.gearcraft.thermal.ThermalGearCraft.Companion.MODID
import net.minecraft.block.Block
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent



@Mod.EventBusSubscriber(modid = MODID)
object BlockRegistry {

    @SubscribeEvent
    @JvmStatic
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        event.registry.registerAll(*basePlugin.blocks)
    }
}
