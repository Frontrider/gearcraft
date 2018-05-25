package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.GearCraft;
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

import java.util.Objects;
import java.util.Random;

public class GearboxBlock extends Block implements IPoweredBlock {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool INVERTED = PropertyBool.create("inverted");

    private final TierRegistry.Tier tier;

    public GearboxBlock(TierRegistry.Tier tier, String tag) {
        super(tier.material, tier.mapColor);
        TierRegistry.Tier.setBlock(this, tier);
        String suffix = "_gearbox";
        if (tag != null)
            suffix += "_" + tag;
        setRegistryName(GearCraft.MODID, tier.name + suffix);
        setUnlocalizedName(tier.name + "_gearbox");
        this.tier = tier;
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
            return getInvertedTargetPower(iBlockAccess, blockPos, facing);
        } else {
            return getOnSide(iBlockAccess, blockPos, facing);
        }
    }

    @Override
    public int getPower(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState leftBlock) {
        if (getStrength(iBlockAccess, blockPos) >= tier.power) {
            return 4;
        } else {
            return 0;
        }
    }

    private int getOnSide(IBlockAccess iBlockAccess, BlockPos blockPos, EnumFacing facing) {

        switch (facing) {
            case SOUTH:

                return getTargetStrength(iBlockAccess, blockPos.south(), EnumFacing.NORTH);
            case NORTH:
                return getTargetStrength(iBlockAccess, blockPos.north(), EnumFacing.SOUTH);
            case WEST:
                return getTargetStrength(iBlockAccess, blockPos.west(), EnumFacing.WEST);
            case EAST:
                return getTargetStrength(iBlockAccess, blockPos.east(), EnumFacing.EAST);
            case DOWN:
                return getTargetStrength(iBlockAccess, blockPos.down(), EnumFacing.UP);
            case UP:
                return getTargetStrength(iBlockAccess, blockPos.up(), EnumFacing.DOWN);
        }
        return 0;
    }

    private int getTargetStrength(IBlockAccess iBlockAccess, BlockPos pos, EnumFacing side) {
        IBlockState targetState = iBlockAccess.getBlockState(pos);
        Block targetBlock = targetState.getBlock();
        if (targetBlock instanceof IPoweredBlock) {
            if (((IPoweredBlock) targetBlock).getPower(iBlockAccess, pos, targetState) > 0
                    && ((IPoweredBlock) targetBlock).isValidSide(iBlockAccess, pos, side)) {
                int targetPower = ((IPoweredBlock) targetBlock).getStrength(iBlockAccess, pos);
                if (targetPower >= tier.power) {
                    return tier.power;
                }
            }
        }
        return 0;
    }

    private int getInvertedTargetStrenght() {
        return 0;
    }

    private int getInvertedTargetPower(IBlockAccess iBlockAccess, BlockPos blockPos, EnumFacing facing) {
        int required;
        int total;
        switch (facing) {
            case SOUTH:
                required = getOnSide(iBlockAccess, blockPos, facing);
                total = getTargetStrength(iBlockAccess, blockPos.south(), EnumFacing.NORTH)
                        + getTargetStrength(iBlockAccess, blockPos.west(), EnumFacing.EAST)
                        + getTargetStrength(iBlockAccess, blockPos.east(), EnumFacing.WEST)
                        + getTargetStrength(iBlockAccess, blockPos.down(), EnumFacing.UP)
                        + getTargetStrength(iBlockAccess, blockPos.up(), EnumFacing.DOWN);
                return total >= required ? required : 0;
            case NORTH:
                required = getOnSide(iBlockAccess, blockPos, facing);
                total = getTargetStrength(iBlockAccess, blockPos.south(), EnumFacing.NORTH)
                        + getTargetStrength(iBlockAccess, blockPos.west(), EnumFacing.EAST)
                        + getTargetStrength(iBlockAccess, blockPos.east(), EnumFacing.WEST)
                        + getTargetStrength(iBlockAccess, blockPos.down(), EnumFacing.UP)
                        + getTargetStrength(iBlockAccess, blockPos.up(), EnumFacing.DOWN);
                return total >= required ? required : 0;
            case WEST:
                required = getOnSide(iBlockAccess, blockPos, facing);
                total = getTargetStrength(iBlockAccess, blockPos.south(), EnumFacing.NORTH)
                        + getTargetStrength(iBlockAccess, blockPos.north(), EnumFacing.SOUTH)
                        + getTargetStrength(iBlockAccess, blockPos.east(), EnumFacing.WEST)
                        + getTargetStrength(iBlockAccess, blockPos.down(), EnumFacing.UP)
                        + getTargetStrength(iBlockAccess, blockPos.up(), EnumFacing.DOWN);
                return total >= required ? required : 0;

            case EAST:
                required = getOnSide(iBlockAccess, blockPos, facing);
                total = getTargetStrength(iBlockAccess, blockPos.south(), EnumFacing.NORTH)
                        + getTargetStrength(iBlockAccess, blockPos.west(), EnumFacing.EAST)
                        + getTargetStrength(iBlockAccess, blockPos.north(), EnumFacing.SOUTH)
                        + getTargetStrength(iBlockAccess, blockPos.down(), EnumFacing.UP)
                        + getTargetStrength(iBlockAccess, blockPos.up(), EnumFacing.DOWN);
                return total >= required ? required : 0;
            case DOWN:
                required = getOnSide(iBlockAccess, blockPos, facing);
                total = getTargetStrength(iBlockAccess, blockPos.south(), EnumFacing.NORTH)
                        + getTargetStrength(iBlockAccess, blockPos.west(), EnumFacing.EAST)
                        + getTargetStrength(iBlockAccess, blockPos.north(), EnumFacing.SOUTH)
                        + getTargetStrength(iBlockAccess, blockPos.east(), EnumFacing.WEST)
                        + getTargetStrength(iBlockAccess, blockPos.up(), EnumFacing.DOWN);
                return total >= required ? required : 0;
            case UP:
                required = getOnSide(iBlockAccess, blockPos, facing);
                total = getTargetStrength(iBlockAccess, blockPos.south(), EnumFacing.NORTH)
                        + getTargetStrength(iBlockAccess, blockPos.west(), EnumFacing.EAST)
                        + getTargetStrength(iBlockAccess, blockPos.north(), EnumFacing.SOUTH)
                        + getTargetStrength(iBlockAccess, blockPos.east(), EnumFacing.WEST)
                        + getTargetStrength(iBlockAccess, blockPos.down(), EnumFacing.UP);
                return total >= required ? required : 0;

        }
        return 0;
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
        world.scheduleUpdate(blockPos, this, 10);
    }

    @Override
    public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
        world.notifyNeighborsOfStateChange(blockPos, this, true);
    }
}
