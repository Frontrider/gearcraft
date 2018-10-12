package hu.frontrider.gearcraft.client.guide

import net.minecraft.init.Items.PAPER
import net.minecraft.init.Items.BOOK
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraft.item.ItemStack
import amerifrance.guideapi.api.GuideAPI
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.Display.setTitle
import net.minecraft.init.Blocks
import amerifrance.guideapi.category.CategoryItemStack
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract
import java.util.ArrayList
import net.minecraft.init.Items.DIAMOND_SWORD
import amerifrance.guideapi.entry.EntryItemStack
import amerifrance.guideapi.page.PageFurnaceRecipe
import net.minecraftforge.oredict.ShapedOreRecipe
import amerifrance.guideapi.page.PageIRecipe
import amerifrance.guideapi.api.IPage
import amerifrance.guideapi.page.PageText
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract
import javax.annotation.Nonnull
import amerifrance.guideapi.api.IGuideBook
import amerifrance.guideapi.api.GuideBook
import amerifrance.guideapi.api.impl.Book
import hu.frontrider.gearcraft.GearCraft
import hu.frontrider.gearcraft.GearCraft.Companion.MODID
import net.minecraft.init.Items
import java.awt.Color


@GuideBook
class Book : IGuideBook {

    override fun buildBook(): Book {
        // Create the map of entries. A LinkedHashMap is used to retain the order of entries.
        val entries = LinkedHashMap<ResourceLocation, EntryAbstract>()

        // Creation of our first entry.
        val pages1 = ArrayList<IPage>()
        pages1.add(PageText("This is a page in my guide!"))
        entries[ResourceLocation("example", "entry_one")] = EntryItemStack(pages1, "Entry One", ItemStack(Blocks.BEACON))

        // Creation of our second entry.
        val pages2 = ArrayList<IPage>()
        pages2.add(PageFurnaceRecipe("oreGold"))
        entries[ResourceLocation("example", "entry_two")] = EntryItemStack(pages2, "Entry Two", ItemStack(Items.DIAMOND_SWORD))

        // Setup the list of categories and add our entries to it.
        val categories = ArrayList<CategoryAbstract>()
        categories.add(CategoryItemStack(entries, "My Category", ItemStack(Blocks.COMMAND_BLOCK)))

        // Setup the book's basePlugin information
        myGuide = Book()
        myGuide.title = "gearcraft.guide.title"
        myGuide.isSpawnWithBook = true
        myGuide.welcomeMessage = "gearcraft.guide.welcome"
        myGuide.displayName = "gearcraft.guide.name"
        myGuide.author = "Frontrider"
        myGuide.color = Color.RED
        myGuide.categoryList = categories
        myGuide.registryName = ResourceLocation(MODID, "first_guide")
        myGuide.creativeTab = GearCraft.creativeTab
        return myGuide
    }

    @SideOnly(Side.CLIENT)
    override fun handleModel(bookStack: ItemStack) {
        // Use the default GuideAPI model
        GuideAPI.setModel(myGuide)
    }

    override fun handlePost(bookStack: ItemStack) {
        // Register a recipe so player's can obtain the book
    }

    companion object {

        lateinit var myGuide: Book
    }
}