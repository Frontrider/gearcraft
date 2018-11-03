package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.machine.BlockSaw
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.item.Item

class Saws : Plugin {
    private val blocks = arrayOf(
            makeSaw("oak_saw"),
            makeSaw("birch_saw"),
            makeSaw("acacia_saw"),
            makeSaw("big_oak_saw"),
            makeSaw("spruce_saw"),
            makeSaw("jungle_saw")
    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item> {
        return arrayOf()
    }

    fun makeSaw(name: String): Block {
        return BlockFactory.start(BlockSaw(
                3,
                2f,
                "",
                "axe",
                1f,
                SoundType.WOOD,
                Material.WOOD,
                MapColor.WOOD,
                2
        ))
                .setResourourceLocation(name)
                .build()
    }
}