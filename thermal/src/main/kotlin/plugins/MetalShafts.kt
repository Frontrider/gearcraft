package hu.frontrider.gearcraft.thermal.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.power.BlockGearbox
import hu.frontrider.gearcraft.blocks.power.BlockShaft
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.item.Item

class MetalShafts : Plugin {
    private val blocks = arrayOf(
            makeShaft("copper", 2, 1),
            makeShaft("tin", 2, 1),
            makeShaft("platinum", 32, 3),
            makeShaft("iridium", 64, 3),

            makeGearbox("copper", 2, 1),
            makeGearbox("tin", 2, 1),
            makeGearbox("platinum", 32, 3),
            makeGearbox("iridium", 64, 3)
    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item> {
        return arrayOf()
    }

    fun makeShaft(name: String, power: Int, miningLevel: Int): Block {
        return BlockFactory.start(BlockShaft(
                power,
                10f,
                name ,
                "pickaxe",
                miningLevel,
                5f,
                SoundType.METAL,
                Material.IRON,
                MapColor.IRON))
                .setResourourceLocation(name+ "_shaft")
                .build()
    }

    fun makeGearbox(name: String, power: Int, miningLevel: Int): Block {
        return BlockFactory.start(BlockGearbox(
                power,
                10f,
                name ,
                "pickaxe",
                miningLevel,
                5f,
                SoundType.METAL,
                Material.IRON,
                MapColor.IRON
        ))
                .setResourourceLocation(name+ "_gearbox")
                .build()


    }
}