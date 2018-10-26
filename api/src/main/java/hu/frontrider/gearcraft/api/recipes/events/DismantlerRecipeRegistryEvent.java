package hu.frontrider.gearcraft.api.recipes.events;

import hu.frontrider.gearcraft.api.recipes.IDismantlerRecipe;
import hu.frontrider.gearcraft.api.GearcraftRegistryEvent;
import java.util.List;

/**
 * Fired when the mod is ready to create new recipes recipes.
 * */
public class DismantlerRecipeRegistryEvent extends GearcraftRegistryEvent<IDismantlerRecipe> {
    public DismantlerRecipeRegistryEvent(List<IDismantlerRecipe> recipeList) {
        super(recipeList);
    }
}
