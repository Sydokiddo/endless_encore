package net.sydokiddo.endlessencore.mixin.entities.shulker;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Shulker Bullets die alongside the Shulker that initially summoned them

@Mixin(ShulkerBullet.class)
public abstract class MixinShulkerBulletEntity {

    public ServerLevel world;

    @Inject(at = @At("HEAD"), method = "tick()V", cancellable = true)
    public void tick(CallbackInfo ci) {
        Object self = this;
        if (!((Entity) self).level.isClientSide && EndlessEncore.getConfig().vanilla_mobs.shulker_changes) {
            Entity owner = ((Projectile) self).getOwner();
            if (owner == null || !owner.isAlive()) {
                ((Projectile) self).playSound(SoundEvents.SHULKER_BULLET_HURT);
                var item = new ItemStack(ModItems.FRACTURED_SHULKER_BULLET);
                ((Projectile) self).spawnAtLocation(item.getItem());
                ((Projectile) self).gameEvent(GameEvent.ENTITY_DAMAGE);
                ((Entity) self).discard();
                ci.cancel();
            }
        }
    }
}