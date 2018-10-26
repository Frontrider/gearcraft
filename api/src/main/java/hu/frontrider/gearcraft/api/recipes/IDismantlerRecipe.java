package hu.frontrider.gearcraft.api.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public interface IDismantlerRecipe {

    boolean isBlock(Block block);
    ItemStack[] getResults(Block block);

}
