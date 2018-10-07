package hu.frontrider.gearcraft.api;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.util.EnumFacing;

public class BlockStateHelpers {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool POWERED = PropertyBool.create("powered");
    public static final PropertyBool INVERTED = PropertyBool.create("inverted");
    public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class);
    public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 3);
    public static final PropertyInteger SPIN = PropertyInteger.create("spin", 0, 15);
}
