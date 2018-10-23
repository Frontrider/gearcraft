package hu.frontrider.gearcraft.plugins.recipes

import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent

class CraftingEventhandler {

    @SubscribeEvent
    fun craft(event: PlayerEvent.ItemCraftedEvent) {
        if (event.crafting.item.registryName.toString() == "$MODID:planks_treated_wood") {
            event.player.addItemStackToInventory(ItemStack(Items.GLASS_BOTTLE))
        }
    }
}