package hu.frontrider.gearcraft2.blocks.power

import hu.frontrider.gearcraft2.gears.tooltip.InvertedToolTip
import hu.frontrider.gearcraft2.gears.tooltip.MultiTooltip
import hu.frontrider.gearcraft.gears.tooltip.PowerTooltip
import hu.frontrider.gearcraft2.gears.traits.gearbox.GearboxPower
import hu.frontrider.gearcraft2.api.traits.ITooltipped
import hu.frontrider.gearcraft2.api.traits.power.IGearPowered
import hu.frontrider.gearcraft2.blocks.InvertedDirectionalBlockBase
import net.minecraft.block.Block

class BlockGearbox(builder: Block.Builder,val power:Int
) : InvertedDirectionalBlockBase(builder),
        IGearPowered by GearboxPower(power),
        ITooltipped by MultiTooltip(PowerTooltip(power), InvertedToolTip("gearcraft.gearbox.invertmessage")) {


}