package hu.frontrider.gearcraft.registry;

import hu.frontrider.gearcraft.core.Tier;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class TierRegistry {

    public static final Tier wooden = new Tier(
            8,
            1,
            "wooden",
            "axe",
            0,
            .2f,
            SoundType.WOOD,
            Material.WOOD,
            MapColor.BROWN);

}
