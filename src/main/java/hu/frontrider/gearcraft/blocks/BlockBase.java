package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.GearCraft;
import hu.frontrider.gearcraft.registry.TierRegistry;
import net.minecraft.block.Block;

public abstract class BlockBase extends Block {
    protected TierRegistry.Tier tier;

    public BlockBase(TierRegistry.Tier tier, String blockName, String tag) {
        super(tier.material, tier.mapColor);
        String suffix = "_" + blockName;
        if (tag != null)
            suffix += "_" + tag;
        setRegistryName(GearCraft.MODID, tier.name + suffix);
        setUnlocalizedName(tier.name + suffix);
        this.tier = tier;
        TierRegistry.Tier.setBlock(this, tier);
    }

    public BlockBase(TierRegistry.Tier tier, String blockName) {
        this(tier, blockName, null);
    }
}
