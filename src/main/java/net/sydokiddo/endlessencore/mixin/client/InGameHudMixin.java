package net.sydokiddo.endlessencore.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.sydokiddo.endlessencore.access.PlayerAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;

// Renders Attack Indicator HUD for Off-Hand Weapon

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {

    @Shadow
    @Final
    @Mutable
    private final MinecraftClient client;
    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;

    public InGameHudMixin(MinecraftClient client) {
        this.client = client;
    }

    @Inject(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F", shift = Shift.AFTER))
    private void renderCrosshairMixinTEST(MatrixStack matrices, CallbackInfo info) {
        assert this.client.player != null;
        float o = ((PlayerAccess) this.client.player).getAttackCooldownProgressOffhand(1.0F);
        if (o < 1.0F) {
            int u = (int) (o * 17.0F);
            RenderSystem.setShaderTexture(0, new Identifier("endlessencore:textures/gui/crosshair_indicator.png"));
            DrawableHelper.drawTexture(matrices, this.scaledWidth / 2 - 8, this.scaledHeight / 2 - 7 + 16, 0.0F, 0.0F, u, 4, 16, 16);
        }
    }

    @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F", shift = Shift.AFTER))
    private void renderHotbar(float tickDelta, MatrixStack matrices, CallbackInfo info) {
        assert this.client.player != null;
        float o = ((PlayerAccess) this.client.player).getAttackCooldownProgressOffhand(1.0F);
        if (o < 1.0F) {
            Arm arm = this.client.player.getMainArm().getOpposite();
            int r = (this.scaledWidth / 2) + 91 + 6;
            if (arm == Arm.RIGHT) {
                r = (this.scaledWidth / 2) - 91 - 22;
            }
            int s = (int) (o * 19.0F);
            RenderSystem.setShaderTexture(0, new Identifier("endlessencore:textures/gui/hotbar_indicator.png"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            DrawableHelper.drawTexture(matrices, r, this.scaledHeight - 20 + 18 - s, 0.0F, 18.0F - s, 18, s, 32, 32);
        }
    }

}