package hu.frontrider.gearcraft.core.traits

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.traits.block.IBlockInitialiser
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material

class BlockInitialiser:IBlockInitialiser {
    override fun initBlock(block: Block, resistance: Float, name: String?, tool: String?, miningLevel: Int, hardness: Float, soundType: SoundType?, material: Material?, mapColor: MapColor?) {
        block.setRegistryName(GearCraft.MODID,name)
        //TODO access transformer this method
       // block.setSoundType(soundType)
        block.setResistance(resistance)
        block.setHardness(hardness)
        block.setHarvestLevel(tool,miningLevel)
        block.unlocalizedName = "${GearCraft.MODID}.$name"
        block.tickRandomly = true
    }
}