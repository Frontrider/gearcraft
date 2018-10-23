package hu.frontrider.gearcraft.api.dismantler;

import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Collections;
import java.util.List;

/**
 * Fired when the mod is ready to create new dismantler recipes.
 * */
public class DismantlerRecipeRegistryEvent extends Event {
    private List<DismantlerRecipe> recipeList;

    public DismantlerRecipeRegistryEvent(List<DismantlerRecipe> recipeList) {
        this.recipeList = recipeList;
    }

    public void registerRecipe(DismantlerRecipe recipe){
     recipeList.add(recipe);
    }

    public void registerAllRecipe(DismantlerRecipe... recipe){
        Collections.addAll(recipeList, recipe);
    }

}
