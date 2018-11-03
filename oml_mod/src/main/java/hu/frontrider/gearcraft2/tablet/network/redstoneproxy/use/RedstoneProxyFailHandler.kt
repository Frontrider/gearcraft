package hu.frontrider.gearcraft.tablet.network.redstoneproxy.use

import hu.frontrider.gearcraft.tablet.network.redstoneproxy.use.RedstoneProxyFailed
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

class RedstoneProxyFailHandler : IMessageHandler<RedstoneProxyFailed, IMessage> {
    override fun onMessage(p0: RedstoneProxyFailed?, p1: MessageContext?): IMessage? {
        println("proxy failed")
        return null
    }

}