package net.sydokiddo.endlessencore.mixin.entities.ender_dragon;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.sydokiddo.endlessencore.EndlessEncore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Ender Dragon Tweaks

@Mixin(EnderDragon.class)
public class EnderDragonMixin {

    // Makes the Ender Dragon immune to explosive damage
    // No more bed bombing, have fun!

    @Inject(at = @At("HEAD"), method = "hurt(Lnet/minecraft/world/entity/boss/EnderDragonPart;Lnet/minecraft/world/damagesource/DamageSource;F)Z", cancellable = true)
    public void hurt(EnderDragonPart enderDragonPart, DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
        if (EndlessEncore.getConfig().upgraded_dragon_fight) {
            if (damageSource.isExplosion()) {
                cir.setReturnValue(false);
            }
        }
    }

        // Buffs the Ender Dragon's health from 200 to 400

        @Inject(at = @At("HEAD"), method = "createAttributes", cancellable = true)
        private static void createAttributes (CallbackInfoReturnable < AttributeSupplier.Builder > cir) {
        if (EndlessEncore.getConfig().upgraded_dragon_fight) {
            cir.setReturnValue(Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 400.0D));
        }
    }
}
