package hu.frontrider.gearcraft.registry;

import hu.frontrider.gearcraft.entities.EntityMechanicalGolem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import static hu.frontrider.gearcraft.GearCraft.MODID;

@Mod.EventBusSubscriber
public class EntityRegistry {

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {

        EntityEntry mech_golem = EntityEntryBuilder.create()
                .entity(EntityMechanicalGolem.class)
                .name("mech_golem")
                .id(MODID + ":mech_golem", 0)
                .tracker(10,10,true)
                .build();
        event.getRegistry().register(mech_golem);
    }
}
