package hu.frontrider.gearcraft.thermal

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.thermal.plugins.MetalShafts
import net.minecraft.block.Block
import net.minecraft.item.Item


lateinit var basePlugin:Plugin

class PluginContainer: Plugin {

    var allBlocks :Array<Block>
    var allItems :Array<Item>

    init{
        val allPlugins = arrayListOf(
                MetalShafts()
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