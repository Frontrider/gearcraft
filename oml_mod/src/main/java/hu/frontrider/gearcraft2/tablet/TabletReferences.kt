package hu.frontrider.gearcraft.tablet

import hu.frontrider.gearcraft.GearCraft
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry

/**
 * Stores objectholder references for the gui, to not to crash the server.
 * */
object TabletReferences {
    val BOOK = ResourceLocation("textures/items/book_normal.png")
    val REDSTONE = ResourceLocation("textures/items/redstone_dust.png")

    @GameRegistry.ObjectHolder("${GearCraft.MODID}:wooden_gear")
    lateinit var gear: Item

    @GameRegistry.ObjectHolder("${GearCraft.MODID}:iron_drill")
    lateinit var tools: Item

    @GameRegistry.ObjectHolder("${GearCraft.MODID}:diamond_drill")
    lateinit var drill: Item

    @GameRegistry.ObjectHolder("${GearCraft.MODID}:tablet")
    lateinit var tablet: Item

    @GameRegistry.ObjectHolder("${GearCraft.MODID}:wooden_gearbox_dark_oak")
    lateinit var gearbox: Block

    @GameRegistry.ObjectHolder("${GearCraft.MODID}:glueball")
    lateinit var glue: Item
}