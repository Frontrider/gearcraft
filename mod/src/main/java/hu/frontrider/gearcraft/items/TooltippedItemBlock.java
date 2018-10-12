package hu.frontrider.gearcraft.items;

import hu.frontrider.gearcraft.GearCraft;
import hu.frontrider.gearcraft.blocks.TooltippedBlock;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class TooltippedItemBlock extends ItemBlock {
    public TooltippedItemBlock(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> information, ITooltipFlag flag) {
        super.addInformation(itemStack, world, information, flag);
        ((TooltippedBlock)block).setTooltip(information);
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return GearCraft.Companion.getCreativeTab();
    }

    @Override
    public CreativeTabs[] getCreativeTabs() {
        return new CreativeTabs[]{GearCraft.Companion.getCreativeTab()};
    }

    @Override
    protected boolean isInCreativeTab(CreativeTabs creativeTabs) {
        return creativeTabs == GearCraft.Companion.getCreativeTab();
    }
}
