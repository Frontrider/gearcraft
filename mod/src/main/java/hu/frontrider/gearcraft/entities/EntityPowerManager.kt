package hu.frontrider.gearcraft.entities

import net.minecraft.entity.Entity
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.world.World

class EntityPowerManager(world: World): Entity(world) {
    override fun writeEntityToNBT(compound: NBTTagCompound) {
        super.writeToNBT(compound)
    }

    override fun readEntityFromNBT(compound: NBTTagCompound) {
        super.readFromNBT(compound)
    }

    override fun entityInit() {
        setNoGravity(true)

    }
    override fun getCollisionBoundingBox(): AxisAlignedBB? {
        return box
    }

    companion object {
         val box = AxisAlignedBB(0.0,0.0,0.0,1.0,1.0,1.0)

    }
}