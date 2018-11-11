package hu.frontrider.gearcraft.proxy

import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.basePlugin
import hu.frontrider.gearcraft.items.ColoredItem
import hu.frontrider.gearcraft.gears.registry.ItemRegistry
import hu.frontrider.gearcraft.tablet.network.redstoneproxy.create.CreateProxyMessage
import hu.frontrider.gearcraft.tablet.proxy.ClientSideProxyManager
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.ItemModelMesher
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.client.settings.KeyBinding
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*
import net.minecraftforge.fml.client.registry.ClientRegistry
import org.lwjgl.input.Keyboard


class ClientProxy : CommonProxy() {
    companion object {
        val keyBindings = arrayOf(
                KeyBinding("gearctaft.key.crafing_gear.desc", Keyboard.KEY_P, "key.gearcraft.category")
        )
    }
    override fun init(e: FMLInitializationEvent) {
        super.init(e)
        initClient(Minecraft.getMinecraft().renderItem.itemModelMesher)
        // declare an array of key bindings



// register all the key bindings
        for (i in 0 until keyBindings.size) {
            ClientRegistry.registerKeyBinding(keyBindings[i])
        }

    }

    @SideOnly(Side.CLIENT)
    fun initClient(mesher: ItemModelMesher) {
        for (item in ItemRegistry.items) {
            val model = ModelResourceLocation(Objects.requireNonNull<ResourceLocation>(item.registryName).toString(), "inventory")
            ModelLoader.registerItemVariants(item, model)
            mesher.register(item, 0, model)
        }

        Minecraft.getMinecraft().itemColors.registerItemColorHandler(
                IItemColor { stack, tintIndex ->
                    (stack.item as ColoredItem).color[tintIndex]
                },
                *basePlugin.items.filter { it is ColoredItem }.toTypedArray()
        )

    }

    override fun handleRedstoneProxy(pos: BlockPos,dim:Int) {
        if (ClientSideProxyManager.makingProxy) {
            ClientSideProxyManager.makingProxy=false
            GearCraft.NETWORK_WRAPPER.sendToServer(CreateProxyMessage(pos,dim))

        }

    }
}
