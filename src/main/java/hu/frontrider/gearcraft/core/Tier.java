package hu.frontrider.gearcraft.core;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class Tier {

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
