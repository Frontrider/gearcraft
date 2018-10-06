package hu.frontrider.gearcraft.blocks;

import hu.frontrider.gearcraft.registry.TierRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Dismantler extends TieredPoweredDirectionalBlockBase {

    public Dismantler(TierRegistry.Tier tier) {
        this(tier, "");
    }

    public Dismantler(TierRegistry.Tier tier, String tag) {
        super(tier,"dismantler",tag);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {

    }
}
