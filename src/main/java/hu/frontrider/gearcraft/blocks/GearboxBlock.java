package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.api.IPoweredBlock;
import hu.frontrider.gearcraft.registry.TierRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static hu.frontrider.gearcraft.util.EnergyHelper.getInvertedTargetPower;
import static hu.frontrider.gearcraft.util.EnergyHelper.getTargetPower;

public class GearboxBlock extends BlockBase implements IPoweredBlock,TooltippedBlock {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool INVERTED = PropertyBool.create("inverted");

    public GearboxBlock(TierRegistry.Tier tier, String tag) {
        super(tier,"gearbox",tag);
    }

    public GearboxBlock(TierRegistry.Tier tier) {
        this(tier, null);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, INVERTED);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean isFullCube(IBlockState blockState) {
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState()
                .withProperty(FACING, Objects.requireNonNull(getFacing(meta)))
                .withProperty(INVERTED, (meta & 8) > 0);
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(FACING).getIndex();
        if (state.getValue(INVERTED)) {
            i |= 8;
        }
        return i;
    }

    public static EnumFacing getFacing(int p_getFacing_0_) {
        int i = p_getFacing_0_ & 7;
        return i > 5 ? null : EnumFacing.getFront(i);
    }

    public IBlockState withRotation(IBlockState p_withRotation_1_, Rotation p_withRotation_2_) {
        return p_withRotation_1_.withProperty(FACING, p_withRotation_2_.rotate(p_withRotation_1_.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState p_withMirror_1_, Mirror p_withMirror_2_) {
        return p_withMirror_1_.withRotation(p_withMirror_2_.toRotation(p_withMirror_1_.getValue(FACING)));
    }

    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float p_getStateForPlacement_4_, float p_getStateForPlacement_5_, float p_getStateForPlacement_6_, int p_getStateForPlacement_7_, EntityLivingBase entityLivingBase) {
        return this.getDefaultState()
                .withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, entityLivingBase))
                .withProperty(INVERTED, entityLivingBase.isSneaking());
    }

    @Override
    public int getStrength(IBlockAccess iBlockAccess, BlockPos blockPos) {

        IBlockState state = iBlockAccess.getBlockState(blockPos);
        boolean inverted = state.getValue(INVERTED);
        EnumFacing facing = state.getValue(FACING);

        if (inverted) {
            int required = getTargetPower(iBlockAccess, blockPos.offset(facing), facing,tier.power,getClass());
            int total = getInvertedTargetPower(iBlockAccess, blockPos, facing,tier.power,getClass());

            return total >= required ? total : 0;
        } else {

            int total = getTargetPower(iBlockAccess, blockPos.offset(facing), facing,tier.power,getClass());
            int required = getInvertedTargetPower(iBlockAccess, blockPos, facing,true,tier.power,getClass());
            return total >= required ? total : 0;
        }
    }

    @Override
    public int getPower(IBlockAccess iBlockAccess, BlockPos blockPos) {
        return getStrength(iBlockAccess, blockPos) > 0 ? 4 : 0;
    }

    @Override
    public boolean isValidSide(IBlockAccess access, BlockPos pos, EnumFacing targetFacing) {
        IBlockState state = access.getBlockState(pos);
        boolean inverted = state.getValue(INVERTED);
        EnumFacing facing = state.getValue(FACING);
        if (inverted) {
            return facing == targetFacing;
        } else {
            return facing != targetFacing;
        }
    }

    @Override
    public void observedNeighborChange(IBlockState blockState, World world, BlockPos blockPos, Block block, BlockPos blockPos1) {
        world.scheduleUpdate(blockPos, this, 30);
    }

    @Override
    public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
        world.notifyNeighborsOfStateChange(blockPos, this, true);
    }

    @Override
    public void setTooltip(List<String> tooltip) {
        tooltip.add("Power level: "+tier.power);
    }
}
