package hu.frontrider.gearcraft.plugins.entities

import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import hu.frontrider.gearcraft.GearCraft.Companion.instance
import hu.frontrider.gearcraft.entities.EntityEngineMinecart
import hu.frontrider.gearcraft.entities.EntityPowerManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.EntityRegistry


class Entities {

    fun init() {
        // Every entity in our mod has an ID (local to this mod)
        var id = 1
        EntityRegistry.registerModEntity(ResourceLocation(MODID, "wound_up_minecart"), EntityEngineMinecart::class.java, "wound_up_minecart", id++, instance, 64, 3, true, 0x996600, 0x00ff00)
        EntityRegistry.registerModEntity(ResourceLocation(MODID, "power_storage"), EntityPowerManager::class.java, "power_storage", id++, instance, 64, 3, true)

    }
}