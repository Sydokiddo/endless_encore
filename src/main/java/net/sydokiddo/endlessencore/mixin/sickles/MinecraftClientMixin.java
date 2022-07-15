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

    @Shadow private int rightClickDelay;

    @Shadow
    @Nullable
    public MultiPlayerGameMode gameMode;

    @Shadow
    @Nullable
    public HitResult hitResult;

    private int secondAttackCooldown;
    private boolean attackedOffhand;

    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;handleKeybinds()V"))
    public void tickMixin(CallbackInfo info) {
        if (this.secondAttackCooldown > 0) {
            --this.secondAttackCooldown;
        }
        if (this.attackedOffhand) {
            this.rightClickDelay = 4;
        }
    }

    @Inject(method = "startUseItem", at = @At(value = "HEAD"), cancellable = true)
    private void doItemUseMixin(CallbackInfo info) {
        assert player != null;
        Item offHandItem = player.getOffhandItem().getItem();
        Item mainHandItem = player.getMainHandItem().getItem();

        if (player != null && !player.isSpectator() && (offHandItem instanceof SickleItem)
                && (mainHandItem instanceof SickleItem)) {
            if (this.secondAttackCooldown <= 0) {
                if (this.hitResult != null && !this.player.isHandsBusy()) {
                    switch (this.hitResult.getType()) {
                        case ENTITY:
                            ((PlayerAccess) player).setOffhandAttack();
                            ((PlayerAccess) player).resetLastOffhandAttackTicks();
                            player.attack(((EntityHitResult) this.hitResult).getEntity());
                            Objects.requireNonNull(Minecraft.getInstance().getConnection()).send(PlayerAttackPacket.attackPacket(((EntityHitResult) this.hitResult).getEntity()));
                            break;
                // Allows for chests and other blocks to be opened/used if the player has a sickle in their off hand:
                        case BLOCK:
                            BlockHitResult blockHitResult = (BlockHitResult) this.hitResult;
                            BlockPos blockPos = blockHitResult.getBlockPos();
                            if (!player.level.getBlockState(blockPos).isAir()) {
                                assert this.gameMode != null;
                                this.gameMode.useItemOn(player, InteractionHand.OFF_HAND, blockHitResult);
                                break;
                            }
                        case MISS:
                            assert this.gameMode != null;
                            if (this.gameMode.hasMissTime()) {
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