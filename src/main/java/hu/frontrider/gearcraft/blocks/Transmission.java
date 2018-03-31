package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.api.PoweredBlock;
import hu.frontrider.gearcraft.api.ShaftBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;


public class Transmission extends Block {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public Transmission(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,FACING);
    }
    @Override
    public IBlockState getStateForPlacement(World p_getStateForPlacement_1_, BlockPos p_getStateForPlacement_2_, EnumFacing p_getStateForPlacement_3_, float p_getStateForPlacement_4_, float p_getStateForPlacement_5_, float p_getStateForPlacement_6_, int p_getStateForPlacement_7_, EntityLivingBase p_getStateForPlacement_8_) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(p_getStateForPlacement_2_, p_getStateForPlacement_8_));
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState blockState, Random random) {
        System.out.println("ticking");
        EnumFacing facing = blockState.getValue(FACING);
        switch (facing) {
            case UP: {
                BlockPos up = pos.up();
                IBlockState target = world.getBlockState(up);
                Block block = target.getBlock();
                int power;
                BlockPos gearboxPos = pos.down();
                IBlockState gearboxState = world.getBlockState(gearboxPos);
                Block gearbox = gearboxState.getBlock();
                if (gearbox instanceof PoweredBlock) {
                    power = ((PoweredBlock) gearbox).getPower(gearboxState, gearboxPos, world);
                } else {
                    break;
                }

                if (block instanceof ShaftBlock) {
                    int range = ((ShaftBlock) block).getRange();
                    for (int i = 0; i < range; i++) {
                        up = up.up();

                        block = world.getBlockState(up).getBlock();
                        if (block instanceof ShaftBlock) {
                            if (((ShaftBlock) block).getRange() != range) {
                                break;
                            }
                        } else {
                            if (block instanceof PoweredBlock) {
                                IBlockState shaftstate = world.getBlockState(up);
                                IBlockState state = ((PoweredBlock) block).pushPower(shaftstate, up, world, power);
                                world.setBlockState(up, state);
                            } else {
                                break;
                            }
                        }

                    }
                }else{
                    if (block instanceof PoweredBlock) {
                        IBlockState state = ((PoweredBlock) block).pushPower(target, up, world, power);
                        world.setBlockState(up, state);
                    }
                }
                break;
            }
            case DOWN: {

            }
            case EAST: {

            }
            case WEST: {

            }
            case NORTH: {

            }
            case SOUTH: {

            }
        }
    }

    @Override
    public int tickRate(World p_tickRate_1_) {
        return 20;
    }

    @Override
    public void onBlockAdded(World world, BlockPos blockPos, IBlockState iBlockState) {
        world.scheduleUpdate(blockPos, this, this.tickRate(world));
    }


    public IBlockState getStateFromMeta(int p_getStateFromMeta_1_) {
        return this.getDefaultState().withProperty(FACING, getFacing(p_getStateFromMeta_1_));
    }

    public int getMetaFromState(IBlockState p_getMetaFromState_1_) {
        return p_getMetaFromState_1_.getValue(FACING).getIndex();
    }

    @Nullable
    public static EnumFacing getFacing(int p_getFacing_0_) {
        int i = p_getFacing_0_ & 7;
        return i > 5 ? null : EnumFacing.getFront(i);
    }
}
