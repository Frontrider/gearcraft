package hu.frontrider.gearcraft.blocks;

import com.google.common.base.Predicate;
import hu.frontrider.gearcraft.entities.EntityMechanicalGolem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static hu.frontrider.gearcraft.GearCraft.MODID;
import static hu.frontrider.gearcraft.GearCraft.creativeTab;

public class MechanicalPumpkin extends BlockPumpkin {

    private static final Predicate<IBlockState> PREDICATE = p_apply_1_ -> p_apply_1_ != null && (p_apply_1_.getBlock() instanceof MechanicalPumpkin);
    private BlockPattern golemPattern;

    public MechanicalPumpkin() {
        super();
        this.setRegistryName(MODID, "mecahnical_pumpkin");
        this.setCreativeTab(creativeTab);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.trySummon(worldIn, pos);
    }

    @Override
    public boolean canDispenserPlace(World worldIn, BlockPos pos) {
        return false;
    }

    private void trySummon(World worldIn, BlockPos pos) {
        BlockPattern.PatternHelper patternHelper = this.getGolemPattern().match(worldIn, pos);
        System.out.println("tries to summon");
        System.out.println("blockpattern$patternhelper = " + patternHelper);

        if (patternHelper != null) {
            for (int j = 0; j < this.getGolemPattern().getPalmLength(); ++j) {
                for (int k = 0; k < this.getGolemPattern().getThumbLength(); ++k) {
                    worldIn.setBlockState(patternHelper.translateOffset(j, k, 0).getPos(), Blocks.AIR.getDefaultState(), 2);
                }
            }

            BlockPos blockpos = patternHelper.translateOffset(1, 2, 0).getPos();
            EntityMechanicalGolem mechanicalGolem = new EntityMechanicalGolem(worldIn);
            mechanicalGolem.setLocationAndAngles((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.05D, (double) blockpos.getZ() + 0.5D, 0.0F, 0.0F);
            worldIn.spawnEntity(mechanicalGolem);

            for (EntityPlayerMP entityPlayerMP : worldIn.getEntitiesWithinAABB(EntityPlayerMP.class, mechanicalGolem.getEntityBoundingBox().grow(5.0D))) {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(entityPlayerMP, mechanicalGolem);
            }

            for (int j1 = 0; j1 < 120; ++j1) {
                worldIn.spawnParticle(EnumParticleTypes.SNOWBALL, (double) blockpos.getX() + worldIn.rand.nextDouble(), (double) blockpos.getY() + worldIn.rand.nextDouble() * 3.9D, (double) blockpos.getZ() + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
            }

            for (int k1 = 0; k1 < this.getGolemPattern().getPalmLength(); ++k1) {
                for (int l1 = 0; l1 < this.getGolemPattern().getThumbLength(); ++l1) {
                    BlockWorldState blockworldstate1 = patternHelper.translateOffset(k1, l1, 0);
                    worldIn.notifyNeighborsRespectDebug(blockworldstate1.getPos(), Blocks.AIR, false);
                }
            }
        }
    }

    protected BlockPattern getGolemPattern() {
        if (this.golemPattern == null) {
            this.golemPattern = FactoryBlockPattern
                    .start()
                    .aisle("~^~", "###", "~#~")
                    .where('^', BlockWorldState.hasState(PREDICATE))
                    .where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.IRON_BLOCK)))
                    .where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR)))
                    .build();
        }

        return this.golemPattern;
    }
}
