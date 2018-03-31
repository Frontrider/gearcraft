package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.api.ShaftBlock;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class Shaft extends BlockRotatedPillar implements ShaftBlock {

    public Shaft(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    @Override
    public int getRange() {
        return 5;
    }
}
