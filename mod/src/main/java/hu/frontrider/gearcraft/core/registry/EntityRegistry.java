package hu.frontrider.gearcraft.core.registry;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;


@Mod.EventBusSubscriber
public class EntityRegistry {

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {

    }
}
