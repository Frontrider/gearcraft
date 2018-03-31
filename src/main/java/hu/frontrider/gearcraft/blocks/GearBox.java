package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.api.PoweredBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class GearBox extends Block implements PoweredBlock {

    private static PropertyInteger power = PropertyInteger.create("power", 0, 15);

    public GearBox(Material blockMaterialIn, MapColor blockMapColorIn) {
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
        return new BlockStateContainer(this, power);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(power);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(power, meta);
    }

    @Override
    public int getLevel(IBlockState blockState, BlockPos pos, World world) {
        return blockState.getValue(power);
    }

    @Override
    public IBlockState setLevel(IBlockState blockstate, BlockPos pos, World world, int level) {
        if(level <15) {
            return this.getDefaultState().withProperty(power, level);
        }else {
            return this.getDefaultState().withProperty(power, 15);
        }
    }
}
