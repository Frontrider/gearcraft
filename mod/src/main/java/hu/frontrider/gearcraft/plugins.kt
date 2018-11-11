package hu.frontrider.gearcraft

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.plugins.*
import net.minecraft.block.Block
import net.minecraft.item.Item


lateinit var basePlugin : PluginContainer

class PluginContainer: Plugin {

    val allPlugins:Array<Plugin> = arrayOf(
            BasePlugin(),
            WoodenShafts(),
            StoneShafts(),
            WeightedEngine(),
            Dismantlers(),
            Scaffolds(),
            Routers(),
            Tools(),
            Redstone(),
            Saws(),
            CombustionPlugin()
    )

    override fun getBlocks(): Array<Block> {
        return allPlugins.map { it.blocks }.flatMap { it.asIterable() }.toTypedArray()
    }

    override fun getItems(): Array<Item> {
        return allPlugins.map { it.items }.flatMap { it.asIterable() }.toTypedArray()
    }
}