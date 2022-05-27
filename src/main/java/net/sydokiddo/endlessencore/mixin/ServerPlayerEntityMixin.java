package net.sydokiddo.endlessencore.mixin;

import net.sydokiddo.endlessencore.item.custom_items.SickleItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    // Allows for the player to swing their off-hand if holding 2 sickles

    @Inject(method = "swingHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;swingHand(Lnet/minecraft/util/Hand;)V", shift = Shift.AFTER), cancellable = true)
    private void swingHandMixin(Hand hand, CallbackInfo info) {
        Item item = ((PlayerEntity) (Object) this).getOffHandStack().getItem();
        if (hand == Hand.OFF_HAND && (item instanceof SickleItem))
            info.cancel();
    }

}