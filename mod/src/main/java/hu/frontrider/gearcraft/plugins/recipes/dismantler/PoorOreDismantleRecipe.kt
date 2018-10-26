package hu.frontrider.gearcraft.plugins.recipes.dismantler

import hu.frontrider.gearcraft.api.recipes.IDismantlerRecipe
import net.minecraft.block.Block
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary
import java.util.*

class PoorOreDismantleRecipe: IDismantlerRecipe {
    override fun isBlock(block: Block): Boolean {
        return getDust(block).isPresent
    }

    override fun getResults(block: Block): Array<ItemStack> {
        val dust = getDust(block)
        if(dust.isPresent){
            val resultStack = dust.get()
            resultStack.count = 2
            return arrayOf(resultStack)
        }
        return arrayOf()
    }

    private fun getDust(block: Block): Optional<ItemStack> {
        val first = OreDictionary.getOreIDs(ItemStack(block)).filter {
            OreDictionary.getOreName(it).startsWith("ore")
        }.firstOrNull()?: return Optional.empty()

        val oreName = OreDictionary.getOreName(first).removePrefix("ore")
        val materialName = "dust$oreName"

        return if (OreDictionary.doesOreNameExist(materialName)) {
            Optional.of(OreDictionary.getOres(materialName).first())
        } else {
            Optional.empty()
        }
    }
}