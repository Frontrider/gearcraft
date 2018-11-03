package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.producer.BlockFuelBurnerEngine
import hu.frontrider.gearcraft.blocks.producer.BlockInternalPowerEngine
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class CombustionPlugin: Plugin {

    private val blocks = arrayOf(
            BlockFactory.start(BlockFuelBurnerEngine(    16,
                    2f,
                    "pickaxe",
                    1,
                    1f,
                    SoundType.STONE,
                    Material.ROCK,
                    MapColor.STONE, arrayOf(ItemStack(Items.COAL,1,0)),8000))
                    .setResourourceLocation("simple_burner_engine")
                    .build()
    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item?> {
        return arrayOfNulls(0)
    }

    fun makeEngine(name:String):Block{
       return BlockFactory.start(BlockInternalPowerEngine(
                16,
                2f,
                name,
                "pickaxe",
                1,
                1f,
                SoundType.METAL,
                Material.IRON,
                MapColor.IRON
        ))
                .setResourourceLocation(name)
                .build()
    }
}