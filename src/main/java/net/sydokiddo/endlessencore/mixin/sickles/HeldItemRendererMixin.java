package net.sydokiddo.endlessencore.mixin.sickles;

import net.sydokiddo.endlessencore.util.PlayerAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.fabricmc.api.EnvType;

// Renders the off-hand swinging when right-clicked with 2 sickles

@Environment(EnvType.CLIENT)
@Mixin(ItemInHandRenderer.class)
public class HeldItemRendererMixin {


    @Shadow private float mainHandHeight;
    @Shadow private float offHandHeight;
    @Mutable
    @Final
    private final Minecraft client;
    private ItemStack offHand;
    private float equipOffhand;
    private boolean isOffhandAttack;

    public HeldItemRendererMixin(Minecraft client) {
        this.client = client;
    }

    @Inject(method = "tick", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/util/Mth;clamp(FFF)F", ordinal = 3, shift = Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void updateHeldItemsMixin(CallbackInfo info, LocalPlayer clientPlayerEntity, ItemStack itemStack, ItemStack itemStack2) {
        assert this.client != null;
        assert this.client.player != null;
        float o = ((PlayerAccess) clientPlayerEntity).getAttackCooldownProgressOffhand(1.0F);
        if (o < 0.1F) this.isOffhandAttack = true;
        if (this.isOffhandAttack) {
            if (this.mainHandHeight >= 1.0F) {
                this.isOffhandAttack = false;
            }
            this.equipOffhand += Mth.clamp((this.offHand == itemStack2 ? o * o * o : 0.0F) - this.equipOffhand, -0.4F, 0.4F);
            this.offHandHeight = this.equipOffhand;
        }
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getAttackStrengthScale(F)F"))
    public void updateHeldMainhandMixin(CallbackInfo info) {
        assert this.client != null;
        assert this.client.player != null;
        float o = ((PlayerAccess) client.player).getAttackCooldownProgressOffhand(1.0F);
        if (o < 0.9F && o > 0.15F)
            this.offHand = new ItemStack(Items.AIR);

    }
}