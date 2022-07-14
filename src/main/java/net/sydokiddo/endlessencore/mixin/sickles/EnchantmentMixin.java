package net.sydokiddo.endlessencore.mixin.sickles;

import java.util.Map;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.sydokiddo.endlessencore.util.PlayerAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "getEquipment", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void getEquipment(LivingEntity entity, CallbackInfoReturnable<Map<EquipmentSlot, ItemStack>> info, Map<EquipmentSlot, ItemStack> map) {
        if (entity instanceof Player) {
            if (((PlayerAccess) entity).isOffhandAttack() && map.containsKey(EquipmentSlot.MAINHAND)) {
                map.remove(EquipmentSlot.MAINHAND);
                map.put(EquipmentSlot.OFFHAND, entity.getOffhandItem());
            }
        }
    }
}