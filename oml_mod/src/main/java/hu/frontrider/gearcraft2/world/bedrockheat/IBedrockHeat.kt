package hu.frontrider.gearcraft.world.bedrockheat

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability

interface IBedrockHeat {
    var heatValue:Int
}

class BedrockHeatStorage: Capability.IStorage<IBedrockHeat>{

    override fun readNBT(capability: Capability<IBedrockHeat>?, instance: IBedrockHeat, side: EnumFacing, nbt: NBTBase) {
        instance.heatValue =  (nbt as NBTTagCompound).getInteger("heatValue")
    }

    override fun writeNBT(capability: Capability<IBedrockHeat>, instance: IBedrockHeat, side: EnumFacing): NBTBase? {
        val nbtTagCompound = NBTTagCompound()

        nbtTagCompound.setInteger("heatValue",instance.heatValue)

        return nbtTagCompound
    }

}