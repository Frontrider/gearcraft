package hu.frontrider.gearcraft.tablet.network.redstoneproxy.modify

import hu.frontrider.gearcraft.tablet.network.redstoneproxy.ProxyMessage
import io.netty.buffer.ByteBuf
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.ByteBufUtils
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

class RedstoneProxyMessage(var id:Int):IMessage {
    constructor():this(0)
    override fun fromBytes(p0: ByteBuf?) {
        id = ByteBufUtils.readVarInt(p0, 5)
    }

    override fun toBytes(p0: ByteBuf?) {
        ByteBufUtils.writeVarInt(p0,id,5)
    }
}