package hu.frontrider.gearcraft.registry


import net.minecraft.block.Block
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


import hu.frontrider.gearcraft.GearCraft.MODID
import hu.frontrider.gearcraft.annotations.BlockInit

@Mod.EventBusSubscriber(modid = MODID)
@BlockInit
object BlockRegistry {

    @SubscribeEvent
    @JvmStatic
    fun registerBlocks(event: RegistryEvent.Register<Block>) {

    }
}
