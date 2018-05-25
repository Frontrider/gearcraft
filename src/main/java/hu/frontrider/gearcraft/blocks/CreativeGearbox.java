package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.GearCraft;
import hu.frontrider.gearcraft.api.IPoweredBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class CreativeGearbox  extends Block implements IPoweredBlock {
    private static final String blockName = "creative_gearbox";

    public CreativeGearbox() {
        super(Material.ROCK,MapColor.BLACK);
        setBlockUnbreakable();
        setRegistryName(GearCraft.MODID, blockName);
        setUnlocalizedName(blockName);
    }

    @Override
    public int getPower(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState leftBlock) {
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
}
