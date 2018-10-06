package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.registry.TierRegistry
import net.minecraft.block.Block

import hu.frontrider.gearcraft.GearCraft.MODID

abstract class BlockBase @JvmOverloads constructor(protected var tier: TierRegistry.Tier, blockName: String, tag: String? = null) : Block(tier.material, tier.mapColor) {

    init {
        var suffix = "_$blockName"
        if (tag != null)
            suffix += "_$tag"
        setRegistryName(MODID, "${tier.name}$suffix")
        unlocalizedName = "$MODID.${tier.name}$suffix"
        TierRegistry.Tier.setBlock(this, tier)
    }
}
