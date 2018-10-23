package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.producer.BlockInternalPowerEngine
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.item.Item

class CombustionPlugin:Plugin {

    private val blocks = arrayOf(
            makeEngine("combustion_engine_white"),
            makeEngine("combustion_engine"),
            makeEngine("combustion_engine_gray"),
            makeEngine("combustion_engine_black"),
            makeEngine("combustion_engine_brown"),
            makeEngine("combustion_engine_red"),
            makeEngine("combustion_engine_orange"),
            makeEngine("combustion_engine_yellow"),
            makeEngine("combustion_engine_lime"),
            makeEngine("combustion_engine_light_blue"),
            makeEngine("combustion_engine_green"),
            makeEngine("combustion_engine_cyan"),
            makeEngine("combustion_engine_blue"),
            makeEngine("combustion_engine_purple"),
            makeEngine("combustion_engine_magenta"),
            makeEngine("combustion_engine_pink")
    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item?> {
        return arrayOfNulls(0)
    }

    fun makeEngine(name:String):Block{
    return  BlockInternalPowerEngine(
                16,
                2f,
                name,
                "pickaxe",
                1,
                1f,
                SoundType.METAL,
                Material.IRON,
                MapColor.IRON
        )
    }
}