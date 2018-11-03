package hu.frontrider.gearcraft.craftinggear.crafting

import hu.frontrider.gearcraft.core.util.inventory.addItemStackToInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.items.ItemStackHandler

class StorageHandler(val stack: ItemStack, val handler: IItemHandler = ItemStackHandler(12)) : ICapabilityProvider, Capability.IStorage<ItemStackHandler>, IItemHandlerModifiable {
    override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
        return handler.insertItem(slot, stack, simulate)
    }

    override fun getStackInSlot(slot: Int): ItemStack {
        return handler.getStackInSlot(slot)
    }

    override fun getSlotLimit(slot: Int): Int {
        return handler.getSlotLimit(slot)
    }

    override fun setStackInSlot(slot: Int, stack: ItemStack) {
        handler.addItemStackToInventory(stack,slot)
    }

    override fun getSlots(): Int {
        return handler.slots
    }

    override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack {
        return handler.extractItem(slot, amount, simulate)
    }

    override fun readNBT(capability: Capability<ItemStackHandler>?, instance: ItemStackHandler?, side: EnumFacing?, nbt: NBTBase?) {
        val nbtTag = nbt ?: NBTTagCompound()
        val nbtTagCompound = if ((nbtTag as NBTTagCompound).hasKey("items")) nbtTag.getCompoundTag("items") else NBTTagCompound()
        instance!!.deserializeNBT(nbtTagCompound)
    }

    override fun writeNBT(capability: Capability<ItemStackHandler>?, instance: ItemStackHandler?, side: EnumFacing?): NBTBase? {
        val nbt = stack.tagCompound ?: NBTTagCompound()
        nbt.setTag("items", instance!!.serializeNBT())
        return nbt
    }

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, facing)) this as T else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return facing == null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }
}