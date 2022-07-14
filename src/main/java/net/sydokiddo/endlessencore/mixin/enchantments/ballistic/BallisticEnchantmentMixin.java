package net.sydokiddo.endlessencore.mixin.enchantments.ballistic;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.enchantment.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Mixin to deal damage to enemies when the player darts into them with Elytra enchanted with Ballistic

@Mixin(PlayerEntity.class)
public class BallisticEnchantmentMixin {

    @Inject(at = @At("RETURN"), method = "collideWithEntity")
    private void collideWithEntity(Entity entity, CallbackInfo ci) {
        LivingEntity player = (LivingEntity) (Object) this;
        int ballisticLevel = Math.max(0, Math.max(EnchantmentHelper.getLevel(ModEnchantments.BALLISTIC, player.getEquippedStack(EquipmentSlot.CHEST)), EnchantmentHelper.getLevel(ModEnchantments.BALLISTIC, player.getEquippedStack(EquipmentSlot.CHEST))));
        if (player instanceof ServerPlayerEntity && (player.isFallFlying()) && (!player.isOnGround()) && (player.getVelocity().horizontalLength() >= 1)) {
            if (ballisticLevel == 1) {
                entity.damage(new EndlessEncore.BallisticDamageSource(player), 4.0f);
            }
            if (ballisticLevel == 2) {
                entity.damage(new EndlessEncore.BallisticDamageSource(player), 6.5f);
            }
            if (ballisticLevel >= 3) {
                entity.damage(new EndlessEncore.BallisticDamageSource(player), 9.0f);
            }
        }
    }
}