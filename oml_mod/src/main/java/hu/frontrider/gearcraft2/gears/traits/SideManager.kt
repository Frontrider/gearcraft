package hu.frontrider.gearcraft.gears.traits

import net.minecraft.util.EnumFacing

class SideManager {

    fun getSidesForTop(facing: EnumFacing): List<EnumFacing> {
        val ordinal = facing.ordinal
        return EnumFacing.values().filter {
            it.ordinal != ordinal
        }

    }
}