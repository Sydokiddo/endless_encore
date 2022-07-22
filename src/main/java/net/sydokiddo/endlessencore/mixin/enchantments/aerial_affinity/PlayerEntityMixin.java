package net.sydokiddo.endlessencore.mixin.enchantments.aerial_affinity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.sydokiddo.endlessencore.enchantment.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Allows for the player to mine at normal speed when not touching the ground if they have the Aerial Affinity enchantment

@Mixin(Player.class)
abstract class PlayerEntityMixin extends LivingEntity {

    @Inject(method = "getDestroySpeed",at = @At("RETURN"),cancellable = true)
    private void aerialAffinity(BlockState block, CallbackInfoReturnable<Float> cir){
        int aerialAffinityLevel = Math.max(0, Math.max(EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.AERIAL_AFFINITY, this.getItemBySlot(EquipmentSlot.CHEST)),
        EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.AERIAL_AFFINITY, this.getItemBySlot(EquipmentSlot.CHEST))));
        if (this.isFallFlying() && aerialAffinityLevel > 0)
            cir.setReturnValue(cir.getReturnValue() * 2);
        if (!this.isOnGround() && aerialAffinityLevel > 0) {
            cir.setReturnValue(cir.getReturnValue() * 2);
            }
        }

    public PlayerEntityMixin(EntityType<? extends LivingEntity> type, Level world) {
        super(type, world);
    }
}