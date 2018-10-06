package hu.frontrider.gearcraft.registry


import hu.frontrider.gearcraft.blocks.*
import net.minecraft.block.Block
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

import java.util.ArrayList

import hu.frontrider.gearcraft.GearCraft.MODID
import hu.frontrider.gearcraft.registry.TierRegistry.STONE
import hu.frontrider.gearcraft.registry.TierRegistry.TIERS
import hu.frontrider.gearcraft.registry.TierRegistry.WOODEN

@Mod.EventBusSubscriber(modid = MODID)
object BlockRegistry {

    lateinit var BLOCKS: Array<Block>
    val CREATIVE_GEARBOX = CreativeGearbox()

    @SubscribeEvent
    @JvmStatic
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        val registry = event.registry
        val blocks = ArrayList<Block>()
        for (tier in TIERS) {
            blocks.add(ShaftBlock(tier))
            blocks.add(GearboxBlock(tier))

            if (tier === WOODEN || tier === STONE) {
                blocks.add(WaterMill(tier))
            }
            if(tier!= WOODEN){
                blocks.add(CoalGenerator(tier))
            }
            blocks.add(BlockBreaker(tier))
        }
        blocks.add(CREATIVE_GEARBOX)
        blocks.add(Magnet())

        BLOCKS = blocks.toTypedArray()
        registry.registerAll(*BLOCKS)
    }
}
