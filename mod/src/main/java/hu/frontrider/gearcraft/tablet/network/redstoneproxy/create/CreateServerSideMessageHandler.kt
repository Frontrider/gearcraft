package hu.frontrider.gearcraft.tablet.network.redstoneproxy.create

import hu.frontrider.gearcraft.core.util.data.toNBT
import hu.frontrider.gearcraft.items.tools.RedstoneTablet
import hu.frontrider.gearcraft.tablet.TabletData
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

class CreateServerSideMessageHandler : IMessageHandler<CreateProxyMessage, IMessage> {
    override fun onMessage(message: CreateProxyMessage, context: MessageContext): IMessage? {
        val player = context.serverHandler.player
        val tabletStack = when {
            player.heldItemMainhand.item is RedstoneTablet -> player.heldItemMainhand
            player.heldItemOffhand.item is RedstoneTablet -> player.heldItemOffhand
            else -> return null
        }
        val tabletTag = tabletStack.tagCompound ?: NBTTagCompound()

        val name = TabletData.REDSTONE_PROXIES.name
        val proxyIdName = TabletData.PROXY_ID.name


        val proxies = if (tabletTag.hasKey(name)) tabletTag.getTag(name) as NBTTagList else NBTTagList()
        var currIndex = if (tabletTag.hasKey(proxyIdName)) tabletTag.getInteger(proxyIdName) else 0
        val proxyTag = NBTTagCompound()
        proxyTag.setTag("pos", message.pos.toNBT())
        proxyTag.setInteger("dim", message.dimension)
        proxyTag.setInteger("id", currIndex++)

        proxies.appendTag(proxyTag)
        tabletTag.setInteger(proxyIdName,currIndex)
        tabletTag.setTag(name,proxies)

        tabletStack.tagCompound = tabletTag
        return null
    }
}