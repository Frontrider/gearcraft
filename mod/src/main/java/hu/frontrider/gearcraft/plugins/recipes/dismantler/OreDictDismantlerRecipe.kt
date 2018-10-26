package hu.frontrider.gearcraft.plugins.recipes.dismantler

import hu.frontrider.gearcraft.api.recipes.IDismantlerRecipe
import net.minecraft.block.Block
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

class OreDictDismantlerRecipe(name:String,private val results: Array<ItemStack>): IDismantlerRecipe {
    val oreID = OreDictionary.getOreID(name)

    override fun isBlock(block: Block): Boolean {
        val oreIDs = OreDictionary.getOreIDs(ItemStack(block))
        return oreIDs.contains(oreID)
    }

    override fun getResults(block:Block): Array<ItemStack> {
        return results
    }
}