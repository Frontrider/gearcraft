package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.GearCraft;
import hu.frontrider.gearcraft.api.IPoweredBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class Magnet extends Block implements TooltippedBlock {
    public Magnet() {
        super(Material.IRON, MapColor.GOLD);
        setRegistryName(GearCraft.MODID, "stone_magnet");
        setUnlocalizedName("stone_magnet");
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return super.createBlockState();
    }

    @Override
    public void observedNeighborChange(IBlockState blockState, World world, BlockPos pos, Block block, BlockPos pos1) {
        if (world.isBlockIndirectlyGettingPowered(pos) >0 || world.isBlockPowered(pos)) {
            final BlockPos down = pos.down();
            final IBlockState bottom = world.getBlockState(down);
            if (bottom.getBlock() instanceof IPoweredBlock) {
                if (((IPoweredBlock) bottom.getBlock()).getPower(world, down) > 0) {
                    final List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos.getX() - 10, pos.getY() - 10, pos.getZ() - 10, pos.getX() + 10, pos.getY() + 10, pos.getZ() + 10));
                    items.forEach(entityItem -> entityItem.setPositionAndUpdate(pos.getX(), pos.getY() + 1.5, pos.getZ()));
                }
            }
        }
    }

    @Override
    public void setTooltip(List<String> tooltip) {
        tooltip.add("area: 20x20x20");
        tooltip.add("Give it a redstone signal to attract items");
    }
}
