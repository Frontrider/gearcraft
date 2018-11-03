package hu.frontrider.gearcraft.plugins.recipes.dismantler

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.recipes.IDismantlerRecipe
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import java.util.*

class SimpleDismantleRecipe(val block:Block,val result:Array<ItemStack>): IDismantlerRecipe {

    override fun isBlock(block: Block): Boolean {
        return block == this.block
    }

    override fun getResults(block: Block): Array<ItemStack> {
        return result
    }
}