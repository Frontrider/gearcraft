package hu.frontrider.gearcraft2.api;

import net.minecraft.property.PropertyBoolean;
import net.minecraft.property.PropertyEnum;
import net.minecraft.property.PropertyFacing;
import net.minecraft.property.PropertyInteger;
import net.minecraft.util.math.Facing.Axis;


public class BlockStates {
    public static final PropertyFacing FACING = PropertyFacing.create("facing");
    public static final PropertyBoolean POWERED = PropertyBoolean.create("powered");
    public static final PropertyBoolean INVERTED = PropertyBoolean.create("inverted");
    public static final PropertyEnum<Axis> AXIS = PropertyEnum.create("axis", Axis.class);
    public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 3);
    public static final PropertyInteger POWER_BIG = PropertyInteger.create("power", 0, 15);
    public static final PropertyInteger SPIN = PropertyInteger.create("spin", 0, 15);
    public static final PropertyInteger RANGE = PropertyInteger.create("range", 0, 15);
}
