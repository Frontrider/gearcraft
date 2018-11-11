package hu.frontrider.gearcraft.items.aids

import hu.frontrider.gearcraft.api.recipes.IGrindingBall
import hu.frontrider.gearcraft.api.recipes.IGrindingBall.defaultitemList
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import java.util.*

class GrindingBall(val count:Int): Item(),IGrindingBall {

    override fun getBonus(itemStack: ItemStack, key: String, random: Random): Array<ItemStack?> {
        val list = defaultitemList[key]!!
        val results = arrayOfNulls<ItemStack>(count)
        for (i in 0..count) {
            results[i] =list[random.nextInt(list.size)-1]
        }
        return results
    }
}