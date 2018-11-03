package hu.frontrider.gearcraft.gears.events

import hu.frontrider.gearcraft.core.util.items.isNotEmpty
import hu.frontrider.gearcraft.items.tools.ItemToolDrill
import net.minecraft.entity.item.EntityItem
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumHand
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

class DrillHandler {

    //make the player drop any offhand items when held
    @SubscribeEvent
    fun holdDrill(event:TickEvent.PlayerTickEvent){

        val player = event.player
        if(player.world.isRemote)
            return

        val mainhand = player.heldItemMainhand
        if(mainhand.item is ItemToolDrill)
        {
            val heldItemOffhand = player.heldItemOffhand
            if(heldItemOffhand.isNotEmpty()){
                player.setHeldItem(EnumHand.OFF_HAND, ItemStack.EMPTY)
                player.world.spawnEntity(EntityItem(player.world,player.posX,player.posY,player.posZ,heldItemOffhand))
            }
        }
    }
}
