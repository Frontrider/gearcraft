package hu.frontrider.gearcraft.registry

import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material

object TierRegistry {


    val WOODEN = Tier(
            8,
            1,
            "wooden",
            "axe",
            0,
            .2f,
            SoundType.WOOD,
            Material.WOOD,
            MapColor.BROWN, 3)

    val IRON = Tier(
            16,
            2,
            "iron",
            "pickaxe",
            2,
            .4f,
            SoundType.METAL,
            Material.IRON,
            MapColor.IRON, 10
    )
    val STONE = Tier(
            4,
            1,
            "stone",
            "pickaxe",
            0,
            .4f,
            SoundType.STONE,
            Material.ROCK,
            MapColor.STONE, 1
    )

    val TIERS = arrayOf(WOODEN, IRON, STONE)

    class Tier(val power: Int, val resistance: Int, val name: String, val tool: String, val miningLevel: Int, val hardness: Float, val soundType: SoundType, val material: Material, val mapColor: MapColor, val factor: Int) {
        companion object {

            fun setBlock(block: Block, tier: Tier) {
                block.setHarvestLevel(tier.tool, tier.miningLevel)
                block.setHardness(tier.hardness)
                block.setResistance(tier.resistance.toFloat())
            }
        }
    }

}
