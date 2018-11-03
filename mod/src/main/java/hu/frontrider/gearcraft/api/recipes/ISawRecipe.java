package hu.frontrider.gearcraft.api.recipes;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ISawRecipe {

    boolean isBlock(Block block);
    ItemStack[] getResults(ItemStack block, World world);
    /**if set to true, doing this recipe will have a chance to damage players standing next to the block,
     * as it launches small splinters.
     */
    boolean splintery();

}
