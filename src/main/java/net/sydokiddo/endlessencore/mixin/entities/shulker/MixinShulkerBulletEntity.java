package net.sydokiddo.endlessencore.mixin.entities.shulker;

import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.event.GameEvent;
import net.sydokiddo.endlessencore.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;

// Shulker Bullets die alongside the Shulker that initially summoned them

@Mixin(ShulkerBulletEntity.class)
public abstract class MixinShulkerBulletEntity {

    public ServerWorld world;

    @Inject(at = @At("HEAD"), method = "tick()V", cancellable = true)
    public void tick(CallbackInfo ci) {
        Object self = this;
        if (!((Entity) self).world.isClient) {
            Entity owner = ((ProjectileEntity) self).getOwner();
            if (owner == null || !owner.isAlive()) {
                ((ProjectileEntity) self).playSoundIfNotSilent(SoundEvents.ENTITY_SHULKER_BULLET_HURT);
                var item = new ItemStack(ModItems.FRACTURED_SHULKER_BULLET);
                ((ProjectileEntity) self).dropItem(item.getItem());
                ((ProjectileEntity) self).emitGameEvent(GameEvent.ENTITY_DAMAGE);
                ((Entity) self).discard();
                ci.cancel();
            }
        }
    }
}