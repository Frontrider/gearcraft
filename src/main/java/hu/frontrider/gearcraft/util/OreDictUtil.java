package hu.frontrider.gearcraft.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictUtil {

    public static boolean blockMatchesName(Block block, String name) {
        final NonNullList<ItemStack> oredictEntries = OreDictionary.getOres(name);
        boolean foundMatch = false;
        for (ItemStack itemStack : oredictEntries) {
            if (foundMatch)
                break;
            final Item item = itemStack.getItem();
            if (item instanceof ItemBlock) {
                if (((ItemBlock) item).getBlock() == block)
                    foundMatch = true;
            }
        }
        return foundMatch;
    }

}
