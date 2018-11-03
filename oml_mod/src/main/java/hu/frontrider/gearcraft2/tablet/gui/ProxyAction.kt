package hu.frontrider.gearcraft.tablet.gui

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.core.gui.widgets.interfaces.Clickable
import hu.frontrider.gearcraft.tablet.network.redstoneproxy.use.RedstoneProxyMessage

class ProxyAction(val id:Int):Clickable {
    override fun onClick(mouseX: Int, mouseY: Int, mouseButton: Int) {
        GearCraft.NETWORK_WRAPPER.sendToServer(RedstoneProxyMessage(id))
    }
    override var x=0
    override var y=0
    override var width=0
    override var height =0
}