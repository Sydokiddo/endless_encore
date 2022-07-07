package net.sydokiddo.endlessencore.mixin.status_effects.aerial_fatigue;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.sydokiddo.endlessencore.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Prevents the user from gliding if they have the Aerial Fatigue status effect

@Mixin(LivingEntity.class)
public class AerialFatigueEffectMixin {

    @Inject(at = @At("RETURN"), method = "tickFallFlying", cancellable = true)
    private void tickFallFlying(CallbackInfo ci) {
        LivingEntity player = (LivingEntity) (Object) this;
        if (player instanceof ServerPlayerEntity && player.hasStatusEffect(ModEffects.AERIAL_FATIGUE)) {
            ci.cancel();
        }
        if (player.isSneaking()) {
            ci.cancel();
        }
    }
}