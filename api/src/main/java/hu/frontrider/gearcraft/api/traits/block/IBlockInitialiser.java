package hu.frontrider.gearcraft.api.traits.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public interface IBlockInitialiser {
    void initBlock(Block block,
                   float resistance,
                   String name,
                   String tool,
                   int miningLevel,
                   float hardness,
                   SoundType soundType,
                   Material material,
                   MapColor mapColor);
}
