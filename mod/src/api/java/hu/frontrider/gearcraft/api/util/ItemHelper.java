package hu.frontrider.gearcraft.api.util;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Objects;

public class ItemHelper {

    public static String convertToOreName(ItemStack itemStack) {

        ResourceLocation registryName = itemStack.getItem().getRegistryName();
        String oreName = OreDictionary.getOreName(OreDictionary.getOreID(Objects.requireNonNull(registryName).toString()));

        if (oreName.equals("Unknown")) {
            return registryName.toString();
        } else {
            return oreName;
        }
    }

    public static ItemStack convertNameToItemStack(String name) {
        if (name.startsWith("ore:")) {
            return OreDictionary.getOres(name).get(0);
        } else {
            Item value = GameRegistry.findRegistry(Item.class).getValue(new ResourceLocation(name));
            if (value != null)
                return new ItemStack(value, 1);
        }
        return null;
    }
}
