package hu.frontrider.gearcraft.proxy

import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.event.FMLInitializationEvent

open class CommonProxy {

    open fun init(e: FMLInitializationEvent) {

    }

    open fun handleRedstoneProxy(pos: BlockPos,dim:Int){}
}
