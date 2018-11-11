package hu.frontrider.gearcraft.items

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.traits.ITooltipped
import net.minecraft.block.Block
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class TooltippedItemBlock(p_i45328_1_: Block) : ItemBlock(p_i45328_1_) {

    override fun addInformation(itemStack: ItemStack, world: World?, information: List<String>, flag: ITooltipFlag) {
        super.addInformation(itemStack, world, information, flag)
        (block as ITooltipped).setTooltip(information)
    }

    override fun getCreativeTab(): CreativeTabs {
        return GearCraft.creativeTab
    }

    override fun getCreativeTabs(): Array<CreativeTabs> {
        return arrayOf(GearCraft.creativeTab)
    }

    override fun isInCreativeTab(creativeTabs: CreativeTabs?): Boolean {
        return creativeTabs === GearCraft.creativeTab
    }
}
