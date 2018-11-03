package hu.frontrider.gearcraft.blocks.machine

import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.recipes.IDismantlerRecipe
import hu.frontrider.gearcraft2.blocks.machine.BlockBreaker
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*
import kotlin.collections.ArrayList

class BlockDismantler(power: Int,
                      resistance: Float,
                      name: String,
                      tool: String,
                      hardness: Float,
                      soundType: SoundType,
                      material: Material,
                      mapColor: MapColor,
                      miningLevel: Int) : BlockBreaker(power, resistance, name, tool, hardness, soundType, material, mapColor, miningLevel) {

    //changed the tick method, so now it will do the recipes recipe, instead of breaking it normally.
    //will always destroy the block!
    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if(world.isRemote)
            return
        val value = state.getValue(BlockStates.FACING)
        val targetPower = powerConsumer.getInvertedTargetPower(world, pos,value.opposite)

        if(world.isAirBlock(pos.offset(value.opposite)))
            return

        if (targetPower >= power) {
            val offset = pos.offset(value.opposite)
            val targetState = world.getBlockState(offset)
            val harvestLevel = targetState.block.getHarvestLevel(targetState)

            if (harvestLevel <= miningLevel) {
                val block = targetState.block
                val recipe = dismantleRecipes.firstOrNull {
                    it.isBlock(block)
                } ?: return

                world.destroyBlock(offset, false)

                recipe.getResults(block).forEach {
                    val entityItem = EntityItem(world, offset.x + .5, offset.y + .5, offset.z + .5, it)
                    world.spawnEntity(entityItem)
                }
            }
        }
    }

    companion object {
        val dismantleRecipes=ArrayList<IDismantlerRecipe>()
    }
}