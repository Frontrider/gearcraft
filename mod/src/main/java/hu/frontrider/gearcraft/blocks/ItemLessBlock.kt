package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.api.traits.block.IItemLessBlock
import net.minecraft.block.Block
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material

class ItemLessBlock(material: Material,mapColor: MapColor): Block(material,mapColor), IItemLessBlock {
}