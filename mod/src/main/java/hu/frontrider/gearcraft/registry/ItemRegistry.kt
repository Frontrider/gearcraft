package hu.frontrider.gearcraft.registry

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.blocks.TooltippedBlock
import hu.frontrider.gearcraft.items.StaffOfTheGhost
import hu.frontrider.gearcraft.items.TooltippedItemBlock
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.registries.IForgeRegistry

import java.util.ArrayList

import hu.frontrider.gearcraft.GearCraft.MODID
import hu.frontrider.gearcraft.GearCraft.creativeTab
import hu.frontrider.gearcraft.registry.BlockRegistry.BLOCKS

@Mod.EventBusSubscriber(modid = MODID)
object ItemRegistry {

    var items = ArrayList<Item>()

    var wooden_gear = Item()
            .setRegistryName(ResourceLocation(MODID, "wooden_gear"))
            .setUnlocalizedName("$MODID.wooden_gear").setCreativeTab(creativeTab)


    var stone_gear = Item()
            .setRegistryName(ResourceLocation(MODID, "stone_gear"))
            .setUnlocalizedName("$MODID.stone_gear").setCreativeTab(creativeTab)

    @SubscribeEvent
    @JvmStatic
    fun register(event: RegistryEvent.Register<Item>) {
        val registry = event.registry

        val itemBlocks = registerAll(*BLOCKS)
        items.addAll(itemBlocks)
        items.add(wooden_gear)
        items.add(stone_gear)
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
        return if (block is TooltippedBlock) TooltippedItemBlock(block)
                .setRegistryName(block.registryName)
                .setCreativeTab(GearCraft.creativeTab)
                .setUnlocalizedName(block.unlocalizedName) else ItemBlock(block)
                .setRegistryName(block.registryName!!
                        .resourcePath)
                .setCreativeTab(GearCraft.creativeTab)
                .setUnlocalizedName(block.unlocalizedName)

    }
}
