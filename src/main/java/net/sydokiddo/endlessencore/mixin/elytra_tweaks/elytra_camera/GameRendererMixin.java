package net.sydokiddo.endlessencore.mixin.elytra_tweaks.elytra_camera;

import net.sydokiddo.endlessencore.util.CameraSetup;
import net.sydokiddo.endlessencore.util.ElytraCameraHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.GameRenderer;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(
            at = @At(
                    value = "INVOKE", target = "Lnet/minecraft/client/Camera;setup(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/world/entity/Entity;ZZF)V", shift = Shift.AFTER
            ), method = "renderLevel(FJLcom/mojang/blaze3d/vertex/PoseStack;)V"
    )
    private void elytracamera$renderLevel(float pPartialTicks, long pFinishTimeNano, PoseStack pMatrixStack, CallbackInfo ci) {
        CameraSetup cameraSetup = new CameraSetup(((GameRenderer) (Object) this).getMainCamera());
        ElytraCameraHandler.cameraSetup(cameraSetup);
        pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(cameraSetup.getRoll()));
    }
}