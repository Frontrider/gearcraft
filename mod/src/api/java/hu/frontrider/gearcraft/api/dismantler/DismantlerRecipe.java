package hu.frontrider.gearcraft.api.dismantler;

import hu.frontrider.gearcraft.api.util.ItemHelper;
import net.minecraft.item.ItemStack;

import java.util.Objects;

/**
 * Used as an intermediary while registering recipes.
 */
public class DismantlerRecipe {

    String sourceName;
    String[] resultnames;
    Integer[] resultCounts;

    DismantlerRecipe(ItemStack source, Object... results) {
        this(Objects.requireNonNull(source.getItem().getRegistryName()).toString(), results);
    }

    DismantlerRecipe(String source, Object... results) {
        resultnames = new String[results.length];
        resultCounts = new Integer[results.length];
        for (int i = 0; i < results.length; i++) {
            Object result = results[i];
            if (result instanceof String) {
                resultnames[i] = (String) result;
                resultCounts[i] = 1;
            }
            if (result instanceof ItemStack) {
                String oreName = ItemHelper.convertToOreName((ItemStack) result);
                resultnames[i] = oreName;
                resultCounts[i] = 1;
            }
        }
        sourceName = source;
    }
}
