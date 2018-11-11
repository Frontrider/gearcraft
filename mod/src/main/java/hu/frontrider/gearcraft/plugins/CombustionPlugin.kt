package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.producer.BlockFuelBurnerEngine
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.init.Items
import net.minecraft.init.SoundEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.SoundEvent

class CombustionPlugin: Plugin {

    private val blocks = arrayOf(
            BlockFactory.start(BlockFuelBurnerEngine(
                    16,
                    2f,
                    "pickaxe",
                    1,
                    1f,
                    SoundType.STONE,
                    Material.ROCK,
                    MapColor.STONE, arrayOf(ItemStack(Items.COAL,1,0),ItemStack(Items.COAL,1,1)),
                    10000))
                    .setResourourceLocation("simple_burner_engine")
                    .build(),
            BlockFactory.start(BlockFuelBurnerEngine(
                    16,
                    2f,
                    "pickaxe",
                    1,
                    1f,
                    SoundType.STONE,
                    Material.ROCK,
                    MapColor.STONE, arrayOf(ItemStack(Items.GUNPOWDER,1,0)),
                    100000,
                    SoundEvents.ENTITY_GENERIC_EXPLODE))
                    .setResourourceLocation("explosive_engine")
                    .build()

    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item?> {
        return arrayOfNulls(0)
    }

}