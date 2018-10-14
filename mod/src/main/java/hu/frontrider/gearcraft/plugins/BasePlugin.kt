package hu.frontrider.gearcraft.plugins

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.Plugin
import hu.frontrider.gearcraft.blocks.*
import hu.frontrider.gearcraft.blocks.power.BlockShaft
import hu.frontrider.gearcraft.blocks.power.CreativeGearbox
import hu.frontrider.gearcraft.items.ColoredItem
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.MapColor.STONE
import net.minecraft.block.material.Material
import net.minecraft.block.material.Material.*
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

class BasePlugin : Plugin {

    private val items = arrayOf(
            ColoredItem(arrayOf(12293985, 4273185))
                    .setRegistryName(ResourceLocation(GearCraft.MODID, "wooden_gear"))
                    .setUnlocalizedName("${GearCraft.MODID}.wooden_gear")
                    .setCreativeTab(GearCraft.creativeTab),
            ColoredItem(arrayOf(3604602, 1703994))
                    .setRegistryName(ResourceLocation(GearCraft.MODID, "obsidian_gear"))
                    .setUnlocalizedName("${GearCraft.MODID}.obsidian_gear")
                    .setCreativeTab(GearCraft.creativeTab),
            ColoredItem(arrayOf(11119017, 5658198))
                    .setRegistryName(ResourceLocation(GearCraft.MODID, "stone_gear"))
                    .setUnlocalizedName("${GearCraft.MODID}.stone_gear")
                    .setCreativeTab(GearCraft.creativeTab),
            ColoredItem(arrayOf(14277081, 14277081))
                    .setRegistryName(ResourceLocation(GearCraft.MODID, "iron_gear"))
                    .setUnlocalizedName("${GearCraft.MODID}.iron_gear")
                    .setCreativeTab(GearCraft.creativeTab)
    )

    override fun getItems(): Array<Item> {
        return items
    }

    private val blocks: Array<Block> = arrayOf(
            BlockGearboxNormal(
                    4,
                    2f,
                    "wooden_gearbox",
                    "axe",
                    1,
                    1f,
                    SoundType.WOOD,
                    WOOD,
                    MapColor.WOOD
            ),
            BlockGearboxNormal(
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
            BlockGearboxNormal(
                    8,
                    2f,
                    "stone_gearbox",
                    "pickaxe",
                    1,
                    1f,
                    SoundType.STONE,
                    ROCK,
                    MapColor.STONE
            ),
            BlockGearboxNormal(
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
                    8,
                    4f,
                    "stone_shaft",
                    "pickaxe",
                    2,
                    1f,
                    SoundType.STONE,
                    ROCK,
                    STONE
            ),
            BlockShaft(
                    4,
                    4f,
                    "wooden_shaft",
                    "axe",
                    1,
                    1f,
                    SoundType.WOOD,
                    WOOD,
                    MapColor.WOOD
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
            STONE
    ),
            BlockBreaker(
                    2,
                    2f,
                    "stone_breaker",
                    "pickaxe",
                    1f,
                    SoundType.STONE,
                    ROCK,
                    STONE,
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
                    Material.ROCK,
                    MapColor.OBSIDIAN,
                    3
            ),
            BlockDismantler(
                    1,
                    2f,
                    "wooden_dismantler",
                    "axe",
                    1f,
                    SoundType.WOOD,
                    WOOD,
                    MapColor.WOOD,
                    0
            ),
            BlockDismantler(
                    2,
                    2f,
                    "stone_dismantler",
                    "pickaxe",
                    1f,
                    SoundType.STONE,
                    Material.ROCK,
                    MapColor.STONE,
                    1
            ),
            BlockDismantler(
                    4,
                    2f,
                    "iron_dismantler",
                    "pickaxe",
                    1f,
                    SoundType.METAL,
                    Material.IRON,
                    MapColor.IRON,
                    2
            ),
            BlockDismantler(
                    8,
                    4f,
                    "obsidian_dismantler",
                    "pickaxe",
                    4f,
                    SoundType.STONE,
                    Material.ROCK,
                    MapColor.OBSIDIAN,
                    3
            ),
            WaterMill(4,
                    2f,
                    "wooden_watermill",
                    "axe",
                    1f,
                    SoundType.WOOD,
                    WOOD,
                    MapColor.WOOD,1)
    )

    override fun getBlocks(): Array<Block> {
        return blocks
    }
}