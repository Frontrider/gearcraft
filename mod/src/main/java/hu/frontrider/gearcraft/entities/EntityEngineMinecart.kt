package hu.frontrider.gearcraft.entities

import hu.frontrider.gearcraft.GearCraft
import net.minecraft.block.Block
import net.minecraft.block.BlockRailPowered
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityMinecart
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.DamageSource
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry


class EntityEngineMinecart(world: World) : EntityMinecart(world) {

    companion object {
        @GameRegistry.ObjectHolder("${GearCraft.MODID}:wooden_gearbox_dark_oak")
        lateinit var gearbox: Block

        @GameRegistry.ObjectHolder("${GearCraft.MODID}:tablet")
        lateinit var tablet: Item
    }

    private var fuel: Int = 0
    private var pushX: Double = 0.toDouble()
    private var pushZ: Double = 0.toDouble()
    private var forward = false
    private var run = true

    override fun getType(): EntityMinecart.Type {
        return EntityMinecart.Type.FURNACE
    }

    /**
     * Called to update the entity's position/logic.
     */
    override fun onUpdate() {
        super.onUpdate()

        if(run)
        if (this.fuel > 0) {
            --this.fuel
        } else {
            this.pushX = 0.0
            this.pushZ = 0.0
        }
    }

    /**
     * Get's the maximum speed for a minecart
     */
    override fun getMaximumSpeed(): Double {
        return 0.2
    }

    override fun killMinecart(source: DamageSource) {
        super.killMinecart(source)

        if (!source.isExplosion && this.world.gameRules.getBoolean("doEntityDrops")) {
            this.entityDropItem(ItemStack(gearbox, 1), 0.0f)
        }
    }

    override fun moveAlongTrack(pos: BlockPos, state: IBlockState) {
        super.moveAlongTrack(pos, state)


        var d0 = this.pushX * this.pushX + this.pushZ * this.pushZ
        if (fuel > 0) {
            if(fuel <1000)
            if (d0 > 1.0E-4 && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.001) {
                d0 = MathHelper.sqrt(d0).toDouble()
                this.pushX /= d0
                this.pushZ /= d0

                if (this.pushX * this.motionX + this.pushZ * this.motionZ < 0.0) {
                    this.pushX = 0.0
                    this.pushZ = 0.0
                }
            }
        }
        if (state.block == Blocks.ACTIVATOR_RAIL) {
            run = !state.getValue(BlockRailPowered.POWERED)
        }else{
            run = true
        }
    }

    override fun applyDrag() {
        motionX *= .9
        motionY *= 0.0
        motionZ *= .9

        if(!run)
        {
            motionX *= .0
            motionY *= .0
            motionZ *= .0
            return;
        }

        if (fuel > 0) {
            val yaw = rotationYaw * Math.PI / 180.0
            motionX += Math.cos(yaw) * pushX
            motionZ += Math.sin(yaw) * pushZ
        } else {
            motionX = Math.copySign(Math.min(Math.abs(motionX), 0.3f.toDouble()), motionX)
            motionZ = Math.copySign(Math.min(Math.abs(motionZ), 0.3f.toDouble()), motionZ)
        }
    }

    override fun processInitialInteract(player: EntityPlayer, hand: EnumHand): Boolean {
        val itemstack = player.getHeldItem(hand)

        if (super.processInitialInteract(player, hand)) return true

        if (itemstack.item === Items.COAL && this.fuel + 100 <= 320000) {
            if (!player.capabilities.isCreativeMode) {
                itemstack.shrink(1)
            }

            this.fuel += 100
        } else {
            if (itemstack.item == tablet) {
                forward = !forward
            }
        }

        this.pushX = if (forward) 10.0 else -10.0
        this.pushZ = if (forward) 10.0 else -10.0
        return true
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    override fun writeEntityToNBT(compound: NBTTagCompound) {
        super.writeEntityToNBT(compound)
        compound.setDouble("PushX", this.pushX)
        compound.setDouble("PushZ", this.pushZ)
        compound.setInteger("Fuel", this.fuel)
        compound.setBoolean("Forward", forward)
        compound.setBoolean("Run", run)

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    override fun readEntityFromNBT(compound: NBTTagCompound) {
        super.readEntityFromNBT(compound)
        this.pushX = compound.getDouble("PushX")
        this.pushZ = compound.getDouble("PushZ")
        this.fuel = compound.getInteger("Fuel")
        forward = compound.getBoolean("Forward")
        run = compound.getBoolean("Run")

    }


    override fun getDefaultDisplayTile(): IBlockState {
        return gearbox.defaultState
    }

}