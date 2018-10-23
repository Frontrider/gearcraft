package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.ItemLessBlock
import hu.frontrider.gearcraft.blocks.machine.BlockBreaker
import hu.frontrider.gearcraft.blocks.machine.BlockMagnet
import hu.frontrider.gearcraft.blocks.power.BlockGearbox
import hu.frontrider.gearcraft.blocks.power.BlockShaft
import hu.frontrider.gearcraft.blocks.power.CreativeGearbox
import hu.frontrider.gearcraft.blocks.util.BlockComposite
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import hu.frontrider.gearcraft.core.util.factory.ItemFactory
import hu.frontrider.gearcraft.items.ColoredItem
import hu.frontrider.gearcraft.items.OreDictedItem
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.material.Material.*
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

class BasePlugin : Plugin {

    private val items = arrayOf(
            ColoredItem(arrayOf(12293985, 4273185), "gearWood")
                    .setRegistryName(ResourceLocation(GearCraft.MODID, "wooden_gear"))
                    .setUnlocalizedName("${GearCraft.MODID}.wooden_gear")
                    .setCreativeTab(GearCraft.creativeTab),
            ColoredItem(arrayOf(3604602, 1703994), "gearObsidian")
                    .setRegistryName(ResourceLocation(GearCraft.MODID, "obsidian_gear"))
                    .setUnlocalizedName("${GearCraft.MODID}.obsidian_gear")
                    .setCreativeTab(GearCraft.creativeTab),
            ColoredItem(arrayOf(11119017, 5658198), "gearStone")
                    .setRegistryName(ResourceLocation(GearCraft.MODID, "stone_gear"))
                    .setUnlocalizedName("${GearCraft.MODID}.stone_gear")
                    .setCreativeTab(GearCraft.creativeTab),
            ColoredItem(arrayOf(14277081, 9342606), "gearIron")
                    .setRegistryName(ResourceLocation(GearCraft.MODID, "iron_gear"))
                    .setUnlocalizedName("${GearCraft.MODID}.iron_gear")
                    .setCreativeTab(GearCraft.creativeTab),
            ItemFactory.start(OreDictedItem("ballGlue"))
                    .setResourourceLocation("glueball")
                    .build(),
            ItemFactory.start(OreDictedItem("pulpWood"))
                    .setResourourceLocation("wood_pulp")
                    .build(),
            ItemFactory.start(OreDictedItem("dustStone"))
                    .setResourourceLocation("stone_dust")
                    .build(),
            ItemFactory.start(ColoredItem(arrayOf(16777215, 10511104), "bottleRawFurfuryl"))
                    .setResourourceLocation("raw_preservative")
                    .build(),
            ItemFactory.start(ColoredItem(arrayOf(16777215, 10511104), "bottleFurfuryl"))
                    .setResourourceLocation("fine_preservative")
                    .build()
    )

    override fun getItems(): Array<Item> {
        return items
    }

    val hardenedGlue = BlockFactory.start(ItemLessBlock(Material.ROCK, MapColor.CLOTH))
            .setResourourceLocation("hardened_glue")
            .build()
    private val blocks: Array<Block> = arrayOf(
            BlockFactory.start(BlockComposite(SAND, MapColor.BROWN, hardenedGlue))
                    .setResourourceLocation("glue_composite")
                    .build(),
            hardenedGlue,
            BlockGearbox(
                    32,
                    4f,
                    "obsidian_gearbox",
                    "pickaxe",
                    3,
                    4f,
                    SoundType.STONE,
                    ROCK,
                    MapColor.OBSIDIAN
            ),

            BlockGearbox(
                    16,
                    2f,
                    "iron_gearbox",
                    "pickaxe",
                    1,
                    1f,
                    SoundType.METAL,
                    IRON,
                    MapColor.IRON
            ),
            CreativeGearbox(),

            BlockShaft(
                    1,
                    4f,
                    "composite_shaft",
                    "axe",
                    2,
                    1f,
                    SoundType.WOOD,
                    WOOD,
                    MapColor.BROWN
            ),
            BlockShaft(
                    16,
                    4f,
                    "iron_shaft",
                    "pickaxe",
                    3,
                    1f,
                    SoundType.METAL,
                    IRON,
                    MapColor.IRON
            ), BlockShaft(
            32,
            4f,
            "obsidian_shaft",
            "pickaxe",
            3,
            4f,
            SoundType.STONE,
            ROCK,
            MapColor.STONE
    ),
            BlockBreaker(
                    2,
                    2f,
                    "stone_breaker",
                    "pickaxe",
                    1f,
                    SoundType.STONE,
                    ROCK,
                    MapColor.STONE,
                    0
            ),
            BlockBreaker(
                    4,
                    4f,
                    "iron_breaker",
                    "pickaxe",
                    1f,
                    SoundType.METAL,
                    IRON,
                    MapColor.IRON,
                    2
            ),
            BlockBreaker(
                    16,
                    8f,
                    "obsidian_breaker",
                    "pickaxe",
                    4f,
                    SoundType.STONE,
                    ROCK,
                    MapColor.OBSIDIAN,
                    3
            ),
            BlockFactory.start(Block(Material.WOOD, MapColor.WOOD))
                    .setResourourceLocation("planks_treated_wood")
                    .build(),
            BlockMagnet(4,
                    4f,
                    "magnet",
                    "pickaxe",
                    1f,
                    SoundType.METAL,
                    IRON,
                    MapColor.IRON,
                    2)

    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }
}