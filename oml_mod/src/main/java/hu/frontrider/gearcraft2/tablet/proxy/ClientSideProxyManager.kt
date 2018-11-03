package hu.frontrider.gearcraft.tablet.proxy

import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object ClientSideProxyManager {
    var makingProxy=false
}