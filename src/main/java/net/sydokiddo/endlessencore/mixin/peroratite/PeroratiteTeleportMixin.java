package net.sydokiddo.endlessencore.mixin.peroratite;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.sydokiddo.endlessencore.misc.EndlessEncoreTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class PeroratiteTeleportMixin extends Entity {


    @Shadow public abstract ItemStack getItem();

    protected PeroratiteTeleportMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void damageItem(CallbackInfo info) {
        // If a Peroratite item falls into the void, it will teleport upwards 45 blocks so that it doesn't get lost
        if (getItem().is(EndlessEncoreTags.GRAVITY_DISOBEYING_ITEMS) && (this.getY() < this.level.getMinBuildHeight())) {
            this.unsetRemoved();
            this.teleportTo(this.getX(), this.level.getMinBuildHeight()+45, this.getZ());
            this.setDeltaMovement(0, 0, 0);
            this.setNoGravity(true);
        }
    }
}