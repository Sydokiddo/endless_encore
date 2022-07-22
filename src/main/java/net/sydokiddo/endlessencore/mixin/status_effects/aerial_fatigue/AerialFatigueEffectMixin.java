package net.sydokiddo.endlessencore.mixin.status_effects.aerial_fatigue;

import net.minecraft.world.entity.LivingEntity;
import net.sydokiddo.endlessencore.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Prevents the user from gliding if they have the Aerial Fatigue status effect

@Mixin(LivingEntity.class)
public abstract class AerialFatigueEffectMixin {

    @Inject(at = @At("HEAD"), method = "updateFallFlying", cancellable = true)
    private void updateFallFlying(CallbackInfo ci) {
        LivingEntity player = (LivingEntity) (Object) this;
        if (player.hasEffect(ModEffects.AERIAL_FATIGUE)) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "isFallFlying", cancellable = true)
    private void isFallFlying(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity player = (LivingEntity) (Object) this;
        if (player.hasEffect(ModEffects.AERIAL_FATIGUE)) {
            cir.setReturnValue(false);
        }
    }
}