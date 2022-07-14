package net.sydokiddo.endlessencore.mixin.peroratite;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.sydokiddo.endlessencore.misc.EndlessEncoreTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class PeroratiteBlastProofMixin {

    // Makes Peroratite related items immune to explosion damage

    @Inject(method = "isInvulnerableTo(Lnet/minecraft/entity/damage/DamageSource;)Z", at = @At("RETURN"), cancellable = true)
    protected void isInvulnerableTo(DamageSource source, CallbackInfoReturnable<Boolean> ci) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof ItemEntity item && item.getItem().is(EndlessEncoreTags.GRAVITY_DISOBEYING_ITEMS)) {
            ci.setReturnValue(ci.getReturnValue()
            || (!item.getItem().isEmpty() && source.isExplosion()));
        }
    }
}