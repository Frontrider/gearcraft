package hu.frontrider.gearcraft.items;

import hu.frontrider.gearcraft.registry.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {

    public CreativeTab(int par1, String par2Str) {
        super(par1, par2Str);

    }
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemRegistry.INSTANCE.getWooden_gear(), 1, 0);
    }
    @Override
    public String getTabLabel() {
        return "gearcraft_creative_tab";
    }
}
