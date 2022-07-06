package net.sydokiddo.endlessencore.mixin.sickles;

import java.util.Map;
import net.sydokiddo.endlessencore.util.PlayerAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "getEquipment", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void getEquipment(LivingEntity entity, CallbackInfoReturnable<Map<EquipmentSlot, ItemStack>> info, Map<EquipmentSlot, ItemStack> map) {
        if (entity instanceof PlayerEntity) {
            if (((PlayerAccess) entity).isOffhandAttack() && map.containsKey(EquipmentSlot.MAINHAND)) {
                map.remove(EquipmentSlot.MAINHAND);
                map.put(EquipmentSlot.OFFHAND, entity.getOffHandStack());
            }
        }
    }
}