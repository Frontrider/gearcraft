package hu.frontrider.gearcraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class PlayerControl {

    final static GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void keyPress(TickEvent.PlayerTickEvent event) {
        final EntityPlayer player = event.player;

        if (player.isRiding()) {
            Entity ridingEntity = player.getRidingEntity();
            assert ridingEntity != null;
            if (!(ridingEntity instanceof EntityLiving))
                return;
            final Vec3d forward = player.getForward();

            if (gameSettings.keyBindForward.isKeyDown()) {
                ridingEntity.motionX = forward.x * 1;
                ridingEntity.motionZ = forward.z * 1;
            }
            if (gameSettings.keyBindBack.isKeyDown()) {
                ridingEntity.motionX = -forward.x * 1;
                ridingEntity.motionZ = -forward.z * 1;
            }

            if (gameSettings.keyBindLeft.isKeyDown()) {
                ridingEntity.motionX = forward.z * 1;
                ridingEntity.motionZ = -forward.x * 1;
            }

            if (gameSettings.keyBindRight.isKeyDown()) {
                ridingEntity.motionX = -forward.z * 1;
                ridingEntity.motionZ = forward.x * 1;
            }
            if (gameSettings.keyBindJump.isKeyDown()) {
                if (ridingEntity.onGround)
                    ridingEntity.motionY = 1;
            }
            if (gameSettings.keyBindSneak.isKeyDown()) {
                player.dismountRidingEntity();
            }

        }
    }
}
