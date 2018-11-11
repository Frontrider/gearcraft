package hu.frontrider.gearcraft.plugins.recipes

import hu.frontrider.gearcraft.GCConfig
import hu.frontrider.gearcraft.api.recipes.IGrindingBall
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.oredict.OreDictionary

object GrindingManager {

    fun init() {
        for (name in GCConfig.GrinderExtraDustList) {
            if (name.contains(":")) {
                val value = ForgeRegistries.ITEMS.getValue(ResourceLocation(name))
                if(value != null){
                    if(IGrindingBall.defaultitemList["ore"]==null){
                        IGrindingBall.defaultitemList["ore"]=ArrayList<ItemStack>()
                    }
                    IGrindingBall.defaultitemList["ore"]!!.add(ItemStack(value))
                }
            } else {
                val firstOrNull = OreDictionary.getOres(name).firstOrNull()
                if(firstOrNull != null){
                    if(IGrindingBall.defaultitemList["ore"]==null){
                        IGrindingBall.defaultitemList["ore"]=ArrayList<ItemStack>()
                    }
                    IGrindingBall.defaultitemList["ore"]!!.add(firstOrNull)
                }
            }
        }
    }
}