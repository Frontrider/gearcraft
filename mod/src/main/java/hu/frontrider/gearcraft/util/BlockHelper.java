package hu.frontrider.gearcraft.util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHelper {

    public static boolean isPowered(World world, BlockPos pos) {
        return world.isBlockIndirectlyGettingPowered(pos) > 0 || world.isBlockPowered(pos);
    }

    public static EnumFacing getFacing(int p_getFacing_0_) {
        int i = p_getFacing_0_ & 7;
        return i > 5 ? null : EnumFacing.getFront(i);
    }

}
