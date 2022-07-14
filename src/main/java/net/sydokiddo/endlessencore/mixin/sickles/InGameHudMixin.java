package net.sydokiddo.endlessencore.mixin.sickles;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.sydokiddo.endlessencore.util.PlayerAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.fabricmc.api.EnvType;

// Renders Attack Indicator HUD for Off-Hand Weapon

@Environment(EnvType.CLIENT)
@Mixin(Gui.class)
public abstract class InGameHudMixin extends GuiComponent {


    @Shadow private int screenWidth;
    @Shadow private int screenHeight;
    @Final
    @Mutable
    private final Minecraft client;
    public InGameHudMixin(Minecraft client) {
        this.client = client;
    }

    @Inject(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getAttackStrengthScale(F)F", shift = Shift.AFTER))
    private void renderCrosshairMixinTEST(PoseStack matrices, CallbackInfo info) {
        assert this.client.player != null;
        float o = ((PlayerAccess) this.client.player).getAttackCooldownProgressOffhand(1.0F);
        if (o < 1.0F) {
            int u = (int) (o * 17.0F);
            RenderSystem.setShaderTexture(0, new ResourceLocation("endlessencore:textures/gui/crosshair_indicator.png"));
            GuiComponent.blit(matrices, this.screenWidth / 2 - 8, this.screenHeight / 2 - 7 + 16, 0.0F, 0.0F, u, 4, 16, 16);
        }
    }

    @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getAttackStrengthScale(F)F", shift = Shift.AFTER))
    private void renderHotbar(float tickDelta, PoseStack matrices, CallbackInfo info) {
        assert this.client.player != null;
        float o = ((PlayerAccess) this.client.player).getAttackCooldownProgressOffhand(1.0F);
        if (o < 1.0F) {
            HumanoidArm arm = this.client.player.getMainArm().getOpposite();
            int r = (this.screenWidth / 2) + 91 + 6;
            if (arm == HumanoidArm.RIGHT) {
                r = (this.screenWidth / 2) - 91 - 22;
            }
            int s = (int) (o * 19.0F);
            RenderSystem.setShaderTexture(0, new ResourceLocation("endlessencore:textures/gui/hotbar_indicator.png"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            GuiComponent.blit(matrices, r, this.screenHeight - 20 + 18 - s, 0.0F, 18.0F - s, 18, s, 32, 32);
        }
    }
}