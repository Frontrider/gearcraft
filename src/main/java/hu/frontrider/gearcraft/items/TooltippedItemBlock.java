package hu.frontrider.gearcraft.items;

import hu.frontrider.gearcraft.blocks.TooltippedBlock;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
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
}
