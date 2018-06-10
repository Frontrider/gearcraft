package hu.frontrider.gearcraft.entities;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static net.minecraft.entity.monster.IMob.VISIBLE_MOB_SELECTOR;

public class EntityMechanicalGolem extends EntityGolem {
    protected static final DataParameter<Byte> PLAYER_CREATED = EntityDataManager.createKey(EntityIronGolem.class, DataSerializers.BYTE);
    /**
     * deincrements, and a distance-to-home check is done at 0
     */
    private int homeCheckTimer;
    private int attackTimer;
    private int holdRoseTick;

    public EntityMechanicalGolem(World worldIn) {
        super(worldIn);
        this.setSize(1.4F, 2.7F);

    }

    public static void registerFixesIronGolem(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityIronGolem.class);
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        //noinspection unchecked
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this,
                EntityLiving.class,
                10,
                false,
                true,
                (Predicate<EntityLiving>) entityLiving -> {
                    if (entityLiving != null) {
                        if (VISIBLE_MOB_SELECTOR.apply(entityLiving)) {
                            return !(entityLiving instanceof EntityCreeper);
                        }
                    }
                    return false;
                }));
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(PLAYER_CREATED, (byte) 0);
    }

    protected void updateAITasks() {
        super.updateAITasks();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }

    /**
     * Decrements the entity's air supply when underwater
     */
    protected int decreaseAirSupply(int air) {
        return air;
    }

    protected void collideWithEntity(Entity entityIn) {
        if (entityIn instanceof IMob && !(entityIn instanceof EntityCreeper) && this.getRNG().nextInt(20) == 0) {
            this.setAttackTarget((EntityLivingBase) entityIn);
        }

        super.collideWithEntity(entityIn);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.attackTimer > 0) {
            --this.attackTimer;
        }

        if (this.holdRoseTick > 0) {
            --this.holdRoseTick;
        }

        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0) {
            int i = MathHelper.floor(this.posX);
            int j = MathHelper.floor(this.posY - 0.20000000298023224D);
            int k = MathHelper.floor(this.posZ);
            IBlockState iblockstate = this.world.getBlockState(new BlockPos(i, j, k));

            if (iblockstate.getMaterial() != Material.AIR) {
                this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + ((double) this.rand.nextFloat() - 0.5D) * (double) this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + ((double) this.rand.nextFloat() - 0.5D) * (double) this.width, 4.0D * ((double) this.rand.nextFloat() - 0.5D), 0.5D, ((double) this.rand.nextFloat() - 0.5D) * 4.0D, Block.getStateId(iblockstate));
            }
        }
    }

    /**
     * Returns true if this entity can attack entities of the specified class.
     */
    public boolean canAttackClass(Class<? extends EntityLivingBase> cls) {
        return cls != EntityCreeper.class && super.canAttackClass(cls) && !EntityPlayer.class.isAssignableFrom(cls);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        this.attackTimer = 10;
        this.world.setEntityState(this, (byte) 4);
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (7 + this.rand.nextInt(15)));

        if (flag) {
            entityIn.motionY += 0.4000000059604645D;
            this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 4) {
            this.attackTimer = 10;
            this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.0F);
        } else if (id == 11) {
            this.holdRoseTick = 400;
        } else if (id == 34) {
            this.holdRoseTick = 0;
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    public void setHoldingRose(boolean p_70851_1_) {
        if (p_70851_1_) {
            this.holdRoseTick = 400;
            this.world.setEntityState(this, (byte) 11);
        } else {
            this.holdRoseTick = 0;
            this.world.setEntityState(this, (byte) 34);
        }
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_IRONGOLEM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRONGOLEM_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_IRON_GOLEM;
    }


    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause) {

        super.onDeath(cause);
    }

    @Override
    public String getCustomNameTag() {
        return "mechanical golem";
    }

    @Override
    public boolean getAlwaysRenderNameTag() {
        return true;
    }
}
