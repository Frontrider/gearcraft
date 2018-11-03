package hu.frontrider.gearcraft.plugins.recipes.saw

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.recipes.ISawRecipe
import net.minecraft.block.Block
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import java.util.*

class PlankToSticks : ISawRecipe {

    companion object {

        @GameRegistry.ObjectHolder("${GearCraft.MODID}:wood_pulp")
        lateinit var pulp: Item

    }

    override fun isBlock(block: Block): Boolean {
        return isPlank(ItemStack(block))
    }

    override fun getResults(block: ItemStack, world: World): Array<ItemStack> {
        val dust = isPlank(block)
        if (dust) {
            return arrayOf(ItemStack(Items.STICK, world.rand.nextInt(3)+2), ItemStack(pulp, world.rand.nextInt(3)))
        }
        return arrayOf()
    }


    override fun splintery(): Boolean {
        return false
    }


    private fun isPlank(block: ItemStack): Boolean {
        OreDictionary.getOreIDs(block).filter {
            OreDictionary.getOreName(it).startsWith("plank")
        }.firstOrNull() ?: return false
        return true
    }
}