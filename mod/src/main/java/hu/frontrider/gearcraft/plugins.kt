package hu.frontrider.gearcraft

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.plugins.*
import net.minecraft.block.Block
import net.minecraft.item.Item


lateinit var basePlugin : PluginContainer

class PluginContainer: Plugin {

    var allBlocks :Array<Block>
    var allItems :Array<Item>

    init{
        val allPlugins = arrayOf(
                BasePlugin(),
                WoodenShafts(),
                StoneShafts(),
                CombustionPlugin(),
                Watermills(),
                Dismantlers(),
                Scaffolds(),
                Routers(),
                Tools(),
                Redstone()
        )
        val blocks = ArrayList<Block>()
        val items = ArrayList<Item>()
        allPlugins.forEach {
            blocks.addAll(it.blocks)
            items.addAll(it.items)
        }
        allBlocks = blocks.toTypedArray()
        allItems = items.toTypedArray()
    }

    override fun getBlocks(): Array<Block> {
        return allBlocks
    }

    override fun getItems(): Array<Item> {
        return allItems
    }
}