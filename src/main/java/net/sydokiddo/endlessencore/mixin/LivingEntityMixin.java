package net.sydokiddo.endlessencore.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow protected int itemUseTimeLeft;
    private int elytrabounce$timer = 0;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Redirect(method = "travel(Lnet/minecraft/util/math/Vec3d;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"))
    public void travel(LivingEntity entity, int idx, boolean val) { }

    @Redirect(method = "tickFallFlying",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"))
    public void initAi(LivingEntity entity, int idx, boolean val) {
        if (entity.getVelocity().y == 0) {
            if (elytrabounce$timer > 1)
                ((EntityAccessor) entity).callSetFlag(7, val);
            elytrabounce$timer += 1;
        } else {
            elytrabounce$timer = 0;
        }
        LivingEntity player = (LivingEntity) (Object) this;
        if (player instanceof ServerPlayerEntity && player.isFallFlying() && player.isSneaking()) {
            ((ServerPlayerEntity) player).stopFallFlying();
            double x = player.getX(), y = player.getY(), z = player.getZ();
            PlayerEntity p = MinecraftClient.getInstance().player;
            world.playSound(p, x, y, z, ModSoundEvents.ELYTRA_CLOSE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }
}