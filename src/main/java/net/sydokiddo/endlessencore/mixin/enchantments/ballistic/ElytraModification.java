package net.sydokiddo.endlessencore.mixin.enchantments.ballistic;

import EnchantmentTarget;
import net.minecraft.enchantment.*;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.sydokiddo.endlessencore.enchantment.BallisticEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
@SuppressWarnings("ConstantConditions")
public abstract class ElytraModification {

    // Mixin to allow the Ballistic enchantment to be put onto Elytra

    @Shadow
    @Final
    public EnchantmentTarget type;
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    public void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Enchantment enchantment = (Enchantment) (Object) this;
        if (enchantment instanceof BallisticEnchantment) {
            if (!(stack.getItem() instanceof ElytraItem)) return;
            cir.setReturnValue(true);
        }
    }
}