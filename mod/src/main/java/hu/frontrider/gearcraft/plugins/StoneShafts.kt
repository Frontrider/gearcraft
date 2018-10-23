package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.power.BlockGearbox
import hu.frontrider.gearcraft.blocks.power.BlockShaft
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.item.Item

class StoneShafts : Plugin {
    private val blocks = arrayOf(
            makeShaft("stone_shaft"),
            makeShaft("stone_shaft_granite"),
            makeShaft("stone_shaft_andesite"),
            makeShaft("stone_shaft_diorite"),
            makeGearbox("stone_gearbox"),
            makeGearbox("stone_gearbox_granite"),
            makeGearbox("stone_gearbox_diorite"),
            makeGearbox("stone_gearbox_andesite")
    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item> {
        return arrayOf()
    }

    fun makeShaft(name: String): Block {
        return BlockShaft(
                8,
                4f,
                name,
                "pickaxe",
                2,
                1f,
                SoundType.STONE,
                Material.ROCK,
                MapColor.STONE
        )
    }

    fun makeGearbox(name: String): Block {
        return BlockGearbox(
                8,
                2f,
                name,
                "pickaxe",
                1,
                1f,
                SoundType.STONE,
                Material.ROCK,
                MapColor.STONE
        )
    }
}