package hu.frontrider.gearcraft.plugins.recipes

import hu.frontrider.gearcraft.api.dismantler.DismantlerRecipe
import net.minecraft.block.Block
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

class OreDictDismantlerRecipe(name:String,private val results: Array<ItemStack>):DismantlerRecipe {
    val oreID = OreDictionary.getOreID(name)

    override fun isBlock(block: Block): Boolean {
        val oreIDs = OreDictionary.getOreIDs(ItemStack(block))
        return oreIDs.contains(oreID)
    }

    override fun getResults(block:Block): Array<ItemStack> {
        return results
    }
}