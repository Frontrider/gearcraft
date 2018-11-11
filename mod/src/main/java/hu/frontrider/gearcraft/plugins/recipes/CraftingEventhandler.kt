package hu.frontrider.gearcraft.plugins.recipes

import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import hu.frontrider.gearcraft.api.recipes.events.DismantlerRecipeRegistryEvent
import hu.frontrider.gearcraft.api.recipes.events.SawRecipeRegistryEvent
import hu.frontrider.gearcraft.plugins.recipes.dismantler.OreDictDismantlerRecipe
import hu.frontrider.gearcraft.plugins.recipes.dismantler.OreDismantleRecipe
import hu.frontrider.gearcraft.plugins.recipes.dismantler.PoorOreDismantleRecipe
import hu.frontrider.gearcraft.plugins.recipes.dismantler.SimpleDismantleRecipe
import hu.frontrider.gearcraft.plugins.recipes.saw.LogToPlanksRecipe
import hu.frontrider.gearcraft.plugins.recipes.saw.PlankToSticks
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent
import net.minecraftforge.fml.common.registry.GameRegistry

class CraftingEventhandler {

    @SubscribeEvent
    fun craft(event: PlayerEvent.ItemCraftedEvent) {
        if (event.crafting.item.registryName.toString() == "$MODID:planks_treated_wood") {
            event.player.addItemStackToInventory(ItemStack(Items.GLASS_BOTTLE))
        }
    }

    companion object ItemStorage {
        @GameRegistry.ObjectHolder("$MODID:wood_pulp")
        lateinit var pulpWood: Item
        @GameRegistry.ObjectHolder("$MODID:stone_dust")
        lateinit var dustStone: Item

        @GameRegistry.ObjectHolder("$MODID:obsidian_dust")
        lateinit var dustObsidian: Item

        @GameRegistry.ObjectHolder("$MODID:stone_sand")
        lateinit var stoneSand: Block
    }

    @SubscribeEvent
    fun addRecipes(event: DismantlerRecipeRegistryEvent){

        event.register(
                OreDictDismantlerRecipe("stone", arrayOf(
                        ItemStack(dustStone))),
                OreDictDismantlerRecipe("logWood", arrayOf(
                        ItemStack(pulpWood))),
                OreDismantleRecipe(),
                PoorOreDismantleRecipe(),
                SimpleDismantleRecipe(Blocks.OBSIDIAN, arrayOf(ItemStack(dustObsidian))),
                SimpleDismantleRecipe(stoneSand, arrayOf(ItemStack(Blocks.SAND)))
        )
    }
    @SubscribeEvent
    fun addRecipes(event: SawRecipeRegistryEvent){
        event.register(
                LogToPlanksRecipe(),
                PlankToSticks()
        )
    }
}