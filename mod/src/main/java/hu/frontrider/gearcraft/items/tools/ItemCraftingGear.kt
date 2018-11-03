package hu.frontrider.gearcraft.items.tools

import baubles.api.BaubleType
import baubles.api.IBauble
import baubles.api.render.IRenderBauble
import hu.frontrider.gearcraft.craftinggear.crafting.StorageHandler
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.init.SoundEvents
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.EnumHelper


class ItemCraftingGear : ItemArmor(ARMOR_MATERIAL_CRAFTING, 1, EntityEquipmentSlot.LEGS), IBauble, IRenderBauble {

    companion object {
        val ARMOR_MATERIAL_CRAFTING = EnumHelper.addArmorMaterial(
                "GC:CRAFTING_GEAR",
                "crafting_gear",
                10,
                intArrayOf(1, 1, 1, 1),
                2,
                SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
                0f)!!
    }

    init {
        ARMOR_MATERIAL_CRAFTING.setRepairItem(ItemStack(Blocks.CRAFTING_TABLE))
    }

    override fun onPlayerBaubleRender(stack: ItemStack?, player: EntityPlayer?, type: IRenderBauble.RenderType?, partialTicks: Float) {

    }

    override fun getBaubleType(itemstack: ItemStack?): BaubleType {
        return BaubleType.BODY
    }

    override fun getArmorTexture(stack: ItemStack?, entity: Entity?, slot: EntityEquipmentSlot?, type: String?): String {
        return "gearcraft:textures/models/armor/crafting_gear.png"
    }

    override fun initCapabilities(stack: ItemStack, nbt: NBTTagCompound?): ICapabilityProvider? {
        return StorageHandler(stack)
    }

}