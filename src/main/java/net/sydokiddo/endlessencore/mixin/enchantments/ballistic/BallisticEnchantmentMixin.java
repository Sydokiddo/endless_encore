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

    @Inject(at = @At("RETURN"), method = "touch")
    private void collideWithEntity(Entity entity, CallbackInfo ci) {
        LivingEntity player = (LivingEntity) (Object) this;
        int ballisticLevel = Math.max(0, Math.max(EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BALLISTIC, player.getItemBySlot(EquipmentSlot.CHEST)),
                EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BALLISTIC, player.getItemBySlot(EquipmentSlot.CHEST))));
        if (player instanceof ServerPlayer && (player.isFallFlying()) && (!player.isOnGround()) && (player.getDeltaMovement().horizontalDistance() >= 1) && entity instanceof LivingEntity) {
            if (ballisticLevel == 1) {
                entity.hurt(new EndlessEncore.BallisticDamageSource(player), 3.0f);
                ((LivingEntity) entity).knockback(0.5D, entity.getX(), entity.getZ());
            }
            if (ballisticLevel == 2) {
                entity.hurt(new EndlessEncore.BallisticDamageSource(player), 5.0f);
                ((LivingEntity) entity).knockback(1.0D, entity.getX(), entity.getZ());
            }
            if (ballisticLevel >= 3) {
                entity.hurt(new EndlessEncore.BallisticDamageSource(player), 7.0f);
                ((LivingEntity) entity).knockback(1.5D, entity.getX(), entity.getZ());
            }
        }
    }
}