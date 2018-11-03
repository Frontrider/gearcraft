package hu.frontrider.gearcraft2.api.recipes.events;

import hu.frontrider.gearcraft2.api.GearcraftRegistryEvent;
import hu.frontrider.gearcraft2.api.recipes.ISawRecipe;

import java.util.List;

/**
 * Fired when the mod is ready to create new recipes recipes.
 * */
public class SawRecipeRegistryEvent extends GearcraftRegistryEvent<ISawRecipe> {
    public SawRecipeRegistryEvent(List<ISawRecipe> recipeList) {
        super(recipeList);
    }
}
