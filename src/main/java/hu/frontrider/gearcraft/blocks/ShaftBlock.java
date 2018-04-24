package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.GearCraft;
import hu.frontrider.gearcraft.api.PoweredBlock;
import hu.frontrider.gearcraft.core.Tier;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class ShaftBlock extends Block implements PoweredBlock {

    public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class);
    public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 3);

    /*
    static final AxisAlignedBB alignedX = new AxisAlignedBB();
    static final AxisAlignedBB alignedY = new AxisAlignedBB();
    static final AxisAlignedBB alignedZ = new AxisAlignedBB();*/

    private final Tier tier;

    public ShaftBlock(Tier tier) {
        super(tier.material, tier.mapColor);
        Tier.setBlock(this, tier);
        setRegistryName(GearCraft.MODID, tier.name + "_shaft");
        setUnlocalizedName(tier.name + "_shaft");
        this.tier = tier;
    }

    @Override
    public boolean isFullBlock(IBlockState p_isFullBlock_1_) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState p_isOpaqueCube_1_) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess p_getBoundingBox_2_, BlockPos p_getBoundingBox_3_) {
        return super.getBoundingBox(blockState, p_getBoundingBox_2_, p_getBoundingBox_3_);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.getValue(AXIS)) {
                    case X:
                        return state.withProperty(AXIS, EnumFacing.Axis.Z);
                    case Z:
                        return state.withProperty(AXIS, EnumFacing.Axis.X);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override
    public boolean eventReceived(IBlockState p_eventReceived_1_, World p_eventReceived_2_, BlockPos p_eventReceived_3_, int p_eventReceived_4_, int p_eventReceived_5_) {
        System.out.println("Shaft updated");
        return false;
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        if (meta > 15) {
            meta = 15;
        }
        EnumFacing.Axis axis = EnumFacing.Axis.X;
        int rotationBits = meta & 0x3;
        int powerBits = (meta >> 2) & 0x3;

        switch (rotationBits) {
            case 1:
                axis = EnumFacing.Axis.Y;
                break;
            case 2:
                axis = EnumFacing.Axis.Z;
                break;
        }
        return this.getDefaultState().withProperty(AXIS, axis).withProperty(POWER, powerBits);
    }

    @Override
    public int getMetaFromState(IBlockState blockState) {
        int i = blockState.getValue(AXIS).ordinal();
        int power = blockState.getValue(POWER);
        i += power << 2;
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{AXIS, POWER});
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState p_getSilkTouchDrop_1_) {
        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    public IBlockState getStateForPlacement(World p_getStateForPlacement_1_, BlockPos p_getStateForPlacement_2_, EnumFacing p_getStateForPlacement_3_, float p_getStateForPlacement_4_, float p_getStateForPlacement_5_, float p_getStateForPlacement_6_, int p_getStateForPlacement_7_, EntityLivingBase p_getStateForPlacement_8_) {
        return super.getStateForPlacement(p_getStateForPlacement_1_, p_getStateForPlacement_2_, p_getStateForPlacement_3_, p_getStateForPlacement_4_, p_getStateForPlacement_5_, p_getStateForPlacement_6_, p_getStateForPlacement_7_, p_getStateForPlacement_8_).withProperty(AXIS, p_getStateForPlacement_3_.getAxis());
    }


    @Override
    public int getPower(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState leftBlock) {
        return iBlockAccess.getBlockState(blockPos).getValue(POWER);
    }

    @Override
    public int getStrength(IBlockAccess iBlockAccess, BlockPos blockPos) {
        return tier.power;
    }

    @Override
    public boolean isValidSide(IBlockAccess access, BlockPos pos, EnumFacing facing) {

        IBlockState blockState = access.getBlockState(pos);

        EnumFacing.Axis axis = blockState.getValue(AXIS);
        switch (axis) {
            case Y:
                if (facing == EnumFacing.UP | facing == EnumFacing.DOWN)
                    return true;
                break;
            case Z:
                if (facing == EnumFacing.NORTH | facing == EnumFacing.SOUTH)
                    return true;
                break;
            case X:
                if (facing == EnumFacing.WEST | facing == EnumFacing.EAST)
                    return true;
                break;
        }

        return false;
    }

    private void update(IBlockState blockState, World world, BlockPos blockPos) {
        EnumFacing.Axis axis = blockState.getValue(AXIS);
        EnumFacing leftSide;
        EnumFacing rightSide;
        switch (axis) {
            case Y:
                leftSide = EnumFacing.DOWN;
                rightSide = EnumFacing.UP;
                updateBlockState(world, blockPos.up(), blockPos.down(), blockState, blockPos, leftSide, rightSide);
                break;
            case Z:
                leftSide = EnumFacing.SOUTH;
                rightSide = EnumFacing.NORTH;
                updateBlockState(world, blockPos.north(), blockPos.south(), blockState, blockPos, leftSide, rightSide);
                break;
            case X:
                leftSide = EnumFacing.WEST;
                rightSide = EnumFacing.EAST;
                updateBlockState(world, blockPos.east(), blockPos.west(), blockState, blockPos, leftSide, rightSide);
                break;
        }
    }

    private void updateBlockState(World world, BlockPos left, BlockPos right, IBlockState thiz, BlockPos thizPos, EnumFacing leftSide, EnumFacing rightSide) {
        IBlockState leftBlock = world.getBlockState(left);
        IBlockState rightBlock = world.getBlockState(right);
        IBlockState thizState = world.getBlockState(thizPos);

        Block poweredLeft = leftBlock.getBlock();
        Block poweredRight = rightBlock.getBlock();

        int rightPower = 0;
        int leftPower = 0;
        int leftStrength = 0;
        int rightStrength = 0;
        boolean isLeftValid = false;
        boolean isRightValid = false;
        if (poweredLeft instanceof PoweredBlock) {
            isLeftValid = ((PoweredBlock) poweredLeft).isValidSide(world, left, leftSide);
            if (isLeftValid)
                leftPower = ((PoweredBlock) poweredLeft).getPower(world, left, leftBlock);
            leftStrength = ((PoweredBlock) poweredLeft).getStrength(world, left);
        }
        if (poweredRight instanceof PoweredBlock) {
            isRightValid = ((PoweredBlock) poweredRight).isValidSide(world, right, rightSide);
            if (isRightValid)
                rightPower = ((PoweredBlock) poweredRight).getPower(world, right, rightBlock);
            rightStrength = ((PoweredBlock) poweredRight).getStrength(world, right);
        }

        int difference = Math.abs(leftPower - rightPower);
        if ((difference == 1 || difference == 0) && (leftPower != 0 & rightPower != 0)) {
            breakAndNotify(world, thizPos);
        } else if (leftPower > rightPower
                && leftStrength >= tier.power
                && rightStrength <= tier.power) {
            updateAndNotify(world, thizState.withProperty(POWER, leftPower - 1), thizPos);
        } else if (leftPower < rightPower
                && rightStrength >= tier.power
                && leftStrength <= tier.power) {
            updateAndNotify(world, thizState.withProperty(POWER, rightPower - 1), thizPos);
        }
        if (rightPower == 0
                && leftPower > 1
                && leftStrength >= tier.power) {
            updateAndNotify(world, thizState.withProperty(POWER, leftPower - 1), thizPos);
        }
        if (leftPower == 0
                && rightPower > 1
                && rightStrength >= tier.power) {
            updateAndNotify(world, thizState.withProperty(POWER, rightPower - 1), thizPos);
        }
        if (leftPower == 1 & !isRightValid || rightPower == 1 & !isLeftValid) {
            breakAndNotify(world, thizPos);
        }
        if (leftPower == 0 && rightPower == 0) {
            updateAndNotify(world, thizState.withProperty(POWER, 0), thizPos);
        }
    }

    private int getPowerLevel(IBlockState blockState, IBlockAccess world, BlockPos pos) {
        return ((PoweredBlock) blockState.getBlock()).getStrength(world, pos);
    }

    private void updateAndNotify(World world, IBlockState thiz, BlockPos pos) {
        world.setBlockState(pos, thiz);
    }

    private void breakAndNotify(World world, BlockPos pos) {
        world.destroyBlock(pos, true);
    }

    @Override
    public void neighborChanged(IBlockState blockState, World world, BlockPos pos, Block p_neighborChanged_4_, BlockPos p_neighborChanged_5_) {
        update(blockState, world, pos);
    }

    @Override
    public void observedNeighborChange(IBlockState blockState, World world, BlockPos blockPos, Block block, BlockPos neighbourPos) {
        update(blockState, world, blockPos);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase p_onBlockPlacedBy_4_, ItemStack p_onBlockPlacedBy_5_) {
        update(blockState, world, blockPos);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState blockState, World world, BlockPos blockPos, Random random) {
        if (this.getPowerLevel(blockState, world, blockPos) > 0) {
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
                    (blockPos.getX() + random.nextFloat() - 0.5F) * 0.2D,
                    (blockPos.getY() + random.nextFloat() - 0.5F) * 0.2D,
                    (blockPos.getZ() + random.nextFloat() - 0.5F) * 0.2D,
                    0D, 0D, 0D);
        }
    }
}
