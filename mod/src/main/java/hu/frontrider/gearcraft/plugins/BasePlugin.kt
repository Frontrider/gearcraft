package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.machine.BlockBreaker
import hu.frontrider.gearcraft.blocks.machine.BlockMagnet
import hu.frontrider.gearcraft.blocks.power.BlockGearbox
import hu.frontrider.gearcraft.blocks.power.BlockShaft
import hu.frontrider.gearcraft.blocks.power.CreativeGearbox
import hu.frontrider.gearcraft.blocks.util.BlockComposite
import hu.frontrider.gearcraft.blocks.util.BlockGlue
import hu.frontrider.gearcraft.core.util.factory.BlockFactory
import hu.frontrider.gearcraft.core.util.factory.ItemFactory
import hu.frontrider.gearcraft.items.ColoredItem
import hu.frontrider.gearcraft.items.OreDictedItem
import hu.frontrider.gearcraft.items.tools.ItemCraftingGear
import hu.frontrider.gearcraft.items.tools.RedstoneTablet
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
            ItemFactory.start(OreDictedItem("dustObsidian"))
                    .setResourourceLocation("obsidian_dust")
                    .build(),
            ItemFactory.start(OreDictedItem("dustIron"))
                    .setResourourceLocation("iron_dust")
                    .build(),
            ItemFactory.start(OreDictedItem("dustGold"))
                    .setResourourceLocation("gold_dust")
                    .build(),
            ItemFactory.start(ColoredItem(arrayOf(16777215, 10511104), "bottleRawFurfuryl"))
                    .setResourourceLocation("raw_preservative")
                    .build(),
            ItemFactory.start(ColoredItem(arrayOf(16777215, 16766371), "bottleFurfuryl"))
                    .setResourourceLocation("fine_preservative")
                    .build(),
            ItemFactory.start(RedstoneTablet())
                    .setResourourceLocation("tablet")
                    .build(),
            ItemFactory.start(ItemCraftingGear())
                    .setResourourceLocation("crafting_gear")
                    .build()
    )

    override fun getItems(): Array<Item> {
        return items
    }

    val hardenedGlue = BlockFactory.start(BlockGlue())
            .setResourourceLocation("glue_block")
            .build()
    private val blocks: Array<Block> = arrayOf(
            BlockFactory.start(BlockComposite(SAND, MapColor.BROWN, hardenedGlue))
                    .setResourourceLocation("glue_composite")
                    .build(),
            hardenedGlue,
            BlockFactory.start(BlockGearbox(
                    32,
                    4f,
                    "obsidian_gearbox",
                    "pickaxe",
                    3,
                    4f,
                    SoundType.STONE,
                    ROCK,
                    MapColor.OBSIDIAN
            ))
                    .setResourourceLocation("obsidian_gearbox")
                    .build(),
            BlockFactory.start(BlockGearbox(
                    16,
                    2f,
                    "iron_gearbox",
                    "pickaxe",
                    1,
                    1f,
                    SoundType.METAL,
                    IRON,
                    MapColor.IRON))
                    .setResourourceLocation("iron_gearbox")
                    .build(),
            BlockFactory.start(

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
                    ))
                    .setResourourceLocation("composite_shaft")
                    .build(),
            BlockFactory.start(BlockShaft(
                    16,
                    4f,
                    "iron_shaft",
                    "pickaxe",
                    3,
                    1f,
                    SoundType.METAL,
                    IRON,
                    MapColor.IRON
            ))
                    .setResourourceLocation("iron_shaft")
                    .build(),
            CreativeGearbox(),
            BlockFactory.start(BlockShaft(
                    32,
                    4f,
                    "obsidian_shaft",
                    "pickaxe",
                    3,
                    4f,
                    SoundType.STONE,
                    ROCK,
                    MapColor.STONE
            ))
                    .setResourourceLocation("obsidian_shaft")
                    .build(),

            BlockFactory.start(BlockBreaker(
                    2,
                    2f,
                    "stone_breaker",
                    "pickaxe",
                    1f,
                    SoundType.STONE,
                    ROCK,
                    MapColor.STONE,
                    0
            ))
                    .setResourourceLocation("stone_breaker")
                    .build(),

            BlockFactory.start(BlockBreaker(
                    4,
                    4f,
                    "iron_breaker",
                    "pickaxe",
                    1f,
                    SoundType.METAL,
                    IRON,
                    MapColor.IRON,
                    2
            ))
                    .setResourourceLocation("iron_breaker")
                    .build(),

            BlockFactory.start(BlockBreaker(
                    16,
                    8f,
                    "obsidian_breaker",
                    "pickaxe",
                    4f,
                    SoundType.STONE,
                    ROCK,
                    MapColor.OBSIDIAN,
                    3
            ))
                    .setResourourceLocation("obsidian_breaker")
                    .build(),
            BlockFactory.start(BlockMagnet(4,
                    4f,
                    "magnet",
                    "pickaxe",
                    1f,
                    SoundType.METAL,
                    IRON,
                    MapColor.IRON,
                    2))
                    .setResourourceLocation("magnet")
                    .build(),
            BlockFactory.start(Block(Material.WOOD, MapColor.WOOD))
                    .setResourourceLocation("planks_treated_wood")
                    .build()


    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }
}