package hu.frontrider.gearcraft.guide.content

import com.buuz135.project42.api.manual.impl.category.BasicCategoryEntry
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class ItemCategoryEntry(item:Item) :BasicCategoryEntry(ItemStack(item)){
}