package hu.frontrider.gearcraft.core.registry

import net.minecraft.block.Block
import net.minecraft.item.ItemStack

val recipes = ArrayList<BreakerRecipe>()

data class BreakerRecipe(val block: Block, val result: ItemStack)