package hu.frontrider.gearcraft.core.events

import hu.frontrider.gearcraft.items.StaffOfTheGhost
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber
object EntityRightClick {

    @SubscribeEvent
    fun entityRightClick(event: PlayerInteractEvent.EntityInteract) {
        val player = event.entityPlayer
        val item = event.itemStack.item
        if (item is StaffOfTheGhost) {
            player.startRiding(event.target, true)
        }
    }
}
