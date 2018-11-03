package hu.frontrider.gearcraft.gears.registry


import net.minecraft.block.Block
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import hu.frontrider.gearcraft.basePlugin
import hu.frontrider.gearcraft.blocks.tile.EngineTile
import net.minecraftforge.fml.common.registry.GameRegistry

@Mod.EventBusSubscriber(modid = MODID)
object BlockRegistry {

    @SubscribeEvent
    @JvmStatic
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        event.registry.registerAll(*basePlugin.blocks)
        GameRegistry.registerTileEntity(EngineTile::class.java, "$MODID:engine_helper");
    }
}
