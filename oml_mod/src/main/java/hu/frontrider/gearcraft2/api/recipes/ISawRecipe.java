package hu.frontrider.gearcraft2.api.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public interface ISawRecipe {

    boolean isBlock(Block block);
    ItemStack[] getResults(Block block);
    /**if set to true, doing this recipe will have a chance to damage players standing next to the block,
     * as it launches small splinters.
     */
    boolean splintery();

}
