package hu.frontrider.gearcraft.tablet.network.redstoneproxy.create

import io.netty.buffer.ByteBuf
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.ByteBufUtils
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

class CreateProxyMessage(var pos: BlockPos, var dimension: Int) : IMessage {
    constructor():this(BlockPos(0,0,0),0)
    override fun fromBytes(p0: ByteBuf) {
        pos = BlockPos(ByteBufUtils.readVarInt(p0, 5),
                ByteBufUtils.readVarInt(p0, 5),
                ByteBufUtils.readVarInt(p0, 5))

        dimension = ByteBufUtils.readVarInt(p0,4)
    }

    override fun toBytes(p0: ByteBuf) {
        ByteBufUtils.writeVarInt(p0, pos.x, 5)
        ByteBufUtils.writeVarInt(p0, pos.y, 5)
        ByteBufUtils.writeVarInt(p0, pos.z, 5)
        ByteBufUtils.writeVarInt(p0, dimension, 2)

    }
}