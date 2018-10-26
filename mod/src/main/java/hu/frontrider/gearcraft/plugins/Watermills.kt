package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.producer.StrongWatermill
import hu.frontrider.gearcraft.blocks.producer.WaterMill
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.item.Item

class Watermills : Plugin {
    private val blocks = arrayOf(
            makeWatermill("wooden_watermill"),
            makeWatermill("wooden_watermill_spruce"),
            makeWatermill("wooden_watermill_birch"),
            makeWatermill("wooden_watermill_big_oak"),
            makeWatermill("wooden_watermill_jungle"),
            makeWatermill("wooden_watermill_acacia"),
            BlockFactory.start(StrongWatermill(
                    16,
                    2f,
                    "strong_watermill",
                    "pickaxe",
                    1f,
                    SoundType.METAL,
                    Material.IRON,
                    MapColor.IRON,
                    2
            ))
                    .setResourourceLocation("strong_watermill")
                    .build()

    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item> {
        return arrayOf()
    }

    fun makeWatermill(name: String): Block {
      return  BlockFactory.start(WaterMill(4,
                2f,
                name,
                "axe",
                1f,
                SoundType.WOOD,
                Material.WOOD,
                MapColor.WOOD, 1))
                .setResourourceLocation(name)
                .build()

    }
}