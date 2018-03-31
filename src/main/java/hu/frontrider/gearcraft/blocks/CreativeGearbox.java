package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.api.PoweredBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class CreativeGearbox extends Block implements PoweredBlock {

    public CreativeGearbox(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {

    }

    @Override
    public int tickRate(World worldIn) {
        return 20;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }

    @Override
    public int getLevel(IBlockState blockState, BlockPos pos, World world) {
        return 15;
    }

    @Override
    public IBlockState setLevel(IBlockState blockstate, BlockPos pos, World world, int level) {
        return blockstate;
    }
}
