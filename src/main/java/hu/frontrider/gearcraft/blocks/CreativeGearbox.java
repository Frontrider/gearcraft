package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.api.IPoweredBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.List;

import static hu.frontrider.gearcraft.GearCraft.MODID;

public class CreativeGearbox  extends Block implements IPoweredBlock,TooltippedBlock {
    private static final String blockName = "creative_gearbox";

    public CreativeGearbox() {
        super(Material.ROCK,MapColor.BLACK);
        setBlockUnbreakable();
        setRegistryName(MODID,blockName);
        setUnlocalizedName(blockName);
    }

    @Override
    public int getPower(IBlockAccess iBlockAccess, BlockPos blockPos) {
        return 4;
    }

    @Override
    public int getStrength(IBlockAccess iBlockAccess, BlockPos blockPos) {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isValidSide(IBlockAccess access, BlockPos pos, EnumFacing facing) {
        return true;
    }

    @Override
    public void setTooltip(List<String> tooltip) {
        tooltip.add("Infinite power");
        tooltip.add("Creative Only");
    }
}
