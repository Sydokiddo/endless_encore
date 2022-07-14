package net.sydokiddo.endlessencore.mixin.sickles;

import net.sydokiddo.endlessencore.util.PlayerAccess;
import net.sydokiddo.endlessencore.item.custom_items.SickleItem;
import net.sydokiddo.endlessencore.util.PlayerAttackPacket;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.fabricmc.api.EnvType;
import java.util.Objects;

// Sends Packets if Using Sickles

@Environment(EnvType.CLIENT)
@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @Shadow
    @Nullable
    public LocalPlayer player;
    @Shadow
    @Nullable
    public MultiPlayerGameMode interactionManager;
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
        Item offHandItem = player.getOffhandItem().getItem();
        Item mainHandItem = player.getMainHandItem().getItem();

        if (player != null && !player.isSpectator() && (offHandItem instanceof SickleItem)
                && (mainHandItem instanceof SickleItem)) {
            if (this.secondAttackCooldown <= 0) {
                if (this.crosshairTarget != null && !this.player.isHandsBusy()) {
                    switch (this.crosshairTarget.getType()) {
                        case ENTITY:
                            ((PlayerAccess) player).setOffhandAttack();
                            ((PlayerAccess) player).resetLastOffhandAttackTicks();
                            player.attack(((EntityHitResult) this.crosshairTarget).getEntity());
                            Objects.requireNonNull(Minecraft.getInstance().getConnection()).send(PlayerAttackPacket.attackPacket(((EntityHitResult) this.crosshairTarget).getEntity()));
                            break;
                // Allows for chests and other blocks to be opened/used if the player has a sickle in their off hand:
                        case BLOCK:
                            BlockHitResult blockHitResult = (BlockHitResult) this.crosshairTarget;
                            BlockPos blockPos = blockHitResult.getBlockPos();
                            if (!player.level.getBlockState(blockPos).isAir()) {
                                assert this.interactionManager != null;
                                this.interactionManager.useItemOn(player, InteractionHand.OFF_HAND, blockHitResult);
                                break;
                            }
                        case MISS:
                            assert this.interactionManager != null;
                            if (this.interactionManager.hasMissTime()) {
                                this.secondAttackCooldown = 10;
                            }
                            ((PlayerAccess) player).resetLastOffhandAttackTicks();
                    }
                    attackedOffhand = true;
                    this.player.swing(InteractionHand.OFF_HAND);
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