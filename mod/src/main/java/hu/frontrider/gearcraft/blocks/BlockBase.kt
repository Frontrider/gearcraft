package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material

/**
 * contains my generic initializer code, and nothing else.
 * */
open class BlockBase(
        resistance: Float,
        tool: String,
        miningLevel: Int,
        hardness: Float,
        soundType: SoundType,
        material: Material,
        mapColor: MapColor
) : Block(material,mapColor)  {

    init{
        this.soundType = soundType
        this.setResistance(resistance)
        this.setHardness(hardness)
        this.setHarvestLevel(tool,miningLevel)
    }
}