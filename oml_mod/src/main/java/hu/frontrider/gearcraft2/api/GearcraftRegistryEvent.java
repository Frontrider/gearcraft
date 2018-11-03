package hu.frontrider.gearcraft2.api;

import com.openmodloader.api.event.Event;

import java.util.Collections;
import java.util.List;

public abstract class GearcraftRegistryEvent<T> implements Event {
    private List<T> recipeList;

    public GearcraftRegistryEvent(List<T> recipeList) {
        this.recipeList = recipeList;
    }

    public void register(T recipe){
        recipeList.add(recipe);
    }

    public void register(T... recipe){
        Collections.addAll(recipeList, recipe);
    }

}