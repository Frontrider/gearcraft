package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.util.BlockScaffold
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.item.Item

class Scaffolds : Plugin {
    private val blocks = arrayOf(
            makeWoodenScaffold("scaffold_wooden"),
            makeWoodenScaffold("scaffold_birch"),
            makeWoodenScaffold("scaffold_acacia"),
            makeWoodenScaffold("scaffold_spruce"),
            makeWoodenScaffold("scaffold_big_oak"),
            makeWoodenScaffold("scaffold_jungle")
    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item> {
        return arrayOf()
    }

    fun makeWoodenScaffold(name:String):Block{
      return  BlockFactory.start(BlockScaffold(
                4f,
                name,
                "axe",
                1,
                1f,
                SoundType.WOOD,
                Material.WOOD,
                MapColor.WOOD
        ))
                .setResourourceLocation(name)
                .build()


    }
}