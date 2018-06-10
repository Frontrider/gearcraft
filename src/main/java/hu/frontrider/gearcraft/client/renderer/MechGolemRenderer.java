package hu.frontrider.gearcraft.client.renderer;

import hu.frontrider.gearcraft.entities.EntityMechanicalGolem;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class MechGolemRenderer extends RenderLiving<EntityMechanicalGolem> {
    private static final ResourceLocation IRON_GOLEM_TEXTURES = new ResourceLocation("minecraft:textures/entity/iron_golem.png");

    public MechGolemRenderer(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelIronGolem(), 0.5F);
    }


    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityMechanicalGolem entity) {
        //return null;
        return IRON_GOLEM_TEXTURES;
    }

    protected void applyRotations(EntityMechanicalGolem entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

        if ((double) entityLiving.limbSwingAmount >= 0.01D) {
            float f = 13.0F;
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            GlStateManager.rotate(6.5F * f2, 0.0F, 0.0F, 1.0F);
        }
    }
}
