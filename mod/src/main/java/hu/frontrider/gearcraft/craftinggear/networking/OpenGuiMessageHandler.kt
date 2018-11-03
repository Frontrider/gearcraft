package hu.frontrider.gearcraft.craftinggear.networking

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.GuiHandler
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

class OpenGuiMessageHandler : IMessageHandler<OpenGuiMessage, IMessage> {
    override fun onMessage(message: OpenGuiMessage, context: MessageContext): IMessage? {
        val player = context.serverHandler.player

        player.apply {
            openGui(GearCraft.instance,
                    GuiHandler.craftingGear,
                    world,
                    posX.toInt(),
                    posY.toInt(),
                    posZ.toInt()
            )
        }
        return null
    }
}