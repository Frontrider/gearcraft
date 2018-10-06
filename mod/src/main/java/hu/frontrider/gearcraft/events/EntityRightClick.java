package hu.frontrider.gearcraft.events;

import hu.frontrider.gearcraft.items.StaffOfTheGhost;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EntityRightClick {

    @SubscribeEvent
    public static void entityRightClick(PlayerInteractEvent.EntityInteract event) {
        final EntityPlayer player = event.getEntityPlayer();
        final Item item = event.getItemStack().getItem();
        if (item instanceof StaffOfTheGhost) {
            player.startRiding(event.getTarget(), true);
        }
    }
}
