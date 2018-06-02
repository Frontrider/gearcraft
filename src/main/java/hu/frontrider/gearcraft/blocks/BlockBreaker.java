package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.registry.TierRegistry;
import hu.frontrider.gearcraft.util.EnergyHelper;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Objects;

public class BlockBreaker extends BlockBase implements TooltippedBlock {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockBreaker(TierRegistry.Tier tier, String tag) {
        super(tier, "breaker", tag);
    }

    public BlockBreaker(TierRegistry.Tier tier) {
        this(tier, null);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
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
                .withProperty(FACING, Objects.requireNonNull(getFacing(meta)));
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    public static EnumFacing getFacing(int facing) {
        return facing > 5 ? null : EnumFacing.getFront(facing);
    }

    public IBlockState withRotation(IBlockState p_withRotation_1_, Rotation p_withRotation_2_) {
        return p_withRotation_1_.withProperty(FACING, p_withRotation_2_.rotate(p_withRotation_1_.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState p_withMirror_1_, Mirror p_withMirror_2_) {
        return p_withMirror_1_.withRotation(p_withMirror_2_.toRotation(p_withMirror_1_.getValue(FACING)));
    }

    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float p_getStateForPlacement_4_, float p_getStateForPlacement_5_, float p_getStateForPlacement_6_, int p_getStateForPlacement_7_, EntityLivingBase entityLivingBase) {
        return this.getDefaultState()
                .withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, entityLivingBase));
    }

    @Override
    public void observedNeighborChange(IBlockState blockState, World world, BlockPos blockPos, Block block, BlockPos blockPos1) {
         final EnumFacing value = blockState.getValue(FACING);
        final int targetPower = EnergyHelper.getInvertedTargetPower(world, blockPos, value, tier.power/4, null);
        if (targetPower >= tier.power/4) {
            final BlockPos offset = blockPos.offset(value.getOpposite());
            final IBlockState targetState = world.getBlockState(offset);
            final int harvestLevel = targetState.getBlock().getHarvestLevel(targetState);
            if (harvestLevel <= tier.miningLevel)
                world.destroyBlock(offset, true);
        }
    }

    @Override
    public void setTooltip(List<String> tooltip) {

    }

}
