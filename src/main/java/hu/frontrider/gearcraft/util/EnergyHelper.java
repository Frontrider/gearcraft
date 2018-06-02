package hu.frontrider.gearcraft.util;

import hu.frontrider.gearcraft.api.IPoweredBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class EnergyHelper {

    public static int getTargetPower(IBlockAccess iBlockAccess, BlockPos pos, EnumFacing side, int target,Class toIgnore) {
        return getTargetPower(iBlockAccess, pos, side, target, toIgnore,false);
    }

    public static int getTargetPower(IBlockAccess iBlockAccess, BlockPos pos, EnumFacing side, int target,Class toIgnore, boolean ignoreStrength) {
        IBlockState targetState = iBlockAccess.getBlockState(pos);
        Block targetBlock = targetState.getBlock();

        if(toIgnore != null) {
            if(toIgnore.isInstance(targetBlock)) {
                return 0;
            }
        }

        if (targetBlock instanceof IPoweredBlock) {
            if ((((IPoweredBlock) targetBlock).getPower(iBlockAccess, pos) > 0
                    && ((IPoweredBlock) targetBlock).isValidSide(iBlockAccess, pos, side))
                    || ignoreStrength) {
                int targetPower = ((IPoweredBlock) targetBlock).getStrength(iBlockAccess, pos);
                if (targetPower >= target) {
                    return target;
                } else
                    return targetPower;
            }
        }
        return 0;
    }

    public static int getInvertedTargetPower(IBlockAccess iBlockAccess, BlockPos blockPos, EnumFacing facing, int target,Class toIgnore) {
        return getInvertedTargetPower(iBlockAccess, blockPos, facing, false, target,toIgnore);
    }

    public static int getInvertedTargetPower(IBlockAccess iBlockAccess, BlockPos blockPos, EnumFacing facing, boolean ignoreStrength, int target,Class toIgnore) {
        int total = 0;
        if (facing != EnumFacing.NORTH) {
            total += getTargetPower(iBlockAccess, blockPos.south(), EnumFacing.NORTH, target,toIgnore, ignoreStrength);
        }
        if (facing != EnumFacing.SOUTH) {
            total += getTargetPower(iBlockAccess, blockPos.north(), EnumFacing.SOUTH, target,toIgnore, ignoreStrength);
        }
        if (facing != EnumFacing.EAST) {
            total += getTargetPower(iBlockAccess, blockPos.west(), EnumFacing.EAST, target,toIgnore, ignoreStrength);
        }
        if (facing != EnumFacing.WEST) {
            total += getTargetPower(iBlockAccess, blockPos.east(), EnumFacing.WEST, target,toIgnore, ignoreStrength);
        }
        if (facing != EnumFacing.UP) {
            total += getTargetPower(iBlockAccess, blockPos.down(), EnumFacing.UP, target,toIgnore, ignoreStrength);
        }
        if (facing != EnumFacing.DOWN) {
            total += getTargetPower(iBlockAccess, blockPos.up(), EnumFacing.DOWN, target,toIgnore, ignoreStrength);
        }
        return total;
    }

}
