package net.sydokiddo.endlessencore.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

// Aerial Fatigue prevents the user from using Elytra
// Most of the code is in the Mixins folder

public class AerialFatigueEffect extends MobEffect {
    public AerialFatigueEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer) {
            ((ServerPlayer) pLivingEntity).stopFallFlying();
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
