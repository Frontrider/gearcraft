package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.api.IPoweredBlock;
import hu.frontrider.gearcraft.registry.TierRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static hu.frontrider.gearcraft.api.BlockStateHelpers.FACING;
import static hu.frontrider.gearcraft.api.BlockStateHelpers.POWERED;
import static hu.frontrider.gearcraft.util.BlockHelper.isPowered;
import static net.minecraft.util.EnumFacing.DOWN;
import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class CoalGenerator extends BlockBase implements IPoweredBlock, TooltippedBlock {

    public CoalGenerator(TierRegistry.Tier tier) {
        this(tier, null);
    }

    public CoalGenerator(TierRegistry.Tier tier, String tag) {
        super(tier, "coal_generator", tag);
        setTickRandomly(true);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, POWERED, FACING);
    }

    @Override
    public void randomTick(World world, BlockPos pos, IBlockState blockState, Random random) {
        final Boolean power = blockState.getValue(POWERED);
        final BlockPos up = pos.up();
        final TileEntity tileEntity = world.getTileEntity(up);
        IItemHandler itemHandler = null;
        if (tileEntity != null) {
            if (tileEntity.hasCapability(ITEM_HANDLER_CAPABILITY, DOWN)) {
                itemHandler = tileEntity.getCapability(ITEM_HANDLER_CAPABILITY, DOWN);
            }
        }

        final List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(up.getX(), up.getY(), up.getZ(), up.getX() + 1, up.getY() + 1, up.getZ() + 1));
        for (Entity entity : entities) {
            if (entity.hasCapability(ITEM_HANDLER_CAPABILITY, DOWN)) {
                itemHandler = entity.getCapability(ITEM_HANDLER_CAPABILITY, DOWN);
            }
        }
        if (itemHandler == null) {
            world.setBlockState(pos, blockState.withProperty(POWERED, false));
            return;
        }

        if (power) {
            if (canItemsFuel(itemHandler, true) && random.nextBoolean()) {
                canItemsFuel(itemHandler, false);

            } else {
                world.setBlockState(pos, blockState.withProperty(POWERED, false));
            }
        } else {
            if (canItemsFuel(itemHandler, false)) {
                world.setBlockState(pos, blockState.withProperty(POWERED, true));
            }
        }
    }

    @Override
    public void observedNeighborChange(IBlockState iBlockState, World world, BlockPos pos, Block block, BlockPos blockPos) {
        if (isPowered(world, pos)) {
            if (!world.getBlockState(pos).getValue(POWERED))
                randomTick(world, pos, iBlockState, world.rand);
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(FACING).getIndex();
        if (state.getValue(POWERED)) {
            i |= 8;
        }
        return i;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState()
                .withProperty(FACING, Objects.requireNonNull(getFacing(meta)))
                .withProperty(POWERED, (meta & 8) > 0);
    }

    public static EnumFacing getFacing(int p_getFacing_0_) {
        int i = p_getFacing_0_ & 7;
        return i > 5 ? null : EnumFacing.getFront(i);
    }

    @Override
    public EnumPushReaction getMobilityFlag(IBlockState p_getMobilityFlag_1_) {
        return EnumPushReaction.BLOCK;
    }

    private static boolean canItemsFuel(IItemHandler itemHandler, boolean simulate) {
        final int slots = itemHandler.getSlots();
        for (int i = 0; i < slots; i++) {
            final ItemStack itemStack = itemHandler.getStackInSlot(i);
            final boolean itemEqual = itemStack.isItemEqual(new ItemStack(Items.COAL));
            if (itemEqual) {
                if (!simulate) {
                    itemStack.shrink(1);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int getPower(IBlockAccess iBlockAccess, BlockPos blockPos) {
        final IBlockState blockState = iBlockAccess.getBlockState(blockPos);
        return blockState.getValue(POWERED) ? 4 : 0;
    }

    @Override
    public int getStrength(IBlockAccess iBlockAccess, BlockPos blockPos) {
        return tier.power;
    }

    @Override
    public boolean isValidSide(IBlockAccess access, BlockPos pos, EnumFacing facing) {
        return facing == DOWN;
    }

    @Override
    public void setTooltip(List<String> tooltip) {
        tooltip.add("Put fuel into a chest above it.(minecraft works)");
        tooltip.add("Power level: " + tier.power);
    }

    public void onBlockAdded(World world, BlockPos pos, IBlockState blockState) {
        this.setDefaultFacing(world, pos, blockState);
    }

    private void setDefaultFacing(World world, BlockPos blockPos, IBlockState blockState) {
        if (!world.isRemote) {
            IBlockState blockState1 = world.getBlockState(blockPos.north());
            IBlockState blockState2 = world.getBlockState(blockPos.south());
            IBlockState blockState3 = world.getBlockState(blockPos.west());
            IBlockState blockState4 = world.getBlockState(blockPos.east());
            EnumFacing blockStateValue = blockState.getValue(FACING);
            if (blockStateValue == EnumFacing.NORTH && blockState1.isFullBlock() && !blockState2.isFullBlock()) {
                blockStateValue = EnumFacing.SOUTH;
            } else if (blockStateValue == EnumFacing.SOUTH && blockState2.isFullBlock() && !blockState1.isFullBlock()) {
                blockStateValue = EnumFacing.NORTH;
            } else if (blockStateValue == EnumFacing.WEST && blockState3.isFullBlock() && !blockState4.isFullBlock()) {
                blockStateValue = EnumFacing.EAST;
            } else if (blockStateValue == EnumFacing.EAST && blockState4.isFullBlock() && !blockState3.isFullBlock()) {
                blockStateValue = EnumFacing.WEST;
            }

            world.setBlockState(blockPos, blockState.withProperty(FACING, blockStateValue), 2);
        }
    }
}
