package hu.frontrider.gearcraft.plugins.recipes

import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import hu.frontrider.gearcraft.api.dismantler.DismantlerRecipeRegistryEvent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry

class BaseDismantlerRecipes {

    companion object Items {
        @GameRegistry.ObjectHolder("$MODID:wood_pulp")
        lateinit var pulpWood: Item
        @GameRegistry.ObjectHolder("$MODID:stone_dust")
        lateinit var dustStone: Item

    }

    @SubscribeEvent
    fun addRecipes(event:DismantlerRecipeRegistryEvent){

        event.registerAllRecipe(
                OreDictDismantlerRecipe("stone", arrayOf(
                        ItemStack(dustStone))),
                OreDictDismantlerRecipe("logWood", arrayOf(
                        ItemStack(pulpWood))),
                OreDismantleRecipe()
        )
    }
}