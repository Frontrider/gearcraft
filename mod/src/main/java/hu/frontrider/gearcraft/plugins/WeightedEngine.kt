package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.producer.BlockWeightedEngine
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry

class WeightedEngine : Plugin {
    private var blocks = arrayOf(
            makeWatermill("wooden_weighted_engine"),
            makeWatermill("wooden_weighted_engine_spruce"),
            makeWatermill("wooden_weighted_engine_birch"),
            makeWatermill("wooden_weighted_engine_big_oak"),
            makeWatermill("wooden_weighted_engine_jungle"),
            makeWatermill("wooden_weighted_engine_acacia"),
            BlockFactory.start(BlockWeightedEngine(
                    16,
                    2f,
                    "pickaxe",
                    1f,
                    SoundType.METAL,
                    Material.IRON,
                    MapColor.IRON,
                    2,
                    {
                        arrayOf(ItemStack(gear), ItemStack(pulp, 2), ItemStack(Items.STICK))
                    },
                    5000
            ))
                    .setResourourceLocation("strong_weighted_engine")
                    .build()
    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }

    override fun getItems(): Array<Item> {
        blocks

        return arrayOf()
    }

    fun makeWatermill(name: String): Block {
        return BlockFactory.start(BlockWeightedEngine(8,
                2f,
                "axe",
                1f,
                SoundType.WOOD,
                Material.WOOD,
                MapColor.WOOD, 1,
                {
                    it.drops = arrayOf(ItemStack(gear), ItemStack(pulp, 2))
                }))
                .setResourourceLocation(name)
                .build()

    }

    companion object {
        @GameRegistry.ObjectHolder("${GearCraft.MODID}:wooden_gear")
        lateinit var gear: Item

        @GameRegistry.ObjectHolder("${GearCraft.MODID}:wood_pulp")
        lateinit var pulp: Item
    }
}