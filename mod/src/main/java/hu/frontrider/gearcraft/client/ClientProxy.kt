package hu.frontrider.gearcraft.client

import hu.frontrider.gearcraft.basePlugin
import hu.frontrider.gearcraft.items.ColoredItem
import hu.frontrider.gearcraft.proxy.CommonProxy
import hu.frontrider.gearcraft.core.registry.ItemRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.ItemModelMesher
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class ClientProxy : CommonProxy() {

    override fun init(e: FMLInitializationEvent) {
        super.init(e)
        initClient(Minecraft.getMinecraft().renderItem.itemModelMesher)
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
}
