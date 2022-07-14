package net.sydokiddo.endlessencore.mixin.sickles;

import net.sydokiddo.endlessencore.util.PlayerAccess;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.sydokiddo.endlessencore.item.custom_items.SickleItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = Player.class, priority = 1001)
public class PlayerEntityMixin implements PlayerAccess {

    private int lastAttackedOffhandTicks;
    @Nullable
    private Entity target;
    private boolean offHandAttack;

    @Inject(method = "tick()V", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/player/Player;attackStrengthTicker:I", ordinal = 0))
    private void tickMixin(CallbackInfo info) {
        lastAttackedOffhandTicks++;
    }

    @Override
    public void resetLastOffhandAttackTicks() {
        lastAttackedOffhandTicks = 0;
    }

    @SuppressWarnings("ALL")
    @ModifyVariable(method = "attack", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getDamageBonus(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/MobType;)F"), ordinal = 0, require = 0)
    private float attackDamageMixin(float original) {
        Item item = ((Player) (Object) this).getOffhandItem().getItem();
        if (this.offHandAttack) {
            if (item instanceof SickleItem sickleItem) {
                return sickleItem.getDamage() + 1F;
            } else {
                SickleItem sickleItem = (SickleItem) item;
                assert false;
                return sickleItem.getDamage() + 1F;
            }
        } else
            return original;
    }

    @ModifyVariable(method = "attack", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/entity/player/Player;getAttackStrengthScale(F)F", shift = Shift.BEFORE), ordinal = 1, require = 0)
    private float attackEnchantmentDamageMixin(float original) {
        ItemStack itemStack = ((Player) (Object) this).getOffhandItem();
        if (this.offHandAttack) {
            float h;
            if (this.target != null && this.target instanceof LivingEntity) {
                h = EnchantmentHelper.getDamageBonus(itemStack, ((LivingEntity) target).getMobType());
            } else {
                h = EnchantmentHelper.getDamageBonus(itemStack, MobType.UNDEFINED);
            }
            return h;
        } else
            return original;
    }

    @ModifyVariable(method = "attack", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/entity/player/Player;getAttackStrengthScale(F)F"), ordinal = 2, require = 0)
    private float cooldownProgressMixin(float original) {
        if (this.offHandAttack) {
            return this.getAttackCooldownProgressOffhand(0.5F);
        } else
            return original;
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;resetAttackStrengthTicker()V"))
    private void attackResetLastAttackedTicksMixin(CallbackInfo info) {
        if (this.offHandAttack) {
            this.resetLastOffhandAttackTicks();
        }
    }

    @Inject(method = "resetAttackStrengthTicker", at = @At(value = "HEAD"), cancellable = true)
    private void resetLastAttackedTicksMixin(CallbackInfo info) {
        if (this.offHandAttack) {
            info.cancel();
        }
    }

    @Redirect(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;hurtEnemy(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/player/Player;)V"), require = 0)
    private void attackPostHitMixin(ItemStack itemstack, LivingEntity livingEntity, Player playerEntity) {
        if (this.offHandAttack) {
            playerEntity.getOffhandItem().hurtEnemy(livingEntity, playerEntity);
        } else
            itemstack.hurtEnemy(livingEntity, playerEntity);
    }

    @Inject(method = "attack", at = @At(value = "TAIL"))
    public void attackMixin(CallbackInfo info) {
        if (this.offHandAttack) {
            this.offHandAttack = false;
        }
    }

    @Inject(method = "attack", at = @At(value = "HEAD"))
    public void attackMixin(Entity target, CallbackInfo info) {
        if (target.isAttackable()) {
            this.target = target;
        }
        if (target instanceof EnderDragonPart) {
            this.target = ((EnderDragonPart) target).parentMob;
        }
    }

    @Inject(method = "sweepAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    public void spawnSweepAttackParticles(CallbackInfo info, double d, double e) {
        if (this.offHandAttack) {
            Player playerEntity = (Player) (Object) this;
            ((ServerLevel) playerEntity.level).sendParticles(ParticleTypes.SWEEP_ATTACK, playerEntity.getX() + d, playerEntity.getY(0.5D), playerEntity.getZ() + e, 0, d, 0.0D, e, 0.0D);
            info.cancel();
        }
    }

    public float getAttackCooldownProgressPerTick() {
        return 1.0F;
    }

    @Override
    public void setOffhandAttack() {
        this.offHandAttack = true;
    }

    @Override
    public boolean isOffhandAttack() {
        return this.offHandAttack;
    }

    @Override
    public float getAttackCooldownProgressOffhand(float baseTime) {
        return Mth.clamp(((float) this.lastAttackedOffhandTicks + baseTime) / this.getAttackCooldownProgressPerTick(), 0.0F, 1.0F);
    }
}