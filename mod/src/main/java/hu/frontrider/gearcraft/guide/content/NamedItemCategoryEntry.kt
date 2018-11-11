package hu.frontrider.gearcraft.guide.content

import com.buuz135.project42.api.manual.CategoryEntry
import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryEntryDisplay
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class NamedItemCategoryEntry(private val itemStack: ItemStack,val name:String) : CategoryEntry() {

    constructor(item:Item,name:String):this(ItemStack(item),name)

    override fun getDisplay(): ICategoryEntryDisplay {
        return NamedItemStackCategoryEntryDisplay(this.itemStack,name)
    }
}
