package hu.frontrider.gearcraft.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHelper {

    public static boolean isPowered(World world, BlockPos pos) {
        return world.isBlockIndirectlyGettingPowered(pos) > 0 || world.isBlockPowered(pos);
    }
}
