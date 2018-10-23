package hu.frontrider.gearcraft.core.registry

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.traits.ITooltipped
import hu.frontrider.gearcraft.items.TooltippedItemBlock
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

import java.util.ArrayList

import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import hu.frontrider.gearcraft.basePlugin

@Mod.EventBusSubscriber(modid = MODID)
object ItemRegistry {
    var items =ArrayList<Item>()

    @SubscribeEvent
    @JvmStatic
    fun register(event: RegistryEvent.Register<Item>) {
        val registry = event.registry

        val itemBlocks = registerAll(*basePlugin.blocks)
        items.addAll(itemBlocks)
        items.addAll(basePlugin.items)
        registry.registerAll(*items.toArray(arrayOfNulls<Item>(items.size)))

    }

    private fun registerAll(vararg blocks: Block): Array<Item> {
        val blockItems = ArrayList<Item>()
        blocks.forEach {
            blockItems.add(itemToBlock(it))
        }
        return blockItems.toArray(arrayOfNulls<Item>(blockItems.size))
    }

    private fun itemToBlock(block: Block): Item {
        return if (block is ITooltipped) TooltippedItemBlock(block)
                .setRegistryName(block.registryName)
                .setCreativeTab(GearCraft.creativeTab)
                .setUnlocalizedName(block.unlocalizedName) else ItemBlock(block)
                .setRegistryName(block.registryName!!
                        .resourcePath)
                .setCreativeTab(GearCraft.creativeTab)
                .setUnlocalizedName(block.unlocalizedName)
    }
}
