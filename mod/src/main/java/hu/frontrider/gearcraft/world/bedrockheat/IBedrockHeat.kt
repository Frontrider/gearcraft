package hu.frontrider.gearcraft.world.bedrockheat

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability

interface IBedrockHeat {
    var heatValue:Int
    val heatCap:Int
    val regen:Int
}

class BedrockHeatStorage: Capability.IStorage<IBedrockHeat>{

    override fun readNBT(capability: Capability<IBedrockHeat>?, instance: IBedrockHeat?, side: EnumFacing?, nbt: NBTBase?) {

    }

    override fun writeNBT(capability: Capability<IBedrockHeat>, instance: IBedrockHeat, side: EnumFacing): NBTBase? {
        val nbtTagCompound = NBTTagCompound()

        nbtTagCompound.setInteger("heatValue",instance.heatValue)
        nbtTagCompound.setInteger("heatCap",instance.heatCap)
        nbtTagCompound.setInteger("regen",instance.regen)

        return nbtTagCompound
    }

}