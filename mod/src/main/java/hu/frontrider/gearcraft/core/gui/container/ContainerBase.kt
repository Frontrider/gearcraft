package hu.frontrider.arcana.content.containers

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

abstract class ContainerBase : Container() {


    fun placePlayerInv(playerInv: InventoryPlayer) {

        for (i in 0..2) {
            for (j in 0..8) {
                addSlotToContainer(Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18))
            }
        }

        for (k in 0..8) {
            addSlotToContainer(Slot(playerInv, k, 8 + k * 18, 142))
        }

    }
}
