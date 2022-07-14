package net.sydokiddo.endlessencore.mixin.sickles;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.sydokiddo.endlessencore.item.custom_items.SickleItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayer.class)

public class ServerPlayerEntityMixin {

    // Allows for the player to swing their off-hand if holding 2 sickles

    @Inject(method = "swing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;swing(Lnet/minecraft/world/InteractionHand;)V", shift = Shift.AFTER), cancellable = true)
    private void swingHandMixin(InteractionHand hand, CallbackInfo info) {
        Item item = ((Player) (Object) this).getOffhandItem().getItem();
        if (hand == InteractionHand.OFF_HAND && (item instanceof SickleItem))
            info.cancel();
    }
}