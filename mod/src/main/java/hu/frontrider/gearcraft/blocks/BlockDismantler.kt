package hu.frontrider.gearcraft.blocks

import hu.frontrider.gearcraft.api.BlockStateHelpers
import hu.frontrider.gearcraft.core.registry.recipes
import hu.frontrider.gearcraft.core.util.EnergyHelper
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class BlockDismantler(power: Int,
                      resistance: Float,
                      name: String,
                      tool: String,
                      hardness: Float,
                      soundType: SoundType,
                      material: Material,
                      mapColor: MapColor,
                      miningLevel:Int):BlockBreaker(power, resistance, name, tool, hardness, soundType, material, mapColor, miningLevel) {

    //changed the tick method, so now it will do the breaker recipe, instead of breaking it normally.
    //will always destroy the block!
    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        val value = state.getValue(BlockStateHelpers.FACING)
        val targetPower = EnergyHelper.getInvertedTargetPower(world, pos, value, power / 4, null)
        if (targetPower >= power) {
            val offset = pos.offset(value.opposite)
            val targetState = world.getBlockState(offset)
            val harvestLevel = targetState.block.getHarvestLevel(targetState)

            if (harvestLevel <= miningLevel) {

                val (_, result) = recipes[recipes.indexOfFirst { targetState.block == it.block }]

                world.destroyBlock(offset, false)
                val entityItem = EntityItem(world, offset.x + .5, offset.y + .5, offset.z + .5, result)

                world.spawnEntity(entityItem)
            }
        }
    }
}