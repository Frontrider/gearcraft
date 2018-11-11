package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.util.TemporaryRedstone
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import net.minecraft.block.Block
import net.minecraft.item.Item

class Redstone : Plugin {
    private val blocks = arrayOf<Block>(
            BlockFactory.start(TemporaryRedstone()).setResourourceLocation("temporary_redstone").build()
    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item> {
        return arrayOf()
    }

}