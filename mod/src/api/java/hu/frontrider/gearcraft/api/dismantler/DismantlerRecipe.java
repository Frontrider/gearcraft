package hu.frontrider.gearcraft.api.dismantler;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public interface DismantlerRecipe {

    boolean isBlock(Block block);
    ItemStack[] getResults(Block block);

}
