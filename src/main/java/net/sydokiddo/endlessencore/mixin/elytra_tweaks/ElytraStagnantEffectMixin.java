package net.sydokiddo.endlessencore.mixin.elytra_tweaks;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.sydokiddo.endlessencore.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Prevents the user from gliding if they have the Stagnant status effect

@Mixin(LivingEntity.class)
public class ElytraStagnantEffectMixin {

    @Inject(at = @At("RETURN"), method = "tickFallFlying", cancellable = true)
    private void tickFallFlying(CallbackInfo ci) {
        LivingEntity player = (LivingEntity) (Object) this;
        if (player instanceof ServerPlayerEntity && player.hasStatusEffect(ModEffects.STAGNANT)) {
            ci.cancel();
        }
        if (player.isSneaking()) {
            ci.cancel();
        }
    }
}