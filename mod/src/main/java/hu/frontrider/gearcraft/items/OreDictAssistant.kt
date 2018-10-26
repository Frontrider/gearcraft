package hu.frontrider.gearcraft.items

import hu.frontrider.gearcraft.api.traits.IOredictionary
import hu.frontrider.gearcraft.basePlugin
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
        OreDictionary.registerOre("blockGold", Blocks.GOLD_BLOCK)
    }
}
