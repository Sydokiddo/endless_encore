package net.sydokiddo.endlessencore.mixin.elytra_tweaks;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.effect.ModEffects;
import net.sydokiddo.endlessencore.misc.ModGameEvents;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Forcefully closes Elytra and gives the Aerial Fatigue status effect for 5 seconds when a player takes damage while fall flying

@Mixin(Player.class)
public class ElytraCloseOnDamageMixin {

    @Inject(at = @At("RETURN"), method = "hurt")
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity player = (LivingEntity) (Object) this;
        if (EndlessEncore.getConfig().elytra_changes.aerial_fatigue) {
            if (player instanceof ServerPlayer && player.isFallFlying() && !((ServerPlayer) player).isCreative()) {
                ((ServerPlayer) player).stopFallFlying();
                ((ServerPlayer) player).playNotifySound(ModSoundEvents.PLAYER_ELYTRA_CLOSE, SoundSource.PLAYERS, 1.0f, 1.0f);
                player.addEffect(new MobEffectInstance(ModEffects.AERIAL_FATIGUE, 100, 0, false, false, true));
                ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);
                stack.hurtAndBreak(2, player, (e) -> e.broadcastBreakEvent(EquipmentSlot.CHEST));
                player.gameEvent(ModGameEvents.ELYTRA_CLOSE);
            }
        }
    }
}