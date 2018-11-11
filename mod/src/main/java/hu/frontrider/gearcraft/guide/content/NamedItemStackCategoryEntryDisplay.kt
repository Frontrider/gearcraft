package hu.frontrider.gearcraft.guide.content

import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay
import com.buuz135.project42.util.ManualHelper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import java.awt.Color

class NamedItemStackCategoryEntryDisplay(private val itemStack: ItemStack,private val name:String) : ICategoryEntryDisplay {

    override fun render(mc: Minecraft, x: Int, y: Int, isHovered: Boolean) {
        mc.renderItem.renderItemIntoGUI(this.itemStack, x + 2, y + 2)
        val info = ManualHelper.getCurrentManualInfoFromGUI()
        val color = if (info == null) Color.CYAN.darker() else Color(info.design.textColor)
        mc.fontRenderer.drawString(name, (x + 22).toFloat(), (y + 7).toFloat(), if (isHovered) color.darker().rgb else color.rgb, false)
    }

    override fun getSizeX(): Int {
        return 123
    }

    override fun getSizeY(): Int {
        return 17
    }
}
