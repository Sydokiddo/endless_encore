package net.sydokiddo.endlessencore.mixin.enchantments.ballistic;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.enchantment.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Mixin to deal damage to enemies when the player darts into them with Elytra enchanted with Ballistic

@Mixin(Player.class)
public class BallisticEnchantmentMixin {

    @Inject(at = @At("RETURN"), method = "collideWithEntity")
    private void collideWithEntity(Entity entity, CallbackInfo ci) {
        LivingEntity player = (LivingEntity) (Object) this;
        int ballisticLevel = Math.max(0, Math.max(EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BALLISTIC, player.getItemBySlot(EquipmentSlot.CHEST)), EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BALLISTIC, player.getItemBySlot(EquipmentSlot.CHEST))));
        if (player instanceof ServerPlayer && (player.isFallFlying()) && (!player.isOnGround()) && (player.getDeltaMovement().horizontalDistance() >= 1)) {
            if (ballisticLevel == 1) {
                entity.hurt(new EndlessEncore.BallisticDamageSource(player), 4.0f);
            }
            if (ballisticLevel == 2) {
                entity.hurt(new EndlessEncore.BallisticDamageSource(player), 6.5f);
            }
            if (ballisticLevel >= 3) {
                entity.hurt(new EndlessEncore.BallisticDamageSource(player), 9.0f);
            }
        }
    }
}