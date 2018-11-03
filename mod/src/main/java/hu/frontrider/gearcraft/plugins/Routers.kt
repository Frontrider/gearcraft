package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.machine.BlockRouter
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.item.Item

class Routers : Plugin {
    private val blocks = arrayOf<Block>(
            BlockFactory.start(BlockRouter(1,
                    4f,
                    "axe",
                    2f,
                    SoundType.WOOD,
                    Material.WOOD,
                    MapColor.BROWN,
                    1,
                    16))
                    .setResourourceLocation("router")
                    .build(),
            BlockFactory.start(BlockRouter(1,
                    4f,
                    "pickaxe",
                    2f,
                    SoundType.METAL,
                    Material.IRON,
                    MapColor.IRON,
                    1,
                    32))
                    .setResourourceLocation("heavy_router")
                    .build(),
            BlockFactory.start(BlockRouter(1,
                    4f,
                    "pickaxe",
                    2f,
                    SoundType.METAL,
                    Material.IRON,
                    MapColor.IRON,
                    1,
                    64))
                    .setResourourceLocation("stack_router")
                    .build()
    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item> {
        return arrayOf()
    }
}