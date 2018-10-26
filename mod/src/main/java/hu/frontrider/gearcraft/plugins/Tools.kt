package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.core.util.factory.ItemFactory
import hu.frontrider.gearcraft.items.tools.ItemToolDrill
import net.minecraft.block.Block
import net.minecraft.item.Item

class Tools: Plugin {
    override fun getBlocks(): Array<Block> {
        return arrayOf()
    }

    private val items= arrayOf(
            ItemFactory
                    .start(ItemToolDrill(Item.ToolMaterial.WOOD))
                    .setResourourceLocation("wooden_drill")
                    .build(),
            ItemFactory
                    .start(ItemToolDrill(Item.ToolMaterial.STONE))
                    .setResourourceLocation("stone_drill")
                    .build(),
            ItemFactory
                    .start(ItemToolDrill(Item.ToolMaterial.IRON))
                    .setResourourceLocation("iron_drill")
                    .build(),
            ItemFactory
                    .start(ItemToolDrill(Item.ToolMaterial.GOLD))
                    .setResourourceLocation("gold_drill")
                    .build(),
            ItemFactory
                    .start(ItemToolDrill(Item.ToolMaterial.DIAMOND))
                    .setResourourceLocation("diamond_drill")
                    .build()
    )

    override fun getItems(): Array<Item> {
        return items
    }
}