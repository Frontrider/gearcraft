package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.power.BlockGearbox
import hu.frontrider.gearcraft.blocks.power.BlockShaft
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.item.Item

class WoodenShafts: Plugin {
    private val blocks = arrayOf(
            makeShaft("wooden_shaft"),
            makeShaft("wooden_shaft_birch"),
            makeShaft("wooden_shaft_spruce"),
            makeShaft("wooden_shaft_jungle"),
            makeShaft("wooden_shaft_dark_oak"),
            makeShaft("wooden_shaft_acacia"),
            makeGearbox("wooden_gearbox"),
            makeGearbox("wooden_gearbox_spruce"),
            makeGearbox("wooden_gearbox_birch"),
            makeGearbox("wooden_gearbox_jungle"),
            makeGearbox("wooden_gearbox_dark_oak"),
            makeGearbox("wooden_gearbox_acacia")
      )
    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item> {
        return arrayOf()
    }

    fun makeShaft(name:String):Block{
       return BlockShaft(
                4,
                4f,
                name,
                "axe",
                1,
                1f,
                SoundType.WOOD,
                Material.WOOD,
                MapColor.WOOD
        )
    }
    fun makeGearbox(name:String):Block{
        return BlockGearbox(
                4,
                2f,
                name,
                "axe",
                1,
                1f,
                SoundType.WOOD,
                Material.WOOD,
                MapColor.WOOD
        )
    }
}