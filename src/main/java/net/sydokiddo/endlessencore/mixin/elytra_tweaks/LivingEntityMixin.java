package net.sydokiddo.endlessencore.mixin.elytra_tweaks;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.sydokiddo.endlessencore.misc.ModGameEvents;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow
    public abstract boolean hurt(@NotNull DamageSource source, float amount);

    private int elytrabounce$timer = 0;
    LivingEntity player = (LivingEntity) (Object) this;

    // Allows the player to bounce and continue gliding with Elytra

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Redirect(method = "travel(Lnet/minecraft/world/phys/Vec3;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setSharedFlag(IZ)V"))
    public void travel(LivingEntity entity, int idx, boolean val) {
    }

    @Redirect(method = "updateFallFlying",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setSharedFlag(IZ)V"))
    public void initAi(LivingEntity entity, int idx, boolean val) {
        if (entity.getDeltaMovement().y == 0) {
            if (elytrabounce$timer > 1)
                entity.setSharedFlag(7, val);
            elytrabounce$timer += 1;
        } else {
            elytrabounce$timer = 0;
        }

        // Allows the player to close their Elytra when sneaking

        ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);

        if (player instanceof ServerPlayer && player.isFallFlying() && player.isShiftKeyDown()) {
            ((ServerPlayer) player).stopFallFlying();
            player.gameEvent(ModGameEvents.ELYTRA_CLOSE);
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), ModSoundEvents.PLAYER_ELYTRA_CLOSE, this.getSoundSource(), 1.0f, 0.8f + level.random.nextFloat() * 0.4F);
        }

        if (player instanceof ServerPlayer && player.isShiftKeyDown()) {
            ((ServerPlayer) player).stopFallFlying();
        }

        // Adds exhaustion to the player if they bounce on the ground with Elytra

        if (!level.isClientSide() && player.isFallFlying() && player.isOnGround()) {
            ((Player) player).causeFoodExhaustion(0.3f);
        }

        // Prevents the user from gliding when un-equipping Elytra

        if (player instanceof ServerPlayer && !player.getItemBySlot(EquipmentSlot.CHEST).is(Items.ELYTRA)) {
            ((ServerPlayer) player).stopFallFlying();
        }

        // Prevents the user from gliding when Elytra are broken

        if (player instanceof ServerPlayer && player.getItemBySlot(EquipmentSlot.CHEST).is(Items.ELYTRA) && stack.getDamageValue() == 431) {
            ((ServerPlayer) player).stopFallFlying();
        }
    }
}