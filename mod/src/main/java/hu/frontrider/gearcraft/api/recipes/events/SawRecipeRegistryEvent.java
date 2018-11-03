package hu.frontrider.gearcraft.api.recipes.events;

import hu.frontrider.gearcraft.api.GearcraftRegistryEvent;
import hu.frontrider.gearcraft.api.recipes.ISawRecipe;

import java.util.List;

/**
 * Fired when the mod is ready to create new recipes recipes.
 * */
public class SawRecipeRegistryEvent extends GearcraftRegistryEvent<ISawRecipe> {
    public SawRecipeRegistryEvent(List<ISawRecipe> recipeList) {
        super(recipeList);
    }
}
