package hu.frontrider.gearcraft.items

import hu.frontrider.gearcraft.api.traits.IOredictionary
import net.minecraft.item.Item


class ColoredItem(val color : Array<Int>,val oreDict:String): Item(), IOredictionary {
    override fun getName(): String {
        return oreDict
    }

}