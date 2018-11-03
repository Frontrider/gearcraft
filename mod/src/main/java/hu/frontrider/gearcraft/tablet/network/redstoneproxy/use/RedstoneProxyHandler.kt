package hu.frontrider.gearcraft.tablet.network.redstoneproxy.use

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.core.util.data.inRange
import hu.frontrider.gearcraft.core.util.data.toBlockPos
import hu.frontrider.gearcraft.items.tools.RedstoneTablet
import hu.frontrider.gearcraft.tablet.TabletData
import hu.frontrider.gearcraft.tablet.network.redstoneproxy.modify.RedstoneProxyMessage
import net.minecraft.block.Block
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import net.minecraftforge.fml.common.registry.GameRegistry

class RedstoneProxyHandler: IMessageHandler<RedstoneProxyMessage, IMessage> {
    companion object {

        @GameRegistry.ObjectHolder("${GearCraft.MODID}:temporary_redstone")
        lateinit var tempRedstone: Block
    }
    override fun onMessage(message: RedstoneProxyMessage, context: MessageContext): IMessage? {
        message.id
        val player = context.serverHandler.player

        val tabletStack = when {
            player.heldItemMainhand.item is RedstoneTablet -> player.heldItemMainhand
            player.heldItemOffhand.item is RedstoneTablet -> player.heldItemOffhand
            else -> return null
        }
        val tabletTag = tabletStack.tagCompound ?: NBTTagCompound()

        val name = TabletData.REDSTONE_PROXIES.name
        val proxies = if (tabletTag.hasKey(name)) tabletTag.getTag(name) as NBTTagList else NBTTagList()

        val proxy = proxies.map {
            it as NBTTagCompound
        }.firstOrNull {
            if (it.hasKey("id")) {
                val id = it.getInteger("id")
                return@firstOrNull id == message.id
            }
            false
        } ?:return null

        val dim = proxy.getInteger("dim")

        if(player.dimension != dim)
            return null

        val proxyPos = (proxy.getTag("pos") as NBTTagCompound).toBlockPos()
        if(player.position.inRange(proxyPos,16))
        {
            val worldIn = player.world

            worldIn.setBlockState(proxyPos, tempRedstone.defaultState)
            RedstoneTablet.tempRedstone.onBlockPlacedBy(worldIn, proxyPos,worldIn.getBlockState(proxyPos),player, ItemStack.EMPTY)
        }else{
            return null
        }
        return null
    }
}