package hu.frontrider.gearcraft.items

import hu.frontrider.gearcraft.api.traits.IOredictionary
import net.minecraft.item.Item

class OreDictedItem(private val name:String):Item(), IOredictionary {
    override fun getName(): String {
        return name
    }
}