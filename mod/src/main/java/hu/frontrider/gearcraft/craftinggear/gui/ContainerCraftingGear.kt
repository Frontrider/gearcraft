package hu.frontrider.gearcraft.craftinggear.gui

import hu.frontrider.gearcraft.items.tools.ItemCraftingGear
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.init.Blocks
import net.minecraft.inventory.*
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.SlotItemHandler

class ContainerCraftingGear(playerInventory: InventoryPlayer, private val world: World,
                            /** Position of the workbench  */
                            private val pos: BlockPos) : Container() {
    /** The crafting matrix inventory (3x3).  */
    var craftMatrix = InventoryCrafting(this, 3, 3)
    var craftResult = InventoryCraftResult()
    private val player: EntityPlayer = playerInventory.player

    init {
        val itemStackFromSlot = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS)
        if (itemStackFromSlot.item !is ItemCraftingGear) {
            player.closeScreen()
        }
        val itemHandler = itemStackFromSlot.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
        for (i in 0..3) {
            for (j in 0..2) {
                this.addSlotToContainer(SlotItemHandler(itemHandler, j + i * 3, 8+ j * 18, 8 + i * 18))
            }
        }


        this.addSlotToContainer(SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124 + 22, 35))

        for (i in 0..2) {
            for (j in 0..2) {
                this.addSlotToContainer(Slot(this.craftMatrix, j + i * 3, 34 + 19 + 30 + j * 18, 16 + i * 18))
            }
        }

        for (k in 0..2) {
            for (i1 in 0..8) {
                this.addSlotToContainer(Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18))
            }
        }

        for (l in 0..8) {
            this.addSlotToContainer(Slot(playerInventory, l, 8 + l * 18, 142))
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    override fun onCraftMatrixChanged(inventoryIn: IInventory) {
        this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult)
    }

    /**
     * Called when the container is closed.
     */
    override fun onContainerClosed(playerIn: EntityPlayer) {
        super.onContainerClosed(playerIn)

        if (!this.world.isRemote) {
            this.clearContainer(playerIn, this.world, this.craftMatrix)
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
    override fun canInteractWith(playerIn: EntityPlayer): Boolean {
        return true;
    }

    /**
     * Handle when the stack in slot `index` is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var itemstack = ItemStack.EMPTY
        val slot = this.inventorySlots[index]

        if (slot != null && slot.hasStack) {
            val itemstack1 = slot.stack
            itemstack = itemstack1.copy()

            if (index == 0) {
                itemstack1.item.onCreated(itemstack1, this.world, playerIn)

                if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY
                }

                slot.onSlotChange(itemstack1, itemstack)
            } else if (index in 10..36) {
                if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
                    return ItemStack.EMPTY
                }
            } else if (index in 37..45) {
                if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
                    return ItemStack.EMPTY
                }
            } else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY
            }

            if (itemstack1.isEmpty) {
                slot.putStack(ItemStack.EMPTY)
            } else {
                slot.onSlotChanged()
            }

            if (itemstack1.count == itemstack.count) {
                return ItemStack.EMPTY
            }

            val itemstack2 = slot.onTake(playerIn, itemstack1)

            if (index == 0) {
                playerIn.dropItem(itemstack2, false)
            }
        }

        return itemstack
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    override fun canMergeSlot(stack: ItemStack, slotIn: Slot): Boolean {
        return slotIn.inventory !== this.craftResult && super.canMergeSlot(stack, slotIn)
    }

}
