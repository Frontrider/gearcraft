package hu.frontrider.gearcraft.registry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class TierRegistry {


    public static final Tier WOODEN = new Tier(
            8,
            1,
            "wooden",
            "axe",
            0,
            .2f,
            SoundType.WOOD,
            Material.WOOD,
            MapColor.BROWN);

    public static final Tier IRON = new Tier(
            16,
            2,
            "iron",
            "pickaxe",
            2,
            .4f,
            SoundType.METAL,
            Material.IRON,
            MapColor.IRON
    );

    public static final Tier[] TIERS = {
            WOODEN, IRON
    };

    public static class Tier {

        public final int power;
        public final int resistance;
        public final String name;
        public final String tool;
        public final int miningLevel;
        public final float hardness;
        public final SoundType soundType;
        public final Material material;
        public final MapColor mapColor;

        public Tier(int power, int resistance, String name, String tool, int miningLevel, float hardness, SoundType soundType, Material material, MapColor mapColor) {
            this.power = power;
            this.resistance = resistance;
            this.name = name;
            this.tool = tool;
            this.miningLevel = miningLevel;
            this.hardness = hardness;
            this.soundType = soundType;
            this.material = material;
            this.mapColor = mapColor;
        }

        public static void setBlock(Block block, Tier tier) {
            block.setHarvestLevel(tier.tool, tier.miningLevel);
            block.setHardness(tier.hardness);
            block.setResistance(tier.resistance);
        }
    }

}
