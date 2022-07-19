package net.sydokiddo.endlessencore.mixin.enchantments.tempo_sprint;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.sydokiddo.endlessencore.enchantment.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class TempoSprintEnchantmentMixin {

    @Inject(method = "getSpeed", at = @At("RETURN"))
    private void tempoSprint(CallbackInfoReturnable<Float> cir) {
        LivingEntity player = (LivingEntity) (Object) this;
        int tempoSprintLevel = Math.max(0, Math.max(EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.TEMPO_SPRINT, player.getItemBySlot(EquipmentSlot.LEGS)),
        EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.TEMPO_SPRINT, player.getItemBySlot(EquipmentSlot.LEGS))));
        if (player instanceof ServerPlayer && player.isSprinting()) {
            if (tempoSprintLevel == 1) {
                player.setSpeed(1.0f);
            }
            if (tempoSprintLevel == 2) {
                player.setSpeed(2.0f);
            }
            if (tempoSprintLevel >= 3) {
                player.setSpeed(3.0f);
            }
        }
    }
}