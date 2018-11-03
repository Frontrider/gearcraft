package hu.frontrider.gearcraft

import hu.frontrider.gearcraft.api.traits.IOredictionary
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

/**
 * Registering stuff to the ore dictionary
 */
class OreDictAssistant {

    fun register() {
        for (item in basePlugin.items) {
            if (item is IOredictionary)
                OreDictionary.registerOre(item.name, ItemStack(item))
        }
        for (block in basePlugin.blocks) {
            if (block is IOredictionary)
                OreDictionary.registerOre(block.name, ItemStack(block))
        }
        OreDictionary.registerOre("blockGold", Blocks.GOLD_BLOCK)
    }
}
