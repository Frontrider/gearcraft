package hu.frontrider.gearcraft2.api.recipes.events;

import hu.frontrider.gearcraft2.api.GearcraftRegistryEvent;
import hu.frontrider.gearcraft2.api.recipes.IDismantlerRecipe;

import java.util.List;

/**
 * Fired when the mod is ready to create new recipes recipes.
 * */
public class DismantlerRecipeRegistryEvent extends GearcraftRegistryEvent<IDismantlerRecipe> {
    public DismantlerRecipeRegistryEvent(List<IDismantlerRecipe> recipeList) {
        super(recipeList);
    }
}
