package hu.frontrider.gearcraft.api.dismantler;

import hu.frontrider.gearcraft.api.util.ItemHelper;
import hu.frontrider.gearcraft.api.util.Pair;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DismantlerRecipeRegistry {

    private static Map<String, Pair<String,Integer>[]> recipes;

    static {
        recipes = new HashMap<>();
    }

    public DismantlerRecipeRegistry() {

    }

    public static void addRecipe(DismantlerRecipe recipe) {

        Pair[] results = new Pair[recipe.resultnames.length];

        for (int i = 0; i < recipe.resultnames.length; i++) {
            String resultname = recipe.resultnames[i];
            Integer resultCount = recipe.resultCounts[i];
            results[i] = new Pair<>(resultname, resultCount);
        }
        recipes.put(recipe.sourceName,results);
    }

    public static List<ItemStack> getResults(String name){
        List<ItemStack> results = new ArrayList<ItemStack>();
        Pair<String, Integer>[] pairs = recipes.get(name);
        for (Pair<String, Integer> pair : pairs) {
            String first = pair.getFirst();
            ItemStack itemStack = ItemHelper.convertNameToItemStack(first);
            if(itemStack != null){

            }
        }
        return results;
    }
    public static List<ItemStack> getResults(ItemStack itemStack){
        String oreName = ItemHelper.convertToOreName(itemStack);
        return getResults(oreName);
    }


    public void initRecipes() {

    }
}
