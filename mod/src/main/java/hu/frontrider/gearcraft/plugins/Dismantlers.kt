package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.machine.BlockDismantler
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import hu.frontrider.gearcraft.core.util.factory.ItemFactory
import hu.frontrider.gearcraft.items.aids.GrindingBall
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.item.Item

class Dismantlers : Plugin {

    private val blocks = arrayOf(
            makeWooden("wooden_dismantler"),
            makeWooden("wooden_dismantler_acacia"),
            makeWooden("wooden_dismantler_big_oak"),
            makeWooden("wooden_dismantler_jungle"),
            makeWooden("wooden_dismantler_birch"),
            makeWooden("wooden_dismantler_spruce"),
            makeStone("stone_dismantler"),
            makeStone("stone_dismantler_granite"),
            makeStone("stone_dismantler_andesite"),
            makeStone("stone_dismantler_diorite"),
            BlockFactory.start(BlockDismantler(
                    4,
                    2f,
                    "iron_dismantler",
                    "pickaxe",
                    1f,
                    SoundType.METAL,
                    Material.IRON,
                    MapColor.IRON,
                    2
            )).setResourourceLocation("iron_dismantler")
                    .build(),
            BlockFactory.start(BlockDismantler(
                    8,
                    4f,
                    "obsidian_dismantler",
                    "pickaxe",
                    4f,
                    SoundType.STONE,
                    Material.ROCK,
                    MapColor.OBSIDIAN,
                    3
            )).setResourourceLocation("obsidian_dismantler")
                    .build()

    )
    private val items = arrayOf(
            ItemFactory.start(GrindingBall(1))
                    .setResourourceLocation("iron_grinding_ball")
                    .build(),
            ItemFactory.start(GrindingBall(3))
                    .setResourourceLocation("obsidian_grinding_ball")
                    .build()
    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item> {
        return items
    }

    private fun makeWooden(name: String): Block {
        return BlockFactory.start(BlockDismantler(
                1,
                2f,
                name,
                "axe",
                1f,
                SoundType.WOOD,
                Material.WOOD,
                MapColor.WOOD,
                0
        ))
                .setResourourceLocation(name)
                .build()


    }

    private fun makeStone(name: String): Block {
        return BlockFactory.start(BlockDismantler(
                2,
                2f,
                name,
                "pickaxe",
                1f,
                SoundType.STONE,
                Material.ROCK,
                MapColor.STONE,
                1
        ))
                .setResourourceLocation(name)
                .build()


    }
}