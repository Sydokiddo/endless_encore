package net.sydokiddo.endlessencore.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.sydokiddo.endlessencore.EndlessEncore;

@Environment(EnvType.CLIENT)
public class ElytraCameraHandler {

    // Elytra camera tilting code
    // Forked and ported to 1.19 from Nimble, a Fabric 1.18 mod by Snowzee which has an MIT licence
    // https://www.curseforge.com/minecraft/mc-mods/nimble-fabric

    static boolean elytraFlying = false;
    static float roll;

    public static void cameraSetup(CameraSetup event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.isPaused())
            return;
        if (mc.player == null || mc.player.isSleeping())
            return;
        if (!EndlessEncore.getConfig().elytra_camera) {
            return;
        }

        if (mc.player.isFallFlying()) {
            if (EndlessEncore.getConfig().elytra_camera) {
                float pTicks = Minecraft.getInstance().getFrameTime();
                Vec3 look = mc.player.getViewVector(pTicks);
                look = new Vec3(look.x, 0, look.z);
                Vec3 motion = mc.player.getDeltaMovement();
                Vec3 move = new Vec3(motion.x, 0, motion.z).normalize();
                float nRoll = (float) look.cross(move).y * 16;
                roll = Mth.lerp(pTicks, roll, nRoll);
                event.setRoll(roll);
            }

            // Sometimes if the game is too laggy, the specific tick may be skipped

            if (EndlessEncore.getConfig().elytra_camera && mc.player.getFallFlyingTicks() >= 10) {
                elytraFlying = true;
            } else if (EndlessEncore.getConfig().elytra_camera && elytraFlying) {
                elytraFlying = false;
            }
        }
    }
}