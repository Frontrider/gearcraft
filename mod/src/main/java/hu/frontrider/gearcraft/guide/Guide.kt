package hu.frontrider.gearcraft.guide

import com.buuz135.project42.api.annotation.ProjectManual
import com.buuz135.project42.api.manual.IManual
import com.buuz135.project42.api.manual.impl.category.BasicCategory
import com.buuz135.project42.api.manual.impl.category.BasicCategoryEntry
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryDisplay
import com.buuz135.project42.api.manual.impl.content.RecipeContent
import com.buuz135.project42.api.manual.impl.content.TextContent
import com.buuz135.project42.manual.ManualInfo
import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import hu.frontrider.gearcraft.core.util.formatTranslate
import hu.frontrider.gearcraft.guide.content.ItemCategoryEntry
import hu.frontrider.gearcraft.guide.content.NamedItemCategoryEntry
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.ForgeRegistries


@ProjectManual("gearguide", modName = MODID)
class Guide : IManual {
    override fun registerCategories(info: ManualInfo) {
        info.registerCategory(
                BasicCategory("Energy", ItemStackCategoryDisplay(ItemStack(GuideReferences.gearbox)), formatTranslate("gearcraft.guide.group.power"))
                        .addEntry(ResourceLocation(MODID, "energy"), NamedItemCategoryEntry(ItemStack(GuideReferences.gear), formatTranslate("gearcraft.guide.page.energy"))
                                .addContent(TextContent(formatTranslate("gearcraft.guide.energy"), 123))
                        ).addEntry(ResourceLocation(MODID, "transfer"), NamedItemCategoryEntry(ItemStack(GuideReferences.gearbox), formatTranslate("gearcraft.guide.page.transfer"))
                                .addContent(TextContent(formatTranslate("gearcraft.guide.power_transfer"), 123))
                        )
                        .addEntry(ResourceLocation(MODID, "engines"), NamedItemCategoryEntry(ItemStack(GuideReferences.burner_engine), formatTranslate("gearcraft.guide.page.engine"))
                                .addContent(TextContent(formatTranslate("gearcraft.guide.engine"), 123))
                                .addContent(RecipeContent(ForgeRegistries.RECIPES.getValue(ResourceLocation(MODID, "simple_burner_engine"))))
                               // .addContent(RecipeContent(ForgeRegistries.RECIPES.getValue(ResourceLocation(MODID, "explosive_engine"))))
                        ))

        info.registerCategory(BasicCategory("Materials", ItemStackCategoryDisplay(ItemStack(GuideReferences.glue)), formatTranslate("gearcraft.guide.group.materials"))
                .addEntry(ResourceLocation(MODID, "glue"),
                        ItemCategoryEntry(GuideReferences.glue).addContent(TextContent(formatTranslate("gearcraft.guide.glue"), 123))
                ).addEntry(ResourceLocation(MODID, "treated_wood"),
                        BasicCategoryEntry(ItemStack(GuideReferences.treated_wood))
                                .addContent(TextContent(formatTranslate("gearcraft.guide.treated_wood"), 123))
                                .addContent(RecipeContent(ForgeRegistries.RECIPES.getValue(ResourceLocation(MODID, "raw_preservative"))))
                )
        )

        info.registerCategory(BasicCategory("Tools", ItemStackCategoryDisplay(ItemStack(GuideReferences.drill)), formatTranslate("gearcraft.guide.group.tools"))
                .addEntry(ResourceLocation(MODID, "drills"),
                        NamedItemCategoryEntry(GuideReferences.drill, formatTranslate("gearcraft.guide.page.drill"))
                                .addContent(TextContent(formatTranslate("gearcraft.guide.drills"), 123))
                ).addEntry(ResourceLocation(MODID, "tablet"),
                        ItemCategoryEntry(GuideReferences.tablet).addContent(TextContent(formatTranslate("gearcraft.guide.tablet"), 123))
                )
        )
        info.displayName = formatTranslate("gearcraft.guide.name")

    }
}