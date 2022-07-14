package net.sydokiddo.endlessencore.mixin.elytra_tweaks;

import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.sydokiddo.endlessencore.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Changes the Elytra repair ingredient to Chitin

@Mixin(ElytraItem.class)
public class ElytraChitinRepairingMixin {

    @Inject(at = @At("RETURN"), method = "canRepair", cancellable = true)
    private void canRepair(ItemStack stack, ItemStack ingredient, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(ingredient.is(ModItems.CHITIN));
        }
    }