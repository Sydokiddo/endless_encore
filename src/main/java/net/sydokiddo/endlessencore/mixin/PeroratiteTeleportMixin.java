package net.sydokiddo.endlessencore.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.sydokiddo.endlessencore.misc.EndlessEncoreTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class PeroratiteTeleportMixin extends Entity {

    @Shadow
    public abstract ItemStack getStack();

    protected PeroratiteTeleportMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    PlayerEntity p = MinecraftClient.getInstance().player;

    @Inject(at = @At("HEAD"), method = "tick")
    private void damageItem(CallbackInfo info) {
        // If a Peroratite item falls into the void, it will teleport upwards 30 blocks so that it doesn't get lost
        if (getStack().isIn(EndlessEncoreTags.GRAVITY_DISOBEYING_ITEMS) && (this.getY() < this.world.getBottomY())) {
            this.unsetRemoved();
            this.requestTeleport(this.getX(), this.world.getBottomY()+30, this.getZ());
            this.setVelocity(0, 0, 0);
            this.setNoGravity(true);
            double x = p.getX(), y = p.getY(), z = p.getZ();
            world.playSound(p, x, y, z, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL, 0.5F, 1.0F);
        }
    }
}