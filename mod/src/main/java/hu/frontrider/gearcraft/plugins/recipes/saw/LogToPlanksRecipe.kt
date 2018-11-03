package hu.frontrider.gearcraft.plugins.recipes.saw

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.api.recipes.ISawRecipe
import hu.frontrider.gearcraft.core.util.inventory.InventoryCraftingFalse
import net.minecraft.block.Block
import net.minecraft.block.BlockLog
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.util.EnumFacing
import net.minecraft.util.Rotation
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary


class LogToPlanksRecipe : ISawRecipe {

    companion object {

        @GameRegistry.ObjectHolder("${GearCraft.MODID}:wood_pulp")
        lateinit var pulp: Item

    }

    override fun isBlock(block: Block): Boolean {
        val oreID = OreDictionary.getOreID("logWood")

        for (iD in OreDictionary.getOreIDs(ItemStack(block))) {
            if (oreID == iD) {
                return true
            }
        }
        return false
    }

    override fun getResults(block: ItemStack,world:World): Array<ItemStack> {
        val craftMatrix = InventoryCraftingFalse(1, 1)

        craftMatrix.setInventorySlotContents(0, block)

        val result = CraftingManager.findMatchingResult(craftMatrix, world)
        if(result.isEmpty)
            return arrayOf()

        result.count =6
        return arrayOf(result, ItemStack(pulp,world.rand.nextInt(3)))

    }

    override fun splintery(): Boolean {
        return true
    }

    class CraftingContainer : Container() {
        override fun canInteractWith(playerIn: EntityPlayer): Boolean {
            return false
        }
    }
}