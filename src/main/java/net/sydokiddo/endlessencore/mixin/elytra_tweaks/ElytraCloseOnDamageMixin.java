package net.sydokiddo.endlessencore.mixin.elytra_tweaks;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.sydokiddo.endlessencore.effect.ModEffects;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Forcefully closes Elytra and gives the Aerial Fatigue status effect for 5 seconds when a player takes damage while fall flying

@Mixin(PlayerEntity.class)
public class ElytraCloseOnDamageMixin {

    @Inject(at = @At("RETURN"), method = "damage")
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity player = (LivingEntity) (Object) this;
        if (player instanceof ServerPlayerEntity && player.isFallFlying() && !((ServerPlayerEntity) player).isCreative()) {
            ((ServerPlayerEntity) player).stopFallFlying();
            ((ServerPlayerEntity) player).playSound(ModSoundEvents.PLAYER_ELYTRA_CLOSE, SoundCategory.PLAYERS, 1.0f, 1.0f);
            player.addStatusEffect(new StatusEffectInstance(ModEffects.AERIAL_FATIGUE, 100, 0, false, false, true));
            ItemStack stack = player.getEquippedStack(EquipmentSlot.CHEST);
            stack.damage(2, player, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
        }
    }
}