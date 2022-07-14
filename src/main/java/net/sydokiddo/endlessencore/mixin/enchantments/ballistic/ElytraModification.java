package net.sydokiddo.endlessencore.mixin.enchantments.ballistic;

import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.sydokiddo.endlessencore.enchantment.BallisticEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
@SuppressWarnings("ConstantConditions")
public abstract class ElytraModification {

    // Mixin to allow the Ballistic enchantment to be put onto Elytra

    @Final
    public EnchantmentCategory type;
    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    public void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Enchantment enchantment = (Enchantment) (Object) this;
        if (enchantment instanceof BallisticEnchantment) {
            if (!(stack.getItem() instanceof ElytraItem)) return;
            cir.setReturnValue(true);
        }
    }
}