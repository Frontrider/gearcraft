package hu.frontrider.gearcraft.craftinggear.events

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.GuiHandler
import hu.frontrider.gearcraft.craftinggear.networking.OpenGuiMessage
import hu.frontrider.gearcraft.proxy.ClientProxy
import hu.frontrider.gearcraft.tablet.network.redstoneproxy.modify.RedstoneProxyMessage
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly


class KeyEventHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    fun onEvent(event: KeyInputEvent) {

        val keyBindings = ClientProxy.keyBindings
        if (keyBindings[0].isPressed) {
            GearCraft.NETWORK_WRAPPER.sendToServer(OpenGuiMessage())
            /*val player = Minecraft.getMinecraft().player;
            player.openGui(
                    GearCraft.instance,
                    GuiHandler.craftingGear,
                    player.world,
                    player.posX.toInt(),
                    player.posY.toInt(),
                    player.posZ.toInt()
            )*/
        }
    }
}