package hu.frontrider.gearcraft.api.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Used by the dismantler and alike as grinding balls, to work or improve outputs.
 */
public interface IGrindingBall {
    /**
     * Stores the default lists of bonus items, for reference. DO NOT TOUCH IT BEFORE POST INIT! ADD YOU OWN THERE!!
     * Can be modified at runtime.
     * <p>
     * The default keys:
     * <ul>
     *     <li>ores</li>
     *     <li>poorOres</li>
     * </ul>
     *
     * it's your responsibility to make sure that
     */
    public static Map<String, List<ItemStack>> defaultitemList = null;

    /**
     * Return the current grinding bonuses
     *
     * @param itemStack the stack of the grinding ball
     * @param key the grinding list that we want to access
     * @param random random
     * */
    public ItemStack[] getBonus(ItemStack itemStack,String key, Random random);
}

