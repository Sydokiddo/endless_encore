package net.sydokiddo.endlessencore.mixin.client;

import net.sydokiddo.endlessencore.access.PlayerAccess;
import net.sydokiddo.endlessencore.item.custom_items.SickleItem;
import net.sydokiddo.endlessencore.network.PlayerAttackPacket;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

// Sends Packets if Using Sickles

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow
    @Nullable
    public ClientPlayerEntity player;
    @Shadow
    @Nullable
    public ClientPlayerInteractionManager interactionManager;
    @Shadow
    @Nullable
    public HitResult crosshairTarget;
    @Shadow
    private int itemUseCooldown;
    private int secondAttackCooldown;
    private boolean attackedOffhand;

    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;handleInputEvents()V"))
    public void tickMixin(CallbackInfo info) {
        if (this.secondAttackCooldown > 0) {
            --this.secondAttackCooldown;
        }
        if (this.attackedOffhand) {
            this.itemUseCooldown = 4;
        }
    }

    @Inject(method = "doItemUse", at = @At(value = "HEAD"), cancellable = true)
    private void doItemUseMixin(CallbackInfo info) {
        assert player != null;
        Item offHandItem = player.getOffHandStack().getItem();
        Item mainHandItem = player.getMainHandStack().getItem();

        if (player != null && !player.isSpectator() && (offHandItem instanceof SickleItem)
                && (mainHandItem instanceof SickleItem)) {
            if (this.secondAttackCooldown <= 0) {
                if (this.crosshairTarget != null && !this.player.isRiding()) {
                    switch (this.crosshairTarget.getType()) {
                        case ENTITY:
                            ((PlayerAccess) player).setOffhandAttack();
                            ((PlayerAccess) player).resetLastOffhandAttackTicks();
                            player.attack(((EntityHitResult) this.crosshairTarget).getEntity());
                            Objects.requireNonNull(MinecraftClient.getInstance().getNetworkHandler()).sendPacket(PlayerAttackPacket.attackPacket(((EntityHitResult) this.crosshairTarget).getEntity()));
                            break;
                // Allows for chests and other blocks to be opened/used if the player has a sickle in their off hand:
                        case BLOCK:
                            BlockHitResult blockHitResult = (BlockHitResult) this.crosshairTarget;
                            BlockPos blockPos = blockHitResult.getBlockPos();
                            if (!player.world.getBlockState(blockPos).isAir()) {
                                assert this.interactionManager != null;
                                this.interactionManager.interactBlock(player, Hand.OFF_HAND, blockHitResult);
                                break;
                            }
                        case MISS:
                            assert this.interactionManager != null;
                            if (this.interactionManager.hasLimitedAttackSpeed()) {
                                this.secondAttackCooldown = 10;
                            }
                            ((PlayerAccess) player).resetLastOffhandAttackTicks();
                    }
                    attackedOffhand = true;
                    this.player.swingHand(Hand.OFF_HAND);
                    info.cancel();
                }
            } else {
                info.cancel();
            }
        } else if (this.attackedOffhand) {
            this.attackedOffhand = false;
        }
    }

}