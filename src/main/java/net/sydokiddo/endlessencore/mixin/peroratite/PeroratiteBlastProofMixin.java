package net.sydokiddo.endlessencore.mixin.peroratite;

import net.sydokiddo.endlessencore.misc.EndlessEncoreTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;

@Mixin(Entity.class)
public abstract class PeroratiteBlastProofMixin {

    // Makes Peroratite related items immune to explosion damage

    @Inject(method = "isInvulnerableTo(Lnet/minecraft/entity/damage/DamageSource;)Z", at = @At("RETURN"), cancellable = true)
    protected void isInvulnerableTo(DamageSource source, CallbackInfoReturnable<Boolean> ci) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof ItemEntity item && item.getStack().isIn(EndlessEncoreTags.GRAVITY_DISOBEYING_ITEMS)) {
            ci.setReturnValue(ci.getReturnValue()
            || (!item.getStack().isEmpty() && source.isExplosive()));
        }
    }
}