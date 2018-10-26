package hu.frontrider.gearcraft.items.tools

import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemTool


class ItemToolDrill(material: ToolMaterial) : ItemTool(material, setOf()) {

    init{
        attackDamage = 3*material.attackDamage
        attackSpeed = 5f
    }

    override fun canHarvestBlock(state: IBlockState, stack: ItemStack): Boolean {
        val tool = state.block.getHarvestTool(state)
        return tool == "shovel" || tool == "pickaxe"
    }

    override fun getMaxDamage(stack: ItemStack): Int {
        return super.getMaxDamage(stack) * 3
    }

    override fun getMaxDamage(): Int {
        return super.getMaxDamage() * 3
    }

    override fun getDestroySpeed(stack: ItemStack, state: IBlockState): Float {

        val material = state.material
        return if (material !== Material.IRON &&
                material !== Material.ANVIL &&
                material !== Material.ROCK &&
                Material.SAND !== material &&
                Material.GROUND !== material &&
                Material.CLAY !== material
        ) super.getDestroySpeed(stack, state) else this.efficiency
    }
}
