package hu.frontrider.gearcraft.core.util

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler

fun Int.cap(cap: Int): Int {
    return if (this > cap)
        cap
    else
        this
}
